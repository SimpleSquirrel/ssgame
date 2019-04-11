package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level1.WorldContactListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.MyGame.*;
import static com.mygdx.game.Player.Player.runningRight;

public class Bullet extends Sprite {
    public Body bulletBody;
    private Texture textureBullet;
    private Sprite spriteBullet;
    public World world;
    private boolean setToDelete;
    public boolean deleted;
    private float stateTime;

    public Bullet(World world, float x, float y, float check) {
        textureBullet = new Texture("Bullets/Bullet.png");
        spriteBullet = new Sprite(textureBullet);
        setBounds(getX(), getY(), 4 / PPM, 2 / PPM);
        this.world = world;
        setToDelete = false;
        deleted = false;
        createBullet(x, y, check);
    }
    public void update(float delta){
        stateTime += delta;
        if(setToDelete && !deleted){
            world.destroyBody(bulletBody);
            stateTime = 0;
            GameScreenLevel1.bulletCounter--;
            deleted = true;
        }
        else if(!deleted) {
            setPosition(bulletBody.getPosition().x - 2 / PPM, bulletBody.getPosition().y - 2 / PPM);
            setRegion(spriteBullet);
        }
    }

    public void draw(Batch batch){
        if(stateTime == 0 || !deleted){
            super.draw(batch);
        }
    }

    public void createBullet(float x, float y, float check) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x + check / PPM, y + (float) 2/ PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bulletBody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2 / PPM, 1 / PPM);
        fdef.filter.categoryBits = BULLET_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT | ENEMY_BIT;
        fdef.shape = shape;
        bulletBody.createFixture(fdef).setUserData(this);
        bulletBody.setGravityScale(0);
    }
    public void deleteBullet(){
        setToDelete = true;
    }
}