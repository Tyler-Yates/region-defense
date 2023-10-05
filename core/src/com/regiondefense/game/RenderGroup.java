package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.regiondefense.game.Constants.WINDOW_HEIGHT;
import static com.regiondefense.game.Constants.WINDOW_WIDTH;

public class RenderGroup {
    public final SpriteBatch batch;
    public final Camera camera;
    public final ShapeRenderer shapeRenderer;
    public final BitmapFont font;

    public RenderGroup() {
        this.batch = new SpriteBatch();

        this.camera = new OrthographicCamera(WINDOW_WIDTH, WINDOW_HEIGHT);

        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont(Gdx.files.internal("overlay_font/font.fnt"));
    }

    /**
     * Disposes of all relevant fields in this group.
     */
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
