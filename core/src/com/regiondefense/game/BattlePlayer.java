package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static com.regiondefense.game.Constants.DIAGONAL_SPEED;

public class BattlePlayer extends AbstractEntity {
    private static final int SPEED = 200;

    private Texture playerImage;

    private Rectangle boundingBox;

    private float x;
    private float y;

    public BattlePlayer() {
        playerImage = new Texture(Gdx.files.internal("player.png"));

        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;

        boundingBox = new Rectangle();
        boundingBox.width = 32;
        boundingBox.height = 32;
    }

    @Override
    protected void render(final SpriteBatch batch) {
        batch.begin();
        batch.draw(playerImage, x, y);
        batch.end();
    }

    @Override
    protected void update(final float deltaTime) {
        handleMovement(deltaTime);
    }

    private void handleMovement(final float deltaTime) {
        float hor = 0;
        float ver = 0;

        if (Gdx.input.isKeyPressed(Keys.A)) {
            hor --;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            hor ++;
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            ver --;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            ver ++;
        }

        float hmov, vmov;
        hmov = hor * SPEED * deltaTime;
        vmov = ver * SPEED * deltaTime;

        // We don't want the player moving faster in the diagonal direction
        if (hor != 0 && ver != 0) {
            hmov = hmov * DIAGONAL_SPEED;
            vmov = vmov * DIAGONAL_SPEED;
        }

        x += hmov;
        y += vmov;
    }

    @Override
    public void dispose() {
        playerImage.dispose();
    }
}
