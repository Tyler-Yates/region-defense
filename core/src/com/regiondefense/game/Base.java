package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Base extends DamageEntity {
    private final RegionDefenseGame game;

    public Base(final RegionDefenseGame game) {
        this.game = game;

        sprite = new Texture(Gdx.files.internal("base.png"));
        collision = new Circle(0, 0, sprite.getWidth() / 2f - 5);

        health = 1000;
        maxHealth = health;

        damage = 10;
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);
    }

    @Override
    public void update(final float deltaTime) {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
