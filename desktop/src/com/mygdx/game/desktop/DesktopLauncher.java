package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
<<<<<<< HEAD
import com.mygdx.game.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		config.title = "Game";
		config.useGL30 = false;
		config.width = 1600;
		config.height = 900;
		new LwjglApplication(new MyGame(), config);
=======
import com.mygdx.game.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Game";
		cfg.useGL30 = false;
		cfg.width = 1600;
		cfg.height = 900;

		new LwjglApplication(new MainClass(), cfg);

>>>>>>> 9300740b22894359a395e197f8c5295c4c970de7
	}
}
