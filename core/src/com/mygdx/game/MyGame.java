package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
	public SpriteBatch batch;

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
