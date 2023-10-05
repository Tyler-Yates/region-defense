package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Base extends AbstractEntity {
    private final Texture sprite;

    private int health = 1000;

    public Base() {
        this.sprite = new Texture(Gdx.files.internal("base.png"));
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        renderGroup.batch.begin();
        renderGroup.batch.draw(sprite, -sprite.getWidth()/2f, -sprite.getHeight()/2f);
        renderGroup.batch.end();
    }

    @Override
    public void update(final float deltaTime) {

    }

    @Override
    public void dispose() {
        sprite.dispose();
    }
}
