package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.regiondefense.game.Constants.*;

public class BattlePlayer extends AbstractEntity {
    private static final int SPEED = 200;

    private final Texture sprite;

    private Rectangle box;

    public BattlePlayer() {
        sprite = new Texture(Gdx.files.internal("player.png"));

        box = new Rectangle();
        box.width = sprite.getWidth();
        box.height = sprite.getHeight();
        box.x = 0;
        box.y = 0;
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        // Make the camera follow the player
        // The camera position is actually the center of the screen so set it to the player's x and y position
        renderGroup.camera.position.set(box.x, box.y, 0);
        renderGroup.camera.update();

        // Draw the player sprite
        renderGroup.batch.begin();
        renderGroup.batch.draw(sprite, box.x - box.width/2f, box.y - box.height/2f);
        renderGroup.batch.end();

        // Draw the bounding box for debug
        renderGroup.shapeRenderer.setProjectionMatrix(renderGroup.camera.combined);
        renderGroup.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderGroup.shapeRenderer.setColor(Color.RED);
        renderGroup.shapeRenderer.rect(box.x - box.width/2f, box.y - box.height/2f, box.width, box.height);
        renderGroup.shapeRenderer.end();
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
        sprite.dispose();
    }
}
