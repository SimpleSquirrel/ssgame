package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGame;
import com.mygdx.game.Graphics.Assets;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;


public class LoadScreen implements Screen
{
    MyGame game;

    SpriteBatch batch;
    private AnimatedSprite animatedSprite;
    Texture back = Assets.textureLoadBack;
    public LoadScreen(MyGame game){
        this.game = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show()
    {

        batch = new SpriteBatch();
        Animation animation = new Animation(1/5f, new TextureRegion(Assets.loadAnimation));
        animation.setPlayMode(Animation.PlayMode.LOOP);

        animatedSprite = new AnimatedSprite(animation);
        animatedSprite.setAutoUpdate(true);
        animatedSprite.setPosition(550,200);

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(back, 0, 0);

        animatedSprite.update();
        animatedSprite.draw(batch);
        animatedSprite.rotate(-2f);

        batch.end();
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
        batch.dispose();
    }
}
