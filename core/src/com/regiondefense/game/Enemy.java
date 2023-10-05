package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

import static com.regiondefense.game.Constants.DIAGONAL_SPEED;

public class Enemy extends AbstractEntity {
    private static final int SPEED = 100;
    private static final int DAMAGE = 10;

    private final RegionDefenseGame game;

    public Enemy(final RegionDefenseGame game) {
        this.game = game;

        sprite = new Texture(Gdx.files.internal("enemy.png"));
        collision = new Circle(-500, 0, sprite.getWidth() / 2f - 2);
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);
    }

    @Override
    public void update(final float deltaTime) {
        // Stop moving when we hit the base
        if (collidesWith(game.base.collision)) {
            game.base.damage(DAMAGE, deltaTime);
            return;
        }

        float hor = 0;
        float ver = 0;

        if (game.base.getX() < collision.x) {
            hor = -1;
        } else if (game.base.getX() > collision.x) {
            hor = 1;
        }

        if (game.base.getY() < collision.y) {
            ver = -1;
        } else if (game.base.getY() > collision.y) {
            ver = 1;
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
