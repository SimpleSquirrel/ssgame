package com.mygdx.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;


import static com.mygdx.game.MyGame.PPM;
public class Bullet implements ContactListener {
    public final static int BULLET_SPEED = 10;


     float x, y;

    public boolean remove = false;
    private static Texture bullet;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;

        if (bullet == null) {
            bullet = new Texture("Bullets/Bullet.png");
        }
    }

    public void update(float deltatime) {
        x += BULLET_SPEED * deltatime;
        if (x >= Gdx.graphics.getWidth() ) {
        remove=true;
        }
    }
    public void render(SpriteBatch batch){
        batch.draw(bullet,x,y,10/PPM ,5/PPM );

    }


    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}