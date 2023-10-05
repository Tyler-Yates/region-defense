package com.regiondefense.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
    /**
     * Method to handle rendering the entity. This will be called <strong>after</strong> the {@link #update(float)}
     * method.
     *
     * @param batch the SpriteBatch
     * @param camera the Camera
     */
    void render(final SpriteBatch batch, final Camera camera);

    /**
     * Method to handle updating the state of the entity. This will be called <strong>before</strong> the
     * {@link #render(SpriteBatch, Camera)} method.
     *
     * @param deltaTime the time since the last frame as a fraction of a second
     */
    void update(final float deltaTime);

    /**
     * Method to handle disposing of any resources that need it.
     */
    void dispose();
}
