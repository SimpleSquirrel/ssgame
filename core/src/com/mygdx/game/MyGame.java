package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Screens.MenuScreen;

public class MyGame extends Game {
	public SpriteBatch batch;
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
