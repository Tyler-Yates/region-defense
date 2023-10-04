package com.regiondefense.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.regiondefense.game.RegionDefenseGame;

import static com.regiondefense.game.Constants.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(FPS);
		config.setTitle("RegionDefense");
		config.setWindowedMode(WINDOW_WIDTH, WINDOW_HEIGHT);
		config.useVsync(true);
		new Lwjgl3Application(new RegionDefenseGame(), config);
	}
}
