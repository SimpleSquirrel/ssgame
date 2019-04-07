package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.MyGame;

public class NewGameScreen implements Screen {
    MyGame game;    //MyGame constructor
    OrthographicCamera camera; //Creating a camera
    SpriteBatch batch;
    public NewGameScreen(MyGame game) { //constructor
        this.game = game;

        camera = new OrthographicCamera(); //Initialising camera
        camera.setToOrtho(false, 1600, 900); //setting sizes for camera
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1); //setting bg color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

        camera.update(); //idk
        batch.setProjectionMatrix(camera.combined); //idk

        batch.begin(); //start of rendering
        batch.draw(Assets.spriteNewGameScreenBack, 0, 0);
        if(Gdx.input.getX() <250 + 300 && Gdx.input.getX() > 250 && Gdx.input.getY() > 900-200- 250  && Gdx.input.getY() < 900-300) { //setting bounds of NewGameButton
            batch.draw(Assets.spriteDeathScreenDaActive, 300, 200, 300, 150); //Drawing Active
            if (Gdx.input.isTouched()) { //creating an event
                this.dispose();
                game.setScreen(new GameScreenLevel1(game)); //changing screen
            }
        }
        else{
            batch.draw(Assets.spriteDeathScreenDaInActive, 300, 200, 300, 150);
        }
        if(Gdx.input.getX() <650 + 300 && Gdx.input.getX() > 650 && Gdx.input.getY() > 900-200- 250  && Gdx.input.getY() < 900-300) { //setting bounds of NewGameButton
            batch.draw(Assets.spriteNoActive, 750, 200, 300, 150); //Drawing Active
            if (Gdx.input.isTouched()) { //creating an event
                this.dispose();
                game.setScreen(new MenuScreen(game)); //changing screen
            }
        }
        else{
            batch.draw(Assets.spriteDeathScreenNoInActive, 750, 200, 300, 150);
        }
        batch.end(); //ending of rendering

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}