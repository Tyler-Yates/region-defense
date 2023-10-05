package com.regiondefense.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

/**
 * Abstract parent class for all entities.
 */
public abstract class AbstractEntity implements Entity {
    // The collision circle for the entity.
    protected Circle collision;

    // The sprite for the entity
    protected Texture sprite;

    /**
     * Checks if there is a collision between the two collision circles.
     *
     * @param circle the circle to see if we collide with
     * @return true if there is a collision, false otherwise
     */
    public boolean collidesWith(final Circle circle) {
        return collision.overlaps(circle);
    }

    public float getX() {
        return collision.x;
    }

    public float getY() {
        return collision.y;
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        renderGroup.batch.begin();
        renderGroup.batch.draw(sprite, collision.x - sprite.getWidth() / 2f, collision.y - sprite.getHeight() / 2f);
        renderGroup.batch.end();

        // Draw the bounding box for debug
        renderGroup.shapeRenderer.setProjectionMatrix(renderGroup.camera.combined);
        renderGroup.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderGroup.shapeRenderer.setColor(Color.RED);
        renderGroup.shapeRenderer.circle(collision.x, collision.y, collision.radius);
        renderGroup.shapeRenderer.end();
    }

    @Override
    public void dispose() {
        sprite.dispose();
    }
}
