package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

import static com.regiondefense.game.Constants.DIAGONAL_SPEED;

public class BattlePlayer extends AbstractEntity {
    private static final int SPEED = 200;

    public BattlePlayer() {
        sprite = new Texture(Gdx.files.internal("player.png"));

        final float radius = sprite.getWidth() / 2f;
        collision = new Circle(-radius, -radius, radius);
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);
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

        collision.x += hmov;
        collision.y += vmov;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
