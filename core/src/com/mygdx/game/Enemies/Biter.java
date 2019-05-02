package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.MyGame.*;

public class Biter extends Enemy {
    private TextureAtlas atlas;
    private TextureAtlas atlas1;
    private Animation BTOOM;
    private Sprite btoom1;
    private Sprite btoom2;
    private Sprite btoom3;
    private Sprite spriteBiter1;
    private Sprite spriteBiter2;
    private Sprite spriteBiter3;
    private Sprite spriteBiter4;
    private Sprite spriteBiter01;
    private Sprite spriteBiter02;
    private Sprite spriteBiter03;
    private Sprite spriteBiter04;
    private Animation biterAnimation;
    private Animation biterAnimationLeft;
    private boolean setToDestroy;
    private boolean destroyed;
    private boolean attack;
    private float timer;
    private float stateTimer;
    Player player;
    public Biter(World world, float x, float y, boolean flip){
        super(world, x, y, flip);
        HP = 50;
        setToDestroy = false;
        destroyed = false;
        attack = false;
        atlas1 = new TextureAtlas("Animations/Btoom.txt");
        btoom1 = atlas1.createSprite("Btoom1");
        btoom2 = atlas1.createSprite("Btoom2");
        btoom3 = atlas1.createSprite("Btoom3");
        atlas = new TextureAtlas("Enemies/Biter.txt");
        spriteBiter1 = atlas.createSprite("Biter1");
        spriteBiter2 = atlas.createSprite("Biter2");
        spriteBiter3 = atlas.createSprite("Biter3");
        spriteBiter4 = atlas.createSprite("Biter4");
        spriteBiter01 = atlas.createSprite("Biter1");
        spriteBiter02 = atlas.createSprite("Biter2");
        spriteBiter03 = atlas.createSprite("Biter3");
        spriteBiter04 = atlas.createSprite("Biter4");
        spriteBiter01.flip(true, false);
        spriteBiter02.flip(true, false);
        spriteBiter03.flip(true, false);
        spriteBiter04.flip(true, false);
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(spriteBiter1);
        frames.add(spriteBiter2);
        frames.add(spriteBiter3);
        frames.add(spriteBiter4);
        frames.add(spriteBiter3);
        frames.add(spriteBiter2);
        biterAnimation = new Animation(0.1f, frames);
        frames.clear();
        frames.add(spriteBiter01);
        frames.add(spriteBiter02);
        frames.add(spriteBiter03);
        frames.add(spriteBiter04);
        frames.add(spriteBiter03);
        frames.add(spriteBiter02);
        biterAnimationLeft = new Animation(0.1f, frames);
        frames.clear();
        frames.add(btoom1);
        frames.add(btoom2);
        frames.add(btoom3);
        BTOOM = new Animation(0.05f, frames);
        frames.clear();
    }
    public void update(float delta, float position){
        stateTimer += delta;
        timer += delta;
        if(setToDestroy && !destroyed && stateTimer > 0.3f){
            world.destroyBody(b2body);
            HUD.SCORE+=30;
            attack = false;
            setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
            stateTimer = 0;
            destroyed = true;
        }
        else if(!destroyed && !setToDestroy){
            if(attack){
                if(position < b2body.getPosition().x){
                    b2body.setLinearVelocity(-2, 0);
                }
                else{
                    b2body.setLinearVelocity(2, 0);
                }
            }
            else {
                b2body.setLinearVelocity(velocity);
            }
        }
        else if(setToDestroy){
            b2body.setLinearVelocity(0, 0);
        }
    }
    public Sprite spriteBiter(float delta){
        Sprite sprite;
        timer+=delta;
        if (setToDestroy && !destroyed){
            sprite = (Sprite) BTOOM.getKeyFrame(stateTimer, false);
        }
        else if(destroyed){
            sprite = Assets.spriteEmpty;
        }
        else {
            if (b2body.getLinearVelocity().x > 0) {
                sprite = (Sprite) biterAnimation.getKeyFrame(timer, true);
            } else if (b2body.getLinearVelocity().x < 0) {
                sprite = (Sprite) biterAnimationLeft.getKeyFrame(timer, true);

            } else {
                sprite = spriteBiter1;
            }
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
        shape.setAsBox(20/PPM, 35/PPM);
        fixtureDef.filter.categoryBits = ENEMY_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | GROUND_BIT | CHEST_BIT | PORTAL_BIT | NOTHING_BIT | SWORD_BIT;
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);
        PolygonShape shape1 = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-20, 34).scl(1/PPM);
        vertice[1] = new Vector2(20, 34).scl(1/PPM);
        vertice[2] = new Vector2(20, -34).scl(1/PPM);
        vertice[3] = new Vector2(-20, -34).scl(1/PPM);
        shape1.set(vertice);
        fixtureDef.shape = shape1;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = WALKING_ENEMY_BIT;
        fixtureDef.filter.maskBits = GROUND_BIT | PLAYER_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
        PolygonShape sensor = new PolygonShape();
        Vector2[] vertice1 = new Vector2[4];
        vertice1[0] = new Vector2(-100, 12).scl(1/PPM);
        vertice1[1] = new Vector2(100, 12).scl(1/PPM);
        vertice1[2] = new Vector2(100, -12).scl(1/PPM);
        vertice1[3] = new Vector2(-100, -12).scl(1/PPM);
        sensor.set(vertice1);
        fixtureDef.shape = sensor;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = SENSOR_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void bulletHit() {
        HP -= 17;
        if(HP <= 0){
            deleted();
        }
    }

    @Override
    public void swordHit() {
        HP -= 25;
        if(HP <= 0){
            deleted();
        }
    }

    @Override
    public void fire() {
        attack = true;
    }

    @Override
    public void deleted() {
        setToDestroy = true;
        stateTimer = 0;
    }
    public boolean isDestroyed(){
        return destroyed;
    }
}
