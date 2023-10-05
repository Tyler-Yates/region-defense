package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Base extends AbstractEntity {
    private int health = 1000;

    public Base() {
        sprite = new Texture(Gdx.files.internal("base.png"));
        collision = new Circle(0, 0, sprite.getWidth() / 2f - 5);
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
