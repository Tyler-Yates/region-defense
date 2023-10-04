package com.regiondefense.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractEntity implements Entity {
    @Override
    public void updateAndRender(final SpriteBatch batch, final float deltaTime) {
        if (deltaTime > 0.5) {
            System.err.println("Skipping rendering due to high deltaTime: " + deltaTime);
            return;
        }

        this.update(deltaTime);

        this.render(batch);
    }

    /**
     * Method to handle rendering the entity. This will be called <strong>after</strong> the {@link #update(float)}
     * method.
     *
     * @param batch the SpriteBatch
     */
    protected abstract void render(final SpriteBatch batch);

    /**
     * Method to handle updating the state of the entity. This will be called <strong>before</strong> the
     * {@link #render(SpriteBatch)} method.
     *
     * @param deltaTime the time since the last frame as a fraction of a second
     */
    protected abstract void update(final float deltaTime);
}
