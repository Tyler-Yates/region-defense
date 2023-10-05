package com.regiondefense.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * An entity that can give and receive damage.
 */
public abstract class DamageEntity extends AbstractEntity {
    protected int damage = 0;

    protected float health = 99999;
    protected float maxHealth = health;

    /**
     * Damages the base by the given amount.
     * This method will handle scaling damage by the deltaTime.
     *
     * @param damage    the damage
     * @param deltaTime the deltaTime to scale damage by
     */
    public void dealDamage(final float damage, final float deltaTime) {
        health -= damage * deltaTime;
    }

    @Override
    public void render(final RenderGroup renderGroup) {
        super.render(renderGroup);

        final String text = String.format("%d/%d", (int) health, (int) maxHealth);
        final GlyphLayout layout = new GlyphLayout(renderGroup.font, text);

        renderGroup.batch.begin();
        renderGroup.font.setColor(Color.BLACK);
        renderGroup.font.draw(renderGroup.batch, text, getX() - layout.width / 2, getY() + collision.radius + layout.height + 5);
        renderGroup.batch.end();
    }

    /**
     * Returns the damage that this entity deals.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}
