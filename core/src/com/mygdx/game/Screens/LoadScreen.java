package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemies.Biter;
import com.mygdx.game.Enemies.Cannon;
import com.mygdx.game.Enemies.DefendedCannon;
import com.mygdx.game.Enemies.VerticalCannon;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level2.GameScreenLevel2;
import com.mygdx.game.MyGame;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Player.Player;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

import static com.mygdx.game.MyGame.*;
import static com.mygdx.game.MyGame.verticalCannons;


public class LoadScreen implements Screen
{
    MyGame game;
    private float timer;
    SpriteBatch batch;
    private AnimatedSprite animatedSprite;
    Texture back = Assets.textureLoadBack;
    public LoadScreen(MyGame game){
        this.game = game;
        batch = new SpriteBatch();
    }

    public void update(float delta){
        for (Cannon cannon:cannons) {
            cannon.update(delta);
            if(cannon.cannonBullets.isEmpty() && cannon.isDestroyed()){
                cannons.removeValue(cannon, true);
            }
        }
        for (DefendedCannon defendedCannon:defendedCannons){
            defendedCannon.update(delta);
            if(defendedCannon.defendedCannonBullets.isEmpty() && defendedCannon.isDestroyed()){
                defendedCannons.removeValue(defendedCannon, true);
            }
        }
        for (VerticalCannon verticalCannon:verticalCannons){
            verticalCannon.update(delta);
            if(verticalCannon.verticalCannonBullets.isEmpty() && verticalCannon.isDestroyed()){
                verticalCannons.removeValue(verticalCannon, true);
            }
        }
        for (Biter biter:biters){
            biter.update(delta, 0);
            if(biter.isDestroyed()){
                biters.removeValue(biter, true);
            }
        }
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
        update(delta);
        timer+=delta;
        if(timer > 2f){
            this.dispose();
            switch (game.preferences.getInteger("level")){
                case 1:
                    switch (game.preferences.getInteger("location")){
                        case 1:
                            game.setScreen(new GameScreenLevel1(game));
                            break;
                        case 2:
                            game.setScreen(new GameScreenLevel2(game));
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                case 2:
                    break;
            }
        }
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
