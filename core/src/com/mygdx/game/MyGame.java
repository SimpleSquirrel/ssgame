package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Enemies.*;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level10.GameScreenLevel10;
import com.mygdx.game.Levels.Level5.GameScreenLevel5;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Screens.DialogScreens.FirstMeet;
import com.mygdx.game.Screens.DialogScreens.SecondBoss;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.WeaponScreen;

public class MyGame extends Game {
	public SpriteBatch batch;

	public static Array<Cactus> cactuses = new Array<Cactus>();
	public static Array<ArmoredBiter> armoredBiters = new Array<ArmoredBiter>();
	public static Array<Bullet> playerBullets = new Array<Bullet>();
	public static Array<Enemy> enemies = new Array<Enemy>();
	public static Array<Cannon> cannons = new Array<Cannon>();
	public static Array<VerticalCannon> verticalCannons = new Array<VerticalCannon>();
	public static Array<DefendedCannon> defendedCannons = new Array<DefendedCannon>();
	public static Array<Biter> biters = new Array<Biter>();
	public static Array<Wonder> wonders = new Array<Wonder>();
	public static Array<Cupcake> cupcakes = new Array<Cupcake>();
	public static Preferences preferences;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short BULLET_BIT = 4;
	public static final short ENEMY_BIT = 8;
	public static final short CHEST_BIT = 16;
	public static final short WONDER_BIT = 32;
	public static final short SENSOR_BIT = 64;
	public static final short SWORD_BIT = 128;
	public static final short PIE_BIT = 256;
	public static final short PORTAL_BIT = 512;
	public static final short SPIKE_BIT = 1024;
	public static final short ROCKET_BIT = 2048;
	public static final short SHIELD_BIT = 4096;
	public static final short LUXURY_CHEST_BIT = 8192;
	public static final short BOSS_BIT = 16384;
	public static  int SOUND=0;
	public static  short MUZIC=0;
	public final  static float PPM = 100;

	public void create() {

		Assets.load();
		batch = new SpriteBatch();
		preferences = Gdx.app.getPreferences("SaveData");
		this.setScreen(new MenuScreen(this));

	}
	public void render(){
		super.render();
	}
	public void dispose(){
		batch.dispose();
	}

}
