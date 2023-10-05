package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a single tower on a battle map. Automatically shoots at enemies.
 */
public class Tower extends AbstractEntity {
    private static final AtomicInteger IDS = new AtomicInteger(1);

    private static final int BASE_DAMAGE = 10;
    private static final int BASE_RANGE = 200;
    private static final int BASE_RELOAD_TIME = 2;

    private final int id;

    private int damage = BASE_DAMAGE;
    private int range = BASE_RANGE;
    private float reloadTime = BASE_RELOAD_TIME;

    // The current level of the tower. This affects how powerful the tower is.
    private int level = 1;

    // The current amount of reload time left. When this is at or below zero, we're ready to fire.
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

        final String idText = String.format("%d", id);
        final GlyphLayout idLayout = new GlyphLayout(renderGroup.font, idText);
        final String levelText = String.format("Lv %d", level);
        final GlyphLayout levelLayout = new GlyphLayout(renderGroup.font, levelText);
        renderGroup.batch.begin();
        // Draw ID for identification to debug
        // Change color to show reload status
        if (reloadTimeLeft <= 0) {
            renderGroup.font.setColor(Color.WHITE);
        } else {
            renderGroup.font.setColor(Color.RED);
        }
        renderGroup.font.draw(renderGroup.batch, idText, getX() - idLayout.width / 2f, getY() + idLayout.height / 2f);
        // Draw the tower level
        renderGroup.font.setColor(Color.WHITE);
        renderGroup.font.draw(renderGroup.batch, levelText, getX() - levelLayout.width / 2f, getY() - levelLayout.height / 2f);
        renderGroup.batch.end();

        // Draw reload bar
        final Rectangle reloadBar = new Rectangle(getX() - 20, getY() + 10, 40, 10);
        renderGroup.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderGroup.shapeRenderer.setColor(Color.RED);
        renderGroup.shapeRenderer.rect(reloadBar.x, reloadBar.y, reloadBar.width * (reloadTimeLeft / reloadTime), reloadBar.height);
        renderGroup.shapeRenderer.end();

        renderGroup.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        // Draw reload bar outline
        renderGroup.shapeRenderer.setColor(Color.WHITE);
        renderGroup.shapeRenderer.rect(reloadBar.x, reloadBar.y, reloadBar.width, reloadBar.height);
        // Draw the range for debug
        renderGroup.shapeRenderer.setColor(Color.TEAL);
        renderGroup.shapeRenderer.circle(collision.x, collision.y, range);
        renderGroup.shapeRenderer.end();
    }

    /**
     * Levels up the tower. This method adjust stats based on the new level.
     */
    public void levelUp() {
        level++;

        damage = BASE_DAMAGE * level;
        range = (int) (BASE_RANGE + 50 * Math.sqrt(level - 1));
        reloadTime = (float) (BASE_RELOAD_TIME / Math.sqrt(level));
        System.out.printf("Tower %d | lv %d | damage:%d range:%d reloadTime:%f %n", id, level, damage, range, reloadTime);
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
            reloadTimeLeft = reloadTime;
        }
    }

    public int getId() {
        return id;
    }
}
