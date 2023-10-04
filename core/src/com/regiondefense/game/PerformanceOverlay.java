package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PerformanceOverlay implements Overlay {
    @Override
    public void render(final BitmapFont font, final SpriteBatch batch) {
        final int deltaTimeMs = (int) (Gdx.graphics.getDeltaTime() * 1000);

        final String text = String.format("Frametime: %dms", deltaTimeMs);
        final GlyphLayout layout = new GlyphLayout(font, text);

        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, text, Gdx.graphics.getWidth() - layout.width - 10, Gdx.graphics.getHeight() - 10);
        batch.end();
    }
}
