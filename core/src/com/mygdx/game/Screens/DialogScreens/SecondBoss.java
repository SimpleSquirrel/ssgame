package com.mygdx.game.Screens.DialogScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Levels.Level10.GameScreenLevel10;
import com.mygdx.game.MyGame;
import com.mygdx.game.Screens.LoadScreen;

import static com.mygdx.game.Screens.DialogScreens.FirstMeet.dialogmusic;

public class SecondBoss implements Screen {

    MyGame game;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture texturePie1;
    private Texture texturePie2;
    private Texture texturePie3;
    private Texture texturePie4;
    private Texture texturePie5;
    private Texture texturePie6;
    private Texture texturePie7;
    private Sprite spritePie1;
    private Sprite spritePie2;
    private Sprite spritePie3;
    private Sprite spritePie4;
    private Sprite spritePie5;
    private Sprite spritePie6;
    private Sprite spritePie7;
    private int frame;
    public SecondBoss(MyGame game){
        this.game = game;
        dialogmusic.play(0.05f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);
        viewport = new FitViewport(1600, 900, camera);

        texturePie1 = new Texture("Dialog/PieFrame1.png");
        texturePie1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePie1 = new Sprite(texturePie1);

        texturePie2 = new Texture("Dialog/PieFrame2.png");
        texturePie2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePie2 = new Sprite(texturePie2);

        texturePie3 = new Texture("Dialog/PieFrame3.png");
        texturePie3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePie3 = new Sprite(texturePie3);

        texturePie4 = new Texture("Dialog/PieFrame4.png");
        texturePie4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePie4 = new Sprite(texturePie4);

        texturePie5 = new Texture("Dialog/PieFrame5.png");
        texturePie5.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePie5 = new Sprite(texturePie5);

        texturePie6 = new Texture("Dialog/PieFrame6.png");
        texturePie6.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePie6 = new Sprite(texturePie6);

        texturePie7 = new Texture("Dialog/PieFrame7.JPG");
        texturePie7.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePie7 = new Sprite(texturePie7);
        frame = 0;
    }

    private void iter(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))frame++;
        if(frame > 6){
            game.setScreen(new LoadScreen(game));
        }
    }
    private void showFrame(int cadr, Sprite sprite){
        if(cadr <= frame){
            game.batch.draw(sprite, 0, 0, 1600, 900);
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        iter();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        showFrame(0, spritePie1);
        showFrame(1, spritePie2);
        showFrame(2, spritePie3);
        showFrame(3, spritePie4);
        showFrame(4, spritePie5);
        showFrame(5, spritePie6);
        showFrame(6, spritePie7);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
