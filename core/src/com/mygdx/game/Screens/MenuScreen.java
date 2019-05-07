package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level2.GameScreenLevel2;
import com.mygdx.game.MyGame;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

public class MenuScreen implements Screen {

    MyGame game;    //MyGame constructor

    OrthographicCamera camera;
    private AnimatedSprite AnimatedSesternya1;
    private AnimatedSprite AnimatedSesternya2;
    private AnimatedSprite AnimatedSesternya3;
    private AnimatedSprite AnimatedSesternya4;
    private  int testX;
    private  int testY;


    public MenuScreen(MyGame game){ //constructor
        this.game = game;

        camera = new OrthographicCamera();

        camera.setToOrtho(true, 1600, 900); //setting sizes for camera
    }

    @Override
    public void show() {
        Animation animation1 = new Animation(1/5f, new TextureRegion(Assets.textureMenuScreenSestesnya));
        animation1.setPlayMode(Animation.PlayMode.LOOP);

        Animation animation2 = new Animation(1/5f, new TextureRegion(Assets.textureMenuScreenSestesnya));
        animation2.setPlayMode(Animation.PlayMode.LOOP);

        Animation animation3 = new Animation(1/5f, new TextureRegion(Assets.textureMenuScreenSestesnya));
        animation3.setPlayMode(Animation.PlayMode.LOOP);

        Animation animation4 = new Animation(1/5f, new TextureRegion(Assets.textureMenuScreenSestesnya));
        animation4.setPlayMode(Animation.PlayMode.LOOP);

        AnimatedSesternya1= new AnimatedSprite(animation1);
        AnimatedSesternya1.setAutoUpdate(true);
        AnimatedSesternya1.setPosition(100,200);

        AnimatedSesternya2= new AnimatedSprite(animation2);
        AnimatedSesternya2.setAutoUpdate(true);
        AnimatedSesternya2.setPosition(100,-220);

        AnimatedSesternya3= new AnimatedSprite(animation3);
        AnimatedSesternya3.setAutoUpdate(true);
        AnimatedSesternya3.setPosition(520,200);

        AnimatedSesternya4= new AnimatedSprite(animation4);
        AnimatedSesternya4.setAutoUpdate(true);
        AnimatedSesternya4.setPosition(520,-220);



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
        game.batch.setProjectionMatrix(camera.combined); //idk

        game.batch.begin(); //start of rendering
            game.batch.draw(Assets.spriteMenuScreenBack, 0, 0); //drawing bg
            AnimatedSesternya1.update();
            AnimatedSesternya1.draw(game.batch);

            AnimatedSesternya2.update();
            AnimatedSesternya2.draw(game.batch);

            AnimatedSesternya3.update();
            AnimatedSesternya3.draw(game.batch);

            AnimatedSesternya4.update();
            AnimatedSesternya4.draw(game.batch);
        if(Gdx.input.getX() < 839 && Gdx.input.getX() > 674 && Gdx.input.getY() < 320 && Gdx.input.getY() > 82) {
            AnimatedSesternya2.rotate(-2f);

                if (Gdx.input.isTouched()) { //creating an event
                    if(game.preferences.getInteger("level") != 1 || game.preferences.getInteger("location") != 1){
                        game.preferences.putBoolean("WoodenChest1IsOpened", false);
                        game.preferences.putBoolean("WoodenChest2IsOpened", false);
                        game.preferences.putBoolean("WoodenChest3IsOpened", false);
                        game.preferences.putBoolean("PrestigeChest1IsOpened", false);
                        game.preferences.putBoolean("IronChest1IsOpened", false);
                        game.preferences.putBoolean("IronChest2IsOpened", false);
                        game.preferences.putBoolean("IronChest3IsOpened", false);
                        game.preferences.putBoolean("PrestigeChest2IsOpened", false);
                        game.preferences.flush();
                        game.setScreen(new NewGameScreen(game));
                        this.dispose();
                    }
                    else {
                        game.preferences.putBoolean("WoodenChest1IsOpened", false);
                        game.preferences.putBoolean("WoodenChest2IsOpened", false);
                        game.preferences.putBoolean("WoodenChest3IsOpened", false);
                        game.preferences.putBoolean("PrestigeChest1IsOpened", false);
                        game.preferences.putBoolean("IronChest1IsOpened", false);
                        game.preferences.putBoolean("IronChest2IsOpened", false);
                        game.preferences.putBoolean("IronChest3IsOpened", false);
                        game.preferences.putBoolean("PrestigeChest2IsOpened", false);
                        game.preferences.flush();
                        game.setScreen(new GameScreenLevel1(game)); //changing screen
                        this.dispose();
                    }
                }
            }
            if(Gdx.input.getX() < 1250 && Gdx.input.getX() > 1023 && Gdx.input.getY() < 330 && Gdx.input.getY() > 80) {
                AnimatedSesternya4.rotate(-2f);
                if (Gdx.input.isTouched()) {
                        this.dispose();
                        game.setScreen(new LoadScreen(game));
                    }
                }


                if(Gdx.input.getX() < 886 && Gdx.input.getX() > 659 && Gdx.input.getY() < 689 && Gdx.input.getY() > 425) {//setting bounds of NewGameButton
                    AnimatedSesternya1.rotate(-2f);
                if (Gdx.input.isTouched()) {
                    this.dispose();
                    game.setScreen(new PreferencesScreen(game));
                }
            }


                if(Gdx.input.getX() < 1227 && Gdx.input.getX() > 1020 && Gdx.input.getY() < 689 && Gdx.input.getY() > 425) {
                    AnimatedSesternya3.rotate(-2f);
                if (Gdx.input.isTouched()) {
                    Gdx.app.exit();
                }
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
