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

		ScreenUtils.clear(1, 1, 1, 1);

		player.updateAndRender(batch, deltaTime);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
	}
}
