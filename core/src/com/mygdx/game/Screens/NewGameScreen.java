package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Graphics.Labels;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.MyGame;
import com.mygdx.game.Screens.DialogScreens.FirstMeet;

public class NewGameScreen implements Screen {
    MyGame game;    //MyGame constructor
    OrthographicCamera camera; //Creating a camera

    SpriteBatch batch;
    Labels labels;
    private int testX,testY;
    public NewGameScreen(MyGame game) { //constructor
        this.game = game;
        labels=new Labels(game);
        camera = new OrthographicCamera(); //Initialising camera
        camera.setToOrtho(false, 1600, 900); //setting sizes for camera
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            testX=Gdx.input.getX();
            testY=Gdx.input.getY();
            System.out.print(testX +"  "+(Gdx.graphics.getHeight()-testY));
            System.out.println();
        }
        Gdx.gl.glClearColor(0, 0, 0.2f, 1); //setting bg color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

        camera.update(); //idk
        batch.setProjectionMatrix(camera.combined); //idk

        batch.begin(); //start of rendering
        batch.draw(Assets.spriteNewGameScreenBack, 0, 0);

        if(Gdx.input.getX() <643 && Gdx.input.getX() > 377 && Gdx.input.getY() >Gdx.graphics.getHeight()-562 && Gdx.input.getY() < Gdx.graphics.getHeight()-458) { //setting bounds of NewGameButton
            batch.draw(Assets.spriteDeathScreenDaActive, 400, 500, 400, 200); //Drawing Active
            batch.draw(Assets.textureDeathScreenTextDa, 450, 525, 300, 150); //Drawing Active
            if (Gdx.input.isTouched()) { //creating an event
                this.dispose();
                game.preferences.putInteger("level", 1);
                game.preferences.putInteger("location", 1);
                game.preferences.flush();
                game.setScreen(new FirstMeet(game)); //changing screen
            }
        }
        else{
            batch.draw(Assets.spriteDeathScreenDaInActive, 400, 500, 400, 200);
            batch.draw(Assets.textureDeathScreenTextDa, 500, 550, 200, 100); //Drawing Active
        }
        if(Gdx.input.getX() <1033 && Gdx.input.getX() > 763 && Gdx.input.getY() >Gdx.graphics.getHeight()-569   && Gdx.input.getY() < Gdx.graphics.getHeight()-458) { //setting bounds of NewGameButton
            batch.draw(Assets.spriteDeathScreenDaActive, 850, 500, 400, 200); //Drawing Active
            batch.draw(Assets.textureDeathScreenTextNo, 900, 525, 300, 150); //Drawing Active
            if (Gdx.input.isTouched()) { //creating an event
                this.dispose();
                game.setScreen(new MenuScreen(game)); //changing screen
            }
        }
        else{
            batch.draw(Assets.spriteDeathScreenDaInActive, 850, 500, 400, 200);
            batch.draw(Assets.textureDeathScreenTextNo, 950, 550, 200, 100); //Drawing Active
        }
        batch.end(); //ending of rendering
        Labels.drawLabel(delta,15);
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