package com.example.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.example.hanoi.HanoiGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = HanoiGame.SCREEN_WIDTH;
		config.height = HanoiGame.SCREEN_HEIGHT;
		new LwjglApplication(new HanoiGame(), config);
	}
}
