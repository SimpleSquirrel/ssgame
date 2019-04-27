package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.MyGame;

public class PreferencesScreen implements Screen {
    MyGame game;    //MyGame constructor
    OrthographicCamera camera; //Creating a camera
    SpriteBatch batch;

    double testX;
    double testY;
    double freeze=0.2f;
    double timer=0;
    public PreferencesScreen(MyGame game){
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
        timer+=delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            testX=Gdx.input.getX();
            testY=Gdx.input.getY();
            System.out.print(testX +"  "+ testY);
            System.out.println();
        }
        Gdx.gl.glClearColor(0, 0, 0.2f, 1); //setting bg color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

        camera.update(); //idk
        game.batch.setProjectionMatrix(camera.combined); //idk

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
        }
        camera.update();
        game.batch.begin();
        game.batch.draw(Assets.spritePreferencesScreenBack, 0, 0,1600,900);
        game.batch.draw(Assets.spriteBigSkalaNiz, 0, 0);
        game.batch.draw(Assets.spriteBigSkalaVerh, 0, 0);
        game.batch.draw(Assets.spriteGrom, 0, 0);
        game.batch.draw(Assets.spriteGuchnistMuz, 0, 0);
        game.batch.draw(Assets.spriteGuchnistZv, 0, 0);
        game.batch.draw(Assets.spriteNadpisMusica, 0, 0);
        game.batch.draw(Assets.spriteNadpisZvuki, 0, 0);
        game.batch.draw(Assets.spriteNoteList, 0, 0);
        game.batch.draw(Assets.spritePoleProtsVerh, 0, 0);
        game.batch.draw(Assets.spritePoleProtsNiz, 0, 0);
        game.batch.draw(Assets.spriteSkalaVerh, 0, 0);
        game.batch.draw(Assets.spriteSkalaNiz, 0, 0);
        game.batch.draw(Assets.spriteTurnDownInactive, 657, Gdx.graphics.getHeight()-240);
        game.batch.draw(Assets.spriteTurnUpInactive, 767, Gdx.graphics.getHeight()-240);
        game.batch.draw(Assets.spriteTurnDownInactive, 657, Gdx.graphics.getHeight()-655);
        game.batch.draw(Assets.spriteTurnUpInactive, 767, Gdx.graphics.getHeight()-655);
        if(Gdx.input.getX() <645  && Gdx.input.getX() > 561 && Gdx.input.getY() >258   && Gdx.input.getY() <316 ) { //setting bounds of NewGameButton
            game.batch.draw(Assets.spriteTurnDownActive1,657 ,Gdx.graphics.getHeight()-240 ); //Drawing Active
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) { //creating an event
                if(timer>freeze) {
                    MyGame.MUZIC--;
                    timer=0;

                }
            }
        }
        if(Gdx.input.getX() <739  && Gdx.input.getX() > 655 && Gdx.input.getY() >257   && Gdx.input.getY() <316 ) { //setting bounds of NewGameButton
            game.batch.draw(Assets.spriteTurnUpActive1,767 ,Gdx.graphics.getHeight()-240 ); //Drawing Active
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) { //creating an event
                if(timer>freeze) {
                    MyGame.MUZIC++;
                    timer=0;
                }
            }
        }
        if(MyGame.MUZIC<0){
            MyGame.MUZIC=0;
        }
        if(MyGame.MUZIC>11){
            MyGame.MUZIC=11;
        }
        if(MyGame.SOUND<0){
            MyGame.SOUND=0;
        }
        if(MyGame.SOUND>11){
            MyGame.SOUND=11;
        }
        switch (MyGame.MUZIC){
            case 0:
                game.batch.draw(Assets.spriteShesternyaVerh, 0, 0);
                break;
            case 1:
                game.batch.draw(Assets.spriteShesternyaVerh, 66, 0);
                break;
            case 2:
                game.batch.draw(Assets.spriteShesternyaVerh, 132, 0);
                break;
            case 3:
                game.batch.draw(Assets.spriteShesternyaVerh, 198, 0);
                break;
            case 4:
                game.batch.draw(Assets.spriteShesternyaVerh, 264, 0);
                break;
            case 5:
                game.batch.draw(Assets.spriteShesternyaVerh, 330, 0);
                break;
            case 6:
                game.batch.draw(Assets.spriteShesternyaVerh, 396, 0);
                break;
            case 7:
                game.batch.draw(Assets.spriteShesternyaVerh, 462, 0);
                break;
            case 8:
                game.batch.draw(Assets.spriteShesternyaVerh, 528, 0);
                break;
            case 9:
                game.batch.draw(Assets.spriteShesternyaVerh, 594, 0);
                break;
            case 10:
                game.batch.draw(Assets.spriteShesternyaVerh, 700, 0);
                break;
            case 11:
                game.batch.draw(Assets.spriteShesternyaVerh, 760, 0);
                break;
        }
        if(Gdx.input.getX() <645  && Gdx.input.getX() > 561 && Gdx.input.getY() >611   && Gdx.input.getY() <672 ) { //setting bounds of NewGameButton
            game.batch.draw(Assets.spriteTurnDownActive2, 657, Gdx.graphics.getHeight()-655);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) { //creating an event
                if(timer>freeze) {
                    MyGame.SOUND--;
                    timer=0;

                }
            }
        }
        if(Gdx.input.getX() <739  && Gdx.input.getX() > 655 && Gdx.input.getY() >611   && Gdx.input.getY() <670 ) { //setting bounds of NewGameButton
            game.batch.draw(Assets.spriteTurnUpActive2, 767, Gdx.graphics.getHeight()-655); //Drawing Active
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) { //creating an event
                if(timer>freeze) {
                    MyGame.SOUND++;
                    timer=0;
                }
            }
        }
        switch (MyGame.SOUND){
            case 0:
                game.batch.draw(Assets.spriteShesternyaNiz, -10, 0);
                break;
            case 1:
                game.batch.draw(Assets.spriteShesternyaNiz, 66, 0);
                break;
            case 2:
                game.batch.draw(Assets.spriteShesternyaNiz, 132, 0);
                break;
            case 3:
                game.batch.draw(Assets.spriteShesternyaNiz, 198, 0);
                break;
            case 4:
                game.batch.draw(Assets.spriteShesternyaNiz, 264, 0);
                break;
            case 5:
                game.batch.draw(Assets.spriteShesternyaNiz, 330, 0);
                break;
            case 6:
                game.batch.draw(Assets.spriteShesternyaNiz, 396, 0);
                break;
            case 7:
                game.batch.draw(Assets.spriteShesternyaNiz, 462, 0);
                break;
            case 8:
                game.batch.draw(Assets.spriteShesternyaNiz, 528, 0);
                break;
            case 9:
                game.batch.draw(Assets.spriteShesternyaNiz, 594, 0);
                break;
            case 10:
                game.batch.draw(Assets.spriteShesternyaNiz, 700, 0);
                break;
            case 11:
                game.batch.draw(Assets.spriteShesternyaNiz, 760, 0);
                break;
        }
        game.batch.end();
    }

    void generalUpdate(){

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
