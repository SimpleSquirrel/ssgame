package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.MyGame.*;

public class VedmeDIO extends Enemy {

    private TextureAtlas atlasStand;
    private Sprite spriteBearStand1;
    private Sprite spriteBearStand2;
    private Animation animationBearStand;
    private TextureAtlas atlasFall;
    private Sprite spriteBearFall1;
    private Sprite spriteBearFall2;
    private Sprite spriteBearFall3;
    private Sprite spriteBearFall4;
    private Sprite spriteBearFall5;
    private Sprite spriteBearFall6;
    private Sprite spriteBearFall7;
    private Animation animationBearFall;
    private TextureAtlas atlasBearHit;
    private Sprite spriteBearHit1;
    private Sprite spriteBearHit2;
    private Sprite spriteBearHit3;
    private Sprite spriteBearHit4;
    private Animation animationBearHit;
    private boolean setToDestroy;
    private boolean destroyed;
    private float timer;

    public VedmeDIO(World world, float x, float y,boolean flip){
        super(world, x, y, flip);
        HP = 10000000;
        atlasStand = new TextureAtlas("Enemies/BearDio.txt");
        spriteBearStand1 = atlasStand.createSprite("Bear1");
        spriteBearStand2 = atlasStand.createSprite("Bear2");
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(spriteBearStand1);
        frames.add(spriteBearStand2);
        animationBearStand = new Animation(0.2f, frames);
        frames.clear();
        atlasFall = new TextureAtlas("Enemies/BearDioFall.txt");
        spriteBearFall1 = atlasFall.createSprite("BearFall1");
        spriteBearFall2 = atlasFall.createSprite("BearFall2");
        spriteBearFall3 = atlasFall.createSprite("BearFall3");
        spriteBearFall4 = atlasFall.createSprite("BearFall4");
        spriteBearFall5 = atlasFall.createSprite("BearFall5");
        spriteBearFall6 = atlasFall.createSprite("BearFall6");
        spriteBearFall7 = atlasFall.createSprite("BearFall7");
        frames.add(spriteBearFall1);
        frames.add(spriteBearFall2);
        frames.add(spriteBearFall3);
        frames.add(spriteBearFall4);
        frames.add(spriteBearFall5);
        frames.add(spriteBearFall6);
        frames.add(spriteBearFall7);
        animationBearFall = new Animation(0.2f, frames);
        frames.clear();
        setToDestroy = false;
        destroyed = false;
    }

    public void update(float delta){
        timer += delta;
        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            timer = 0;
            destroyed = true;
        }
    }

    public Sprite getSpriteStand(float delta){
        timer += delta;
        Sprite sprite;
        sprite = (Sprite) animationBearStand.getKeyFrame(timer, true);
        return sprite;
    }
    public Sprite getSpriteFall(float delta){
        timer += delta;
        Sprite sprite;
        sprite = (Sprite) animationBearFall.getKeyFrame(timer, false);
        return sprite;
    }
    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX() + 20/PPM, getY() + 40/PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20/PPM, 54/PPM);
        fixtureDef.filter.categoryBits = ENEMY_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | GROUND_BIT | CHEST_BIT | PORTAL_BIT | NOTHING_BIT;
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);
        PolygonShape shape1 = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(10, 25).scl(1/PPM);
        vertice[1] = new Vector2(25, 25).scl(1/PPM);
        vertice[2] = new Vector2(25, 8).scl(1/PPM);
        vertice[3] = new Vector2(10, 8).scl(1/PPM);
        shape1.set(vertice);
        fixtureDef.shape = shape1;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = WEAK_POINT_BIT;
        fixtureDef.filter.maskBits = SWORD_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(-21/PPM, 54/PPM, -21/PPM, -54/PPM);
        fixtureDef.filter.categoryBits = BOSS_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT;
        fixtureDef.shape = edgeShape;
        fixtureDef.isSensor = true;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void bulletHit() {
    }

    @Override
    public void swordHit() {
        HP = 0;
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
}
