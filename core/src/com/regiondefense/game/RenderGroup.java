package com.regiondefense.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RenderGroup {
    public final SpriteBatch batch;
    public final Camera camera;
    public final ShapeRenderer shapeRenderer;
    public final BitmapFont font;

    public RenderGroup(final SpriteBatch batch, final Camera camera, final ShapeRenderer shapeRenderer, final BitmapFont font) {
        this.batch = batch;
        this.camera = camera;
        this.shapeRenderer = shapeRenderer;
        this.font = font;
    }
}
