package com.regiondefense.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Overlay {
    /**
     * Render the overlay on screen.
     */
    void render(BitmapFont font, SpriteBatch batch);
}
