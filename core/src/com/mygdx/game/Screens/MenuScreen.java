package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level2.GameScreenLevel2;
import com.mygdx.game.MyGame;

public class MenuScreen implements Screen {

    MyGame game;    //MyGame constructor

    OrthographicCamera camera;

    public MenuScreen(MyGame game){ //constructor
        this.game = game;

        camera = new OrthographicCamera();

        camera.setToOrtho(true, 1600, 900); //setting sizes for camera
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1); //setting bg color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

        camera.update(); //idk
        game.batch.setProjectionMatrix(camera.combined); //idk

        game.batch.begin(); //start of rendering
            game.batch.draw(Assets.spriteMenuScreenBack, 0, 0); //drawing bg
            if(Gdx.input.getX() < 550 + 250 && Gdx.input.getX() > 550 && Gdx.input.getY() < 50 + 150 && Gdx.input.getY() > 50) { //setting bounds of NewGameButton
                game.batch.draw(Assets.spriteNewGameButtonActive, 550, 50, 500, 150); //Drawing Active NewGameButton
                if (Gdx.input.isTouched()) { //creating an event
                    if(game.preferences.getInteger("level") != 1 || game.preferences.getInteger("location") != 1){
                        game.setScreen(new NewGameScreen(game));
                        this.dispose();
                    }
                    else {
                        game.setScreen(new GameScreenLevel1(game)); //changing screen
                        this.dispose();
                    }
                }
            }
            else{
                game.batch.draw(Assets.spriteNewGameButtonInactive, 550, 50, 500, 150);
            }
            if(Gdx.input.getX() < 550 + 250 && Gdx.input.getX() > 550 && Gdx.input.getY() < 250 + 150 && Gdx.input.getY() > 250) {
                game.batch.draw(Assets.spriteContinueGameButtonActive, 550, 250, 500, 150);
                if (Gdx.input.isTouched()) {
                    switch (game.preferences.getInteger("level")){
                        case 1:
                            switch (game.preferences.getInteger("location")){
                                case 1:
                                    game.setScreen(new GameScreenLevel1(game));
                                    this.dispose();
                                    break;
                                case 2:
                                    game.setScreen(new GameScreenLevel2(game));
                                    this.dispose();
                                    break;
                                default:
                                    break;
                            }
                        default:
                            break;
                    }
                }
            }
            else{
                game.batch.draw(Assets.spriteContinueGameButtonInactive, 550, 250, 500, 150);
            }
            if(Gdx.input.getX() < 550 + 250 && Gdx.input.getX() > 550 && Gdx.input.getY() < 450 + 150 && Gdx.input.getY() > 450) {

            game.batch.draw(Assets.spritePreferencesButtonActive, 550, 450, 500, 150);
                if (Gdx.input.isTouched()) {
                    this.dispose();
                    game.setScreen(new PreferencesScreen(game));
                }
            }
            else {
                game.batch.draw(Assets.spritePreferencesButtonInactive, 550, 450, 500, 150);
            }
            if(Gdx.input.getX() < 550 + 250 && Gdx.input.getX() > 550 && Gdx.input.getY() < 650 + 150 && Gdx.input.getY() > 650) {
            game.batch.draw(Assets.spriteExitButtonActive, 550, 650, 500, 150);
                if (Gdx.input.isTouched()) {
                    Gdx.app.exit();
                }
            }
            else{
                game.batch.draw(Assets.spriteExitButtonInactive, 550, 650, 500, 150);
            }
        game.batch.end(); //ending of rendering
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
