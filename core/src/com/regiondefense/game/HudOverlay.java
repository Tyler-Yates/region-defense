package com.regiondefense.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import static com.regiondefense.game.Constants.UI_BORDER;

public class HudOverlay implements Overlay {
    private final RegionDefenseGame game;

    public HudOverlay(final RegionDefenseGame game) {
        this.game = game;
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        final String text = String.format("%d/%d", (int) game.base.getHealth(), (int) game.base.getMaxHealth());
        final GlyphLayout layout = new GlyphLayout(renderGroup.font, text);

        renderGroup.batch.begin();
        renderGroup.font.setColor(Color.BLACK);
        renderGroup.font.draw(renderGroup.batch, text, UI_BORDER, layout.height + UI_BORDER);
        renderGroup.batch.end();
    }
}
