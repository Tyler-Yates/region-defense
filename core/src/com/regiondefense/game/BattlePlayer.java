package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.regiondefense.game.Constants.*;

public class BattlePlayer extends AbstractEntity {
    private static final int SPEED = 200;

    private final Texture playerImage;
    private final ShapeRenderer shapeRenderer;

    private Rectangle box;

    public BattlePlayer() {
        playerImage = new Texture(Gdx.files.internal("player.png"));

        box = new Rectangle();
        box.x = 0;
        box.y = 0;
        box.width = 32;
        box.height = 32;

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(final SpriteBatch batch, final Camera camera) {
        // Make the camera follow the player
        // The camera position is actually the center of the screen so set it to the player's x and y position
        camera.position.set(box.x, box.y, 0);
        camera.update();

        // Draw the player sprite
        batch.begin();
        batch.draw(playerImage, box.x, box.y);
        batch.end();

        // Draw the bounding box for debug
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(box.x, box.y, box.width, box.height);
        shapeRenderer.end();
    }

    @Override
    public void update(final float deltaTime) {
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

        box.x += hmov;
        box.y += vmov;
    }

    @Override
    public void dispose() {
        playerImage.dispose();
    }
}
