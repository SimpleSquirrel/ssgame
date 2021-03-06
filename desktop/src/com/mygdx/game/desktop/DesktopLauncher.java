package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		System.setProperty("user.name","Public");
		config.title = "Game";
		config.useGL30 = false;
		config.width = 1600;
		config.height = 900;
		config.fullscreen = true;
		new LwjglApplication(new MyGame(), config);
	}
}