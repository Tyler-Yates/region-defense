package com.regiondefense.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class RegionDefenseGame extends ApplicationAdapter {
	SpriteBatch gameBatch;
	OrthographicCamera gameCamera;
	ShapeRenderer shapeRenderer;

	SpriteBatch uiBatch;
	BitmapFont font;
	OrthographicCamera UiCamera;

	RenderGroup gameRenderGroup;

	BattlePlayer player;
	Base base;

	List<Enemy> enemyList = new ArrayList<>();

	private PerformanceOverlay performanceOverlay;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		uiBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho(false, 1024, 768);

		UiCamera = new OrthographicCamera();
		UiCamera.setToOrtho(false, 1024, 768);

		font = new BitmapFont(Gdx.files.internal("overlay_font/font.fnt"));

		gameRenderGroup = new RenderGroup(gameBatch, gameCamera, shapeRenderer, font);

		player = new BattlePlayer();
		base = new Base();

		enemyList.add(new Enemy(this));

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

		// Clear the screen to get a fresh drawing space
		ScreenUtils.clear(1, 1, 1, 1);

		// Perform all updates before we render anything
		handleUpdates(deltaTime);

		// Make the camera follow the player
		// The camera position is actually the center of the screen so set it to the player's x and y position
		gameCamera.position.set(player.getX(), player.getY(), 0);
		gameCamera.update();

		// Render based on the camera within the game world
		gameBatch.setProjectionMatrix(gameCamera.combined);
		shapeRenderer.setProjectionMatrix(gameCamera.combined);
		gameCamera.update();

		// Render everything as the last step
		handleRenders();
	}

	private void handleRenders() {
		player.render(gameRenderGroup);
		base.render(gameRenderGroup);
		for (final Enemy enemy: enemyList) {
			enemy.render(gameRenderGroup);
		}

		// We don't want the overlay to be rendered based on the camera
		performanceOverlay.render(font, uiBatch);
	}

	private void handleUpdates(final float deltaTime) {
		player.update(deltaTime);
		for (final Enemy enemy: enemyList) {
			enemy.update(deltaTime);
		}
		base.update(deltaTime);
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		player.dispose();
		font.dispose();
	}
}
