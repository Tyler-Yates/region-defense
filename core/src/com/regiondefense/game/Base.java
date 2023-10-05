package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Base extends AbstractEntity {
    private final RegionDefenseGame game;

    private float health;
    private float maxHealth;

    public Base(final RegionDefenseGame game) {
        this.game = game;

        sprite = new Texture(Gdx.files.internal("base.png"));
        collision = new Circle(0, 0, sprite.getWidth() / 2f - 5);

        health = 1000;
        maxHealth = health;
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);
    }

    @Override
    public void update(final float deltaTime) {
        for (final Enemy enemy : game.enemyList) {
            if (enemy.collidesWith(collision)) {

            }
        }
    }

    /**
     * Damages the base by the given amount.
     * This method will handle scaling damage by the deltaTime.
     *
     * @param damage the damage
     * @param deltaTime the deltaTime to scale damage by
     */
    public void damage(final float damage, final float deltaTime) {
        health -= damage * deltaTime;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}
