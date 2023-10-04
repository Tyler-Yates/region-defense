package com.regiondefense.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
    /**
     * Method to update and render the entity. Called once per frame.
     * <p>
     * DO NOT reimplement this method!
     *
     * @param batch the SpriteBatch
     * @param deltaTime the time since the last frame as a fraction of a second
     */
    void updateAndRender(final SpriteBatch batch, final float deltaTime);

    /**
     * Method to handle disposing of any resources that need it.
     */
    void dispose();
}
