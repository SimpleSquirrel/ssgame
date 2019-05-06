package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Enemies.Cannon;
import com.mygdx.game.Enemies.DefendedCannon;
import com.mygdx.game.Enemies.VerticalCannon;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.NovelScreen;

public class MyGame extends Game {
	public SpriteBatch batch;

	public static Array<Bullet> playerBullets = new Array<Bullet>();
	public static Array<Cannon> cannons = new Array<Cannon>();
	public static Array<VerticalCannon> verticalCannons = new Array<VerticalCannon>();
	public static Array<DefendedCannon> defendedCannons = new Array<DefendedCannon>();
	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short BULLET_BIT = 4;
	public static final short ENEMY_BIT = 8;
	public static final short CHEST_BIT = 16;
	public static final short FLOOR_BIT = 32;
	public static final short SENSOR_BIT = 64;
	public static final short SWORD_BIT = 128;
	public static final short WEAK_POINT_BIT = 256;
	public static final short PORTAL_BIT = 512;

	public final  static float PPM = 100;

	public void create() {
		Assets.load();
		batch = new SpriteBatch();
		this.setScreen(new NovelScreen());

	}
	public void render(){
		super.render();
	}
	public void dispose(){
		batch.dispose();
	}

}
