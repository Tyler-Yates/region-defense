package com.regiondefense.game;

public interface Entity {
    /**
     * Method to handle rendering the entity. This will be called <strong>after</strong> the {@link #update(float)}
     * method.
     *
     * @param renderGroup the RenderGroup
     */
    void render(final RenderGroup renderGroup);

    /**
     * Method to handle updating the state of the entity. This will be called <strong>before</strong> the
     * {@link #render(RenderGroup)} method.
     *
     * @param deltaTime the time since the last frame as a fraction of a second
     */
    void update(final float deltaTime);

    /**
     * Method to handle disposing of any resources that need it.
     */
    void dispose();
}
