package com.regiondefense.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class RegionDefenseGame extends ApplicationAdapter {
	SpriteBatch gameBatch;
	OrthographicCamera gameCamera;

	SpriteBatch uiBatch;
	BitmapFont overlayFont;
	OrthographicCamera UiCamera;

	private BattlePlayer player;
	private PerformanceOverlay performanceOverlay;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		uiBatch = new SpriteBatch();

		gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho(false, 1024, 768);

		UiCamera = new OrthographicCamera();
		UiCamera.setToOrtho(false, 1024, 768);

		overlayFont = new BitmapFont(Gdx.files.internal("overlay_font/font.fnt"));

		player = new BattlePlayer();
		performanceOverlay = new PerformanceOverlay(this);
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
		gameCamera.update();

		// Render based on the camera within the game world
		gameBatch.setProjectionMatrix(gameCamera.combined);

		// Perform all updates before we render anything
		handleUpdates(deltaTime);

		// Render everything as the last step
		handleRenders(gameBatch);
	}

	private void handleRenders(final SpriteBatch spriteBatch) {
		player.render(spriteBatch, gameCamera);

		// We don't want the overlay to be rendered based on the camera
		performanceOverlay.render(overlayFont, uiBatch);
	}

	private void handleUpdates(final float deltaTime) {
		player.update(deltaTime);
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		player.dispose();
		overlayFont.dispose();
	}
}
