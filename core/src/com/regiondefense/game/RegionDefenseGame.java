package com.regiondefense.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegionDefenseGame extends ApplicationAdapter {
    RenderGroup gameRenderGroup;
    RenderGroup uiRenderGroup;

    BattlePlayer player;
    Base base;

    List<Enemy> enemyList = new ArrayList<>();
    List<Tower> towerList = new ArrayList<>();

    List<Overlay> overlayList = new ArrayList<>();

    @Override
    public void create() {
        // Rendering group for the game which moves within the world
        gameRenderGroup = new RenderGroup();

        // Rendering group for the UI which does not move within the world
        uiRenderGroup = new RenderGroup();

        player = new BattlePlayer(this);
        base = new Base(this);

        enemyList.add(new Enemy(this, -500, 200));
        enemyList.add(new Enemy(this, 800, -300));
        enemyList.add(new Enemy(this, 800, 800));

        towerList.add(new Tower(this, -300, 0));
        towerList.add(new Tower(this, 300, 0));
        towerList.add(new Tower(this, 0, -300));
        towerList.add(new Tower(this, 0, 300));

        overlayList.add(new HudOverlay(this));
        overlayList.add(new PerformanceOverlay(this));
    }

    @Override
    public void render() {
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

        // Make the game camera follow the player.
        // The camera position is actually the center of the screen so set it to the player's x and y position.
        gameRenderGroup.camera.position.set(player.getX(), player.getY(), 0);
        gameRenderGroup.camera.update();

        // Render based on the camera within the game world
        gameRenderGroup.batch.setProjectionMatrix(gameRenderGroup.camera.combined);
        gameRenderGroup.shapeRenderer.setProjectionMatrix(gameRenderGroup.camera.combined);

        // Render everything as the last step
        handleRenders();
    }

    private void handleRenders() {
        base.render(gameRenderGroup);

        for (final Tower tower : towerList) {
            tower.render(gameRenderGroup);
        }

        for (final Enemy enemy : enemyList) {
            enemy.render(gameRenderGroup);
        }

        // Render the player the last amongst objects, so it always shows up on top
        player.render(gameRenderGroup);

        // We don't want the overlay to be rendered based on the camera
        for (final Overlay overlay : overlayList) {
            overlay.render(uiRenderGroup);
        }
    }

    private void handleUpdates(final float deltaTime) {
        player.update(deltaTime);

        for (final Tower tower : towerList) {
            tower.update(deltaTime);
        }

        for (final Iterator<Enemy> iterator = enemyList.iterator(); iterator.hasNext(); ) {
            final Enemy enemy = iterator.next();
            enemy.update(deltaTime);

            if (enemy.getHealth() <= 0) {
                iterator.remove();
            }
        }

        base.update(deltaTime);
    }

    @Override
    public void dispose() {
        gameRenderGroup.dispose();
        uiRenderGroup.dispose();
    }
}
