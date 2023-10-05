package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Enemy extends DamageEntity {
    private static final int SPEED = 100;

    private final RegionDefenseGame game;

    public Enemy(final RegionDefenseGame game, final float x, final float y) {
        this.game = game;

        health = 100;
        maxHealth = health;

        damage = 10;

        sprite = new Texture(Gdx.files.internal("enemy.png"));
        collision = new Circle(x, y, sprite.getWidth() / 2f - 2);
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);
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
}
