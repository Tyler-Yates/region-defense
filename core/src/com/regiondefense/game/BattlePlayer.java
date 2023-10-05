package com.regiondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import static com.regiondefense.game.Constants.DIAGONAL_SPEED;

public class BattlePlayer extends AbstractEntity {
    private static final int SPEED = 200;
    private static final int MAX_INTERACT_DISTANCE = 100;

    private final RegionDefenseGame game;

    public BattlePlayer(final RegionDefenseGame game) {
        this.game = game;

        sprite = new Texture(Gdx.files.internal("player.png"));

        final float radius = sprite.getWidth() / 2f;
        collision = new Circle(-radius, -radius, radius);
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);

        // Draw the range for debug
        renderGroup.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderGroup.shapeRenderer.setColor(Color.YELLOW);
        renderGroup.shapeRenderer.circle(collision.x, collision.y, MAX_INTERACT_DISTANCE);
        renderGroup.shapeRenderer.end();
    }

    @Override
    public void update(final float deltaTime) {
        handleMovement(deltaTime);

        handleCommands(deltaTime);
    }

    private void handleCommands(final float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            double closestDistance = Double.MAX_VALUE;
            Tower closestTower = null;
            for (final Tower tower : game.towerList) {
                final double distanceToTower = Math.hypot(tower.getX() - getX(), tower.getY() - getY());
                if (distanceToTower < closestDistance) {
                    closestDistance = distanceToTower;
                    closestTower = tower;
                }
            }

            if (closestTower != null) {
                System.out.printf("Level up tower %d%n", closestTower.getId());
                closestTower.levelUp();
            }
        }
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
