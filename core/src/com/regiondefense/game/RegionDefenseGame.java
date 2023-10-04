package com.regiondefense.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class RegionDefenseGame extends ApplicationAdapter {
	SpriteBatch batch;

	private BattlePlayer player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		player = new BattlePlayer();
	}

	@Override
	public void render () {
		final float deltaTime = Gdx.graphics.getDeltaTime();

		// If the game is minimized or paused we can get very high deltaTime values, so just discard that first frame
		if (deltaTime > 0.5) {
			System.err.println("Skipping rendering due to high deltaTime: " + deltaTime);
			return;
		}

		ScreenUtils.clear(1, 1, 1, 1);

		// Perform all updates before we render anything
		handleUpdates(deltaTime);

		// Render everything as the last step
		handleRenders(batch);
	}

	private void handleRenders(final SpriteBatch spriteBatch) {
		player.render(spriteBatch);
	}

	private void handleUpdates(final float deltaTime) {
		player.update(deltaTime);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
	}
}
