package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Circle;

import java.util.concurrent.atomic.AtomicInteger;

public class Enemy extends DamageEntity {
    private static final AtomicInteger IDS = new AtomicInteger(1);

    private static final int SPEED = 100;

    private final RegionDefenseGame game;
    private final int id;

    public Enemy(final RegionDefenseGame game, final float x, final float y) {
        this.game = game;

        id = IDS.getAndIncrement();

        health = 100;
        maxHealth = health;

        damage = 10;

        sprite = new Texture(Gdx.files.internal("enemy.png"));
        collision = new Circle(x, y, sprite.getWidth() / 2f - 2);
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);

        // Draw ID for identification to debug
        final String text = String.format("%d", id);
        final GlyphLayout layout = new GlyphLayout(renderGroup.font, text);
        renderGroup.batch.begin();
        renderGroup.font.setColor(Color.WHITE);
        renderGroup.font.draw(renderGroup.batch, text, getX() - layout.width / 2f, getY() + layout.height / 2f);
        renderGroup.batch.end();
    }

    @Override
    public void update(final float deltaTime) {
        // Stop moving when we hit the base
        if (collidesWith(game.base.collision)) {
            game.base.dealDamage(getDamage(), deltaTime);

            dealDamage(game.base.getDamage(), deltaTime);
            return;
        }

        // Move towards the center of the base
        final double angle = Math.atan2(game.base.getY() - getY(), game.base.getX() - getX());
        float hor = (float) Math.cos(angle);
        float ver = (float) Math.sin(angle);
        collision.x += hor * SPEED * deltaTime;
        collision.y += ver * SPEED * deltaTime;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public int getId() {
        return id;
    }
}
