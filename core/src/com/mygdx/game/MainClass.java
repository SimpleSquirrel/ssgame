package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MainClass extends Game {
	public GameScreen game_screen;
	@Override
	public void create() {
		game_screen = new GameScreen(this);
		setScreen(game_screen);
	}
}
