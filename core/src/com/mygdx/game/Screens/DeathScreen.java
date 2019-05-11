package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.MyGame;

public class DeathScreen implements Screen {
    MyGame game;    //MyGame constructor
    OrthographicCamera camera; //Creating a camera
    SpriteBatch batch;
    private int testX,testY;
    private boolean size;
     public DeathScreen(MyGame game){ //constructor
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
        Gdx.gl.glClearColor(0, 0, 0, 1); //setting bg color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

        camera.update(); //idk
        batch.setProjectionMatrix(camera.combined); //idk

        batch.begin(); //start of rendering
        batch.draw(Assets.spriteDeathScreenBack, 0, 0, 1600,900); //drawing bg
        batch.draw(Assets.spriteDeathScreenDaInActive, 300, 200, 400, 200); //Drawing Active NewGameButton
        batch.draw(Assets.spriteDeathScreenDaInActive,750,200,400,200);
        if (Gdx.graphics.getHeight()>800){
            size=true;
        }
        else {
            size=false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            testX=Gdx.input.getX();
            testY=Gdx.input.getY();
            System.out.print(testX +"  "+(Gdx.graphics.getHeight()-testY));
            System.out.println();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            this.dispose();
            game.setScreen(new LoadScreen(game));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.dispose();
            game.setScreen(new MenuScreen(game)); //changing screen
        }
        batch.draw(Assets.textureDeathScreenYouDied, 500, 700);
        batch.draw(Assets.textureDeathScreenOneMore, 500, 600);//Drawing Active
        if(!size) {
            if (Gdx.input.getX() < 568 && Gdx.input.getX() > 287 && Gdx.input.getY() >Gdx.graphics.getHeight()- 309 && Gdx.input.getY() <Gdx.graphics.getHeight()- 179) { //setting bounds of NewGameButton
                batch.draw(Assets.spriteDeathScreenDaActive, 300, 200, 400, 200); //Drawing Active
                batch.draw(Assets.textureDeathScreenTextDa, 300, 200, 400, 200); //Drawing Active
                if (Gdx.input.isTouched()) { //creating an event
                    this.dispose();
                    game.setScreen(new LoadScreen(game)); //changing screen
                }
            }   //changing screen
            else {
                batch.draw(Assets.spriteDeathScreenDaInActive, 300, 200, 400, 200);
                batch.draw(Assets.textureDeathScreenTextDa, 400, 230, 200, 150); //Drawing Active
            }
            if (Gdx.input.getX() < 936 && Gdx.input.getX() > 672 && Gdx.input.getY() >Gdx.graphics.getHeight()-307 && Gdx.input.getY() <Gdx.graphics.getHeight()- 194) { //setting bounds of NewGameButton
                batch.draw(Assets.spriteDeathScreenDaActive, 750, 200, 400, 200); //Drawing Active
                batch.draw(Assets.spriteDeathScreenTosheDa, 800, 220, 300, 150);
                if (Gdx.input.isTouched()) { //creating an event
                    this.dispose();
                    game.setScreen(new LoadScreen(game)); //changing screen
                }
            }
            else {
                batch.draw(Assets.spriteDeathScreenDaInActive, 750, 200, 400, 200);
                batch.draw(Assets.textureDeathScreenTextNo, 850, 220, 200, 150); //Drawing Active
            }
        }
        if(size) {
            if (Gdx.input.getX() < 568 && Gdx.input.getX() > 287 && Gdx.input.getY() >Gdx.graphics.getHeight()- 309 && Gdx.input.getY() <Gdx.graphics.getHeight()- 179) { //setting bounds of NewGameButton
                batch.draw(Assets.spriteDeathScreenDaActive, 300, 200, 400, 200); //Drawing Active
                batch.draw(Assets.textureDeathScreenTextDa, 300, 200, 400, 200); //Drawing Active
                if (Gdx.input.isTouched()) { //creating an event
                    this.dispose();
                    game.setScreen(new LoadScreen(game)); //changing screen
                }
            }   //changing screen
            else {
                batch.draw(Assets.spriteDeathScreenDaInActive, 300, 200, 400, 200);
                batch.draw(Assets.textureDeathScreenTextDa, 400, 230, 200, 150); //Drawing Active
            }
            if (Gdx.input.getX() < 936 && Gdx.input.getX() > 672 && Gdx.input.getY() >Gdx.graphics.getHeight()-307 && Gdx.input.getY() <Gdx.graphics.getHeight()- 194) { //setting bounds of NewGameButton
                batch.draw(Assets.spriteDeathScreenDaActive, 750, 200, 400, 200); //Drawing Active
                batch.draw(Assets.spriteDeathScreenTosheDa, 800, 220, 300, 150);
                if (Gdx.input.isTouched()) { //creating an event
                    this.dispose();
                    game.setScreen(new LoadScreen(game)); //changing screen
                }
            }
            else {
                batch.draw(Assets.spriteDeathScreenDaInActive, 750, 200, 400, 200);
                batch.draw(Assets.textureDeathScreenTextNo, 850, 220, 200, 150); //Drawing Active
            }
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
