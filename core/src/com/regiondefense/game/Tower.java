package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a single tower on a battle map. Automatically shoots at enemies.
 */
public class Tower extends AbstractEntity {
    private static final AtomicInteger IDS = new AtomicInteger(1);

    private final int id;

    private int damage = 10;
    private int range = 250;
    private float reloadTimeSeconds = 2;

    private float reloadTimeLeft = 0;

    private final RegionDefenseGame game;

    public Tower(final RegionDefenseGame game, final float x, final float y) {
        this.game = game;

        id = IDS.getAndIncrement();

        sprite = new Texture(Gdx.files.internal("tower.png"));
        collision = new Circle(x, y, sprite.getWidth() / 2f - 2);
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);

        // Draw ID for identification to debug
        final String text = String.format("%d", id);
        final GlyphLayout layout = new GlyphLayout(renderGroup.font, text);
        renderGroup.batch.begin();
        // Change color to show reload status
        if (reloadTimeLeft == 0) {
            renderGroup.font.setColor(Color.WHITE);
        } else {
            renderGroup.font.setColor(Color.RED);
        }
        renderGroup.font.draw(renderGroup.batch, text, getX() - layout.width / 2f, getY() + layout.height / 2f);
        renderGroup.batch.end();

        // Draw the range for debug
        renderGroup.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderGroup.shapeRenderer.setColor(Color.TEAL);
        renderGroup.shapeRenderer.circle(collision.x, collision.y, range);
        renderGroup.shapeRenderer.end();
    }

    @Override
    public void update(final float deltaTime) {
        handleFiring(deltaTime);
    }

    private void handleFiring(final float deltaTime) {
        if (reloadTimeLeft > 0) {
            reloadTimeLeft -= deltaTime;
            return;
        }

        // Find the enemy in range that is closest to the base and damage that one
        double closestDistance = Float.MAX_VALUE;
        Enemy closestEnemy = null;

        for (final Enemy enemy : game.enemyList) {
            final double distanceToEnemy = Math.hypot(enemy.getX() - getX(), enemy.getY() - getY());
            if (distanceToEnemy < range) {
                final double distanceToBase = Math.hypot(enemy.getX() - game.base.getX(), enemy.getY() - game.base.getY());
                if (distanceToBase < closestDistance) {
                    closestDistance = distanceToBase;
                    closestEnemy = enemy;
                }
            }
        }

        if (closestEnemy != null) {
            System.out.printf("Tower %d firing at enemy %d%n", id, closestEnemy.getId());

            // Deal full damage
            closestEnemy.dealDamage(damage, 1);

            // Start reloading
            reloadTimeLeft = reloadTimeSeconds;
        }
    }
}
