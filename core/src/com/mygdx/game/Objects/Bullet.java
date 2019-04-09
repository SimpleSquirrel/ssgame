package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.MyGame.PPM;

public class Bullet extends Sprite{
    public Body bulletBody;
    private Texture textureBullet;
    private Sprite spriteBullet;
    public World world;
    public Bullet(World world, float x, float y, float check){
        textureBullet = new Texture("Bullets/Bullet.png");
        spriteBullet = new Sprite(textureBullet);
        setBounds(0, 0, 32/PPM, 64/PPM);
        this.world = world;
        createBullet(x, y, check);
    }
    public Sprite getSpriteBullet(float delta){
        Sprite sprite;
        sprite = spriteBullet;
        return sprite;
    }
    public void createBullet(float x, float y, float check){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x + check/PPM, y + (float) 9/PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;
        bulletBody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2/PPM,1/PPM);
        fdef.shape = shape;
        bulletBody.createFixture(fdef);
    }
    public void deleteBullet(){

    }
}