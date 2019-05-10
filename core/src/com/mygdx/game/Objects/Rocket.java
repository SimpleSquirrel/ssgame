package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.MyGame.*;
import static com.mygdx.game.MyGame.PIE_BIT;

public class Rocket extends Sprite {
    public Body rocketBody;
    private Texture textureRocket;
    public Sprite spriteRocket;
    public World world;
    private boolean setToDelete;
    public boolean deleted;
    public static boolean flipRocket;
    public Rocket(World world, float x, float y, float checkX, float checkY) {
        textureRocket = new Texture("Objects/Rocket.png");
        spriteRocket = new Sprite(textureRocket);
        setBounds(getX(), getY(), 60 / PPM, 50 / PPM);
        this.world = world;
        setToDelete = false;
        deleted = false;
        flipRocket = false;
        createRocket(x, y, checkX, checkY);
    }
    public void update(){
        if(rocketBody.getPosition().y < -10/PPM || rocketBody.getPosition().y > 1000/PPM || rocketBody.getPosition().x < 0 || rocketBody.getPosition().x > 1600/PPM){
            setToDelete = true;
        }
        if(setToDelete && !deleted){
            world.destroyBody(rocketBody);
            deleted = true;
        }
        else if(!deleted) {
            setPosition(rocketBody.getPosition().x - 2 / PPM, rocketBody.getPosition().y - 2 / PPM);
            setRegion(spriteRocket);
        }
    }

    public void createRocket(float x, float y, float checkX, float checkY) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x + checkX, y + checkY);
        bdef.type = BodyDef.BodyType.DynamicBody;
        rocketBody = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(20 / PPM, 6 / PPM);
            fdef.filter.categoryBits = ROCKET_BIT;
            fdef.filter.maskBits = PLAYER_BIT | PIE_BIT | SWORD_BIT;
            fdef.shape = shape;
            rocketBody.createFixture(fdef).setUserData(this);
            rocketBody.setGravityScale(0);
    }
    public void deleteBullet(){
        setToDelete = true;
    }
    public boolean isDestroyed(){
        return deleted;
    }
    public void attackPie(){
        rocketBody.setLinearVelocity(8, 0);
        flipRocket = true;
    }
}
