package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import static com.mygdx.game.MyGame.*;

public class Bullet extends Sprite {
    public Body bulletBody;
    private Texture textureBullet;
    private Sprite spriteBullet;
    public World world;
    private boolean setToDelete;
    public boolean deleted;

    public Bullet(World world, float x, float y, float checkX, float checkY) {
        textureBullet = new Texture("Bullets/Bullet.png");
        spriteBullet = new Sprite(textureBullet);
        setBounds(getX(), getY(), 4 / PPM, 4 / PPM);
        this.world = world;
        setToDelete = false;
        deleted = false;
        createBullet(x, y, checkX, checkY);
    }
    public void update(){
        if(bulletBody.getLinearVelocity().x == 0){
            deleteBullet();
        }
        if(setToDelete && !deleted){
            world.destroyBody(bulletBody);
            deleted = true;
        }
        else if(!deleted) {
                setPosition(bulletBody.getPosition().x - 2 / PPM, bulletBody.getPosition().y - 2 / PPM);
                setRegion(spriteBullet);
        }
    }

    public void createBullet(float x, float y, float checkX, float checkY) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x + checkX, y + checkY);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bulletBody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2 / PPM, 1 / PPM);
        fdef.filter.categoryBits = BULLET_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT | ENEMY_BIT | SHIELD_BIT;
        fdef.shape = shape;
        bulletBody.createFixture(fdef).setUserData(this);
        bulletBody.setGravityScale(0);
    }
    public void deleteBullet(){
        setToDelete = true;
    }
    public boolean isDestroyed(){
        return deleted;
    }
}