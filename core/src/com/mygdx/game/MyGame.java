package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Screens.MenuScreen;

public class MyGame extends Game {
	public SpriteBatch batch;

	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short BULLET_BIT = 4;
	public static final short ENEMY_BIT = 8;
	public static final short CHEST_BIT = 16;
	public static final short FLOOR_BIT = 32;
	public static final short SENSOR_BIT = 64;

	public final  static float PPM = 100;

	public void create() {
		Assets.load();
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));

	}
	public void render(){
		super.render();
	}
	public void dispose(){
		batch.dispose();
	}

}
