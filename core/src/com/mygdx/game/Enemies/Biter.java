package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.Levels.Level2.GameScreenLevel2;
import com.mygdx.game.Levels.Level3.GameScreenLevel3;
import com.mygdx.game.Levels.Level4.GameScreenLevel4;

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
    private float timer;
    public float positionX;
    public float positionY;
    private float stateTimer;
    public Biter(World world, float x, float y, boolean flip){
        super(world, x, y, flip);
        HP = 50;
        setToDestroy = false;
        destroyed = false;
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
        velocity = new Vector2(4, -8);
    }
    public void update(float delta, float position){
        timer += delta;
        stateTimer += delta;
        timerToZero();
        if(setToDestroy && !destroyed){
            b2body.setLinearVelocity(0, 0);
            positionX = b2body.getPosition().x;
            positionY = b2body.getPosition().y;
            world.destroyBody(b2body);
            HUD.SCORE+=30;
            attack = false;
            GameScreenLevel2.destroyTimer = 0;
            GameScreenLevel3.destroyTimer = 0;
            GameScreenLevel4.destroyTimer = 0;
            destroyed = true;
        }
        else if(!destroyed && !setToDestroy && !FOURboolean){
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
        else if(FOURboolean && !setToDestroy){
            System.out.println(timer);
            if(stateTimer <= FOURtimer) {
                b2body.setLinearVelocity(0, 0);
            }
            else {
                counter = 1;
                FOURboolean = false;
            }
        }
    }
    public Sprite spriteBiter(float delta){
        Sprite sprite;
        timer+=delta;
        if (b2body.getLinearVelocity().x > 0) {
            sprite = (Sprite) biterAnimation.getKeyFrame(timer, true);
        } else if (b2body.getLinearVelocity().x < 0) {
            sprite = (Sprite) biterAnimationLeft.getKeyFrame(timer, true);

        } else {
            sprite = spriteBiter1;
        }
        return sprite;
    }

    public Sprite babax(float delta){
        Sprite sprite;
        timer+=delta;
        sprite = (Sprite)BTOOM.getKeyFrame(timer, false);
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
        fixtureDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | GROUND_BIT | CHEST_BIT | PORTAL_BIT | NOTHING_BIT | SWORD_BIT | SHIELD_BIT;
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
        fixtureDef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT;
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
        if(rageActive){
            HP = 0;
            if(HP <= 0){
                explosion.play(0.1f);
                deleted();
            }
        }
        else {
            HP -= 17;
            if(HP <= 0){
                explosion.play(0.1f);
                deleted();
            }
        }
    }

    @Override
    public void swordHit() {
        HP -= 50;
        if(HP <= 0){
            explosion.play(0.1f);
            deleted();
        }
    }

    @Override
    public void fire() {
        attack = true;
    }

    @Override
    public void deleted() {
        timer = 0;
        setToDestroy = true;
    }
    @Override
    public boolean isDestroyed(){
        return destroyed;
    }
    private void timerToZero(){
        if(FOURboolean) {
            if(counter == 1) {
                stateTimer = 0;
                counter++;
            }
        }
    }

    public boolean isSetToDestroy(){return setToDestroy;}
    public void reverseVelocity(boolean x, boolean y){
        if(x){
            if(velocity.x > 0){
                check = velocity.x;
                velocity.x -= 4;
            }
            else if (velocity.x == 0 && check > 0){
                velocity.x -= 4;
            }
            else if (velocity.x < 0){
                check = velocity.x;
                velocity.x += 4;
            }
            else if (velocity.x == 0 && check < 0){
                velocity.x += 4;
            }
        }
        if(y){
            velocity.y = -velocity.y;
        }
    }
}
