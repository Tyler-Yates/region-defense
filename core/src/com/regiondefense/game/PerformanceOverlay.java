package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import static com.regiondefense.game.Constants.UI_BORDER;

public class PerformanceOverlay implements Overlay {
    private final RegionDefenseGame game;

    public PerformanceOverlay(final RegionDefenseGame game) {
        this.game = game;
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        final int deltaTimeMs = (int) (Gdx.graphics.getDeltaTime() * 1000);

        final String text = String.format("(%d, %d) Frametime: %dms",
                (int) game.gameRenderGroup.camera.position.x, (int) game.gameRenderGroup.camera.position.y, deltaTimeMs);
        final GlyphLayout layout = new GlyphLayout(renderGroup.font, text);

        renderGroup.batch.begin();
        renderGroup.font.setColor(Color.BLACK);
        renderGroup.font.draw(renderGroup.batch, text, Gdx.graphics.getWidth() - layout.width - UI_BORDER, Gdx.graphics.getHeight() - UI_BORDER);
        renderGroup.batch.end();
    }
}
