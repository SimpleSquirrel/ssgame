package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Levels.Level10.GameScreenLevel10;

import static com.mygdx.game.MyGame.*;

public class Cupcake extends Enemy {
    private TextureAtlas atlas;
    private TextureAtlas atlas1;
    private Animation BTOOM;
    private Sprite btoom1;
    private Sprite btoom2;
    private Sprite btoom3;
    private Sprite cupcake1;
    private Sprite cupcake2;
    private Sprite cupcake01;
    private Sprite cupcake02;
    private Animation cupcakeAnimation;
    private Animation cupcakeAnimation1;
    private boolean setToDestroy;
    private boolean destroyed;
    private float positionX;
    private float timer;
    public Cupcake(World world, float x, float y, boolean flip) {
        super(world, x, y, flip);
        HP = 10;
        setToDestroy = false;
        destroyed = false;
        atlas1 = new TextureAtlas("Animations/Btoom.txt");
        btoom1 = atlas1.createSprite("Btoom1");
        btoom2 = atlas1.createSprite("Btoom2");
        btoom3 = atlas1.createSprite("Btoom3");
        atlas = new TextureAtlas("Enemies/Cupcake.txt");
        cupcake1 = atlas.createSprite("Cupcake2");
        cupcake2 = atlas.createSprite("Cupcake1");
        cupcake01 = atlas.createSprite("Cupcake2");
        cupcake02 = atlas.createSprite("Cupcake1");
        cupcake01.flip(true, false);
        cupcake02.flip(true, false);
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(cupcake1);
        frames.add(cupcake2);
        cupcakeAnimation = new Animation(0.1f, frames);
        frames.clear();
        frames.add(cupcake01);
        frames.add(cupcake02);
        cupcakeAnimation1 = new Animation(0.1f, frames);
        frames.clear();
        frames.add(btoom1);
        frames.add(btoom2);
        frames.add(btoom3);
        BTOOM = new Animation(0.05f, frames);
        frames.clear();
    }

    public void update(float delta, float position){
        timer += delta;
        positionX = position;
        if(setToDestroy && !destroyed){
            b2body.setLinearVelocity(0, 0);
            world.destroyBody(b2body);
            GameScreenLevel10.destroyTimer = 0;
            destroyed = true;
        }
        else if(!destroyed) {
            if (b2body.getLinearVelocity().y == 0) {
                if (position > b2body.getPosition().x) {
                    b2body.setLinearVelocity(2, 0);
                } else if (position < b2body.getPosition().x) {
                    b2body.setLinearVelocity(-2, 0);
                }
            } else {
                b2body.setLinearVelocity(0, -10);
            }
        }
    }

    public Sprite getSprite(){
        Sprite sprite;
        if(!destroyed) {
            if (b2body.getLinearVelocity().y != 0) {
                sprite = cupcake2;
            } else if (b2body.getLinearVelocity().x > 0) {
                sprite = (Sprite) cupcakeAnimation1.getKeyFrame(timer, true);
            } else {
                sprite = (Sprite) cupcakeAnimation.getKeyFrame(timer, true);
            }
        }
        else {
            sprite = (Sprite)BTOOM.getKeyFrame(timer, false);
        }
        return sprite;
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX() + 20/PPM, getY() + 16/PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(24/PPM, 24/PPM);
        fixtureDef.filter.categoryBits = ENEMY_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | GROUND_BIT | CHEST_BIT | PORTAL_BIT | SWORD_BIT | SHIELD_BIT;
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);

        /*PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(24/PPM, 24/PPM);
        fixtureDef.filter.categoryBits = WALKING_ENEMY_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | GROUND_BIT | CHEST_BIT | PORTAL_BIT | SWORD_BIT | SHIELD_BIT;
        fixtureDef.shape = shape1;
        b2body.createFixture(fixtureDef).setUserData(this);*/
    }

    @Override
    public void bulletHit() {
        HP -= 20;
        if(HP <= 0){
            deleted();
        }
    }

    @Override
    public void swordHit() {
        HP -= 20;
        if(HP <= 0){
            deleted();
        }
    }

    @Override
    public void fire() {

    }

    @Override
    public void deleted() {
        setToDestroy = true;
    }

    @Override
    public void reverseVelocity(boolean x, boolean y) {
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void playSound() {

    }
}
