package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class PreferencesScreen implements Screen {
    MyGame game;
    OrthographicCamera camera;

    int music = 10;
    int sound = 10;

    public PreferencesScreen(MyGame game){
        this.game = game;

        camera = new OrthographicCamera();

        camera.setToOrtho(true, 1600, 900);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0,0,0,0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
            game.batch.draw(Assets.spritePreferencesScreenBack, 0, 0);
            if(sound != 0) {
                game.batch.draw(Assets.spriteMusicButtonActive, 200, 250, 100, 100);
                if(Gdx.input.isTouched()){
                    game.batch.draw(Assets.spriteMusicScroller0, 600, 250, 800, 100);
                }
            }
            else {
                game.batch.draw(Assets.spriteMusicButtonInactive, 200, 250, 100, 100);
            }
            if(music != 0) {
                game.batch.draw(Assets.spriteMusicButtonActive, 200, 550, 100, 100);
                if(Gdx.input.isTouched()){
                    game.batch.draw(Assets.spriteMusicScroller0, 600, 550, 800, 100);
                }
            }
            else{
                game.batch.draw(Assets.spriteMusicButtonInactive, 200, 550, 100, 100);
            }
            if(sound >= 0) {
                game.batch.draw(Assets.spriteTurnDown, 600, 350);
                if(Gdx.input.isTouched() && sound != 0){
                    sound -= 1;
                }
            }
            if(sound <= 10) {
                game.batch.draw(Assets.spriteTurnUp, 680, 350);
                if(Gdx.input.isTouched() && sound != 10){
                    sound += 1;
                }
            }
            if(music >= 0) {
                game.batch.draw(Assets.spriteTurnDown, 600, 650);
                if(Gdx.input.isTouched() && music != 0){
                    music -= 1;
                }
            }
            if(music <= 10) {
                game.batch.draw(Assets.spriteTurnUp, 680, 650);
                if(Gdx.input.isTouched() && music != 10){
                    music += 1;
                }
            }
            if(sound == 10) {
                game.batch.draw(Assets.spriteMusicScroller100, 600, 250, 800, 100);
            }
            if(music == 10) {
                game.batch.draw(Assets.spriteMusicScroller100, 600, 550, 800, 100);
            }
            if(sound == 9) {
                game.batch.draw(Assets.spriteMusicScroller90, 600, 250, 800, 100);
            }
            if(music == 9) {
            game.batch.draw(Assets.spriteMusicScroller90, 600, 550, 800, 100);
            }
            if(sound == 8) {
                game.batch.draw(Assets.spriteMusicScroller80, 600, 250, 800, 100);
            }
            if(music == 8) {
                game.batch.draw(Assets.spriteMusicScroller80, 600, 550, 800, 100);
            }
            if(sound == 7) {
                game.batch.draw(Assets.spriteMusicScroller70, 600, 250, 800, 100);
            }
            if(music == 7) {
                game.batch.draw(Assets.spriteMusicScroller70, 600, 550, 800, 100);
            }
            if(sound == 6) {
                game.batch.draw(Assets.spriteMusicScroller60, 600, 250, 800, 100);
            }
            if(music == 6) {
                game.batch.draw(Assets.spriteMusicScroller60, 600, 550, 800, 100);
            }
            if(sound == 5) {
                game.batch.draw(Assets.spriteMusicScroller50, 600, 250, 800, 100);
            }
            if(music == 5) {
                game.batch.draw(Assets.spriteMusicScroller50, 600, 550, 800, 100);
            }
            if(sound == 4) {
                game.batch.draw(Assets.spriteMusicScroller40, 600, 250, 800, 100);
            }
            if(music == 4) {
                game.batch.draw(Assets.spriteMusicScroller40, 600, 550, 800, 100);
            }
            if(sound == 3) {
                game.batch.draw(Assets.spriteMusicScroller30, 600, 250, 800, 100);
            }
            if(music == 3) {
                game.batch.draw(Assets.spriteMusicScroller30, 600, 550, 800, 100);
            }
            if(sound == 2) {
                game.batch.draw(Assets.spriteMusicScroller20, 600, 250, 800, 100);
            }
            if(music == 2) {
                game.batch.draw(Assets.spriteMusicScroller20, 600, 550, 800, 100);
            }
            if(sound == 1) {
                game.batch.draw(Assets.spriteMusicScroller10, 600, 250, 800, 100);
            }
            if(music == 1) {
                game.batch.draw(Assets.spriteMusicScroller10, 600, 550, 800, 100);
            }
            if(sound == 0) {
                game.batch.draw(Assets.spriteMusicScroller0, 600, 250, 800, 100);
            }
            if(music == 0) {
                game.batch.draw(Assets.spriteMusicScroller0, 600, 550, 800, 100);
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
