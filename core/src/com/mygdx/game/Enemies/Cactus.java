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
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Graphics.HUD;

import static com.mygdx.game.MyGame.*;
import static com.mygdx.game.MyGame.PLAYER_BIT;

public class Cactus extends Enemy {
    private TextureAtlas atlas;
    private TextureAtlas atlas1;
    private Sprite spriteCactus1;
    private Sprite spriteCactus2;
    private Sprite btoom1;
    private Sprite btoom2;
    private Sprite btoom3;
    private Animation animationCactus;
    private Animation BTOOM;
    private float stateTimer;
    private float timer;
    private boolean setToDestroy;
    private boolean destroyed;
    private float checkVelocity;
    public Cactus(World world, float x, float y, boolean flip) {
        super(world, x, y, flip);
        HP = 50;
        atlas = new TextureAtlas("Enemies/Cactus.txt");
        spriteCactus1 = atlas.createSprite("Cactus1");
        spriteCactus2 = atlas.createSprite("Cactus2");
        atlas1 = new TextureAtlas("Animations/Btoom.txt");
        btoom1 = atlas1.createSprite("Btoom1");
        btoom2 = atlas1.createSprite("Btoom2");
        btoom3 = atlas1.createSprite("Btoom3");
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(spriteCactus1);
        frames.add(spriteCactus2);
        animationCactus = new Animation(0.1f, frames);
        frames.clear();
        frames.add(btoom1);
        frames.add(btoom2);
        frames.add(btoom3);
        BTOOM = new Animation(0.05f, frames);
        frames.clear();
        velocity = new Vector2(8, -8);
        check = velocity.x/2;
    }

    public void update(float delta){
        stateTimer += delta;
        timer += delta;
        if(setToDestroy && !destroyed && stateTimer > 0.3f){
            world.destroyBody(b2body);
            setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
            stateTimer = 0;
            destroyed = true;
            HUD.SCORE+=30;
        }
        else if(!destroyed && !setToDestroy){
            b2body.setLinearVelocity(velocity);
        }
        else if(setToDestroy){
            b2body.setLinearVelocity(0, 0);
        }
    }
    public Sprite spriteCactus(float delta){
        Sprite sprite;
        timer += delta;
        if(setToDestroy && !destroyed){
            sprite = (Sprite) BTOOM.getKeyFrame(timer, false);
        }
        else if(destroyed){
            sprite = Assets.spriteEmpty;
        }
        else {
            sprite = (Sprite) animationCactus.getKeyFrame(timer, true);
        }
        return  sprite;
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX() + 20/PPM, getY() + 16/PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(15/PPM, 35/PPM);
        fixtureDef.filter.categoryBits = ENEMY_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | GROUND_BIT | CHEST_BIT | PORTAL_BIT | NOTHING_BIT | SWORD_BIT;
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);
        PolygonShape shape1 = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-15, 34).scl(1/PPM);
        vertice[1] = new Vector2(15, 34).scl(1/PPM);
        vertice[2] = new Vector2(15, -34).scl(1/PPM);
        vertice[3] = new Vector2(-15, -34).scl(1/PPM);
        shape1.set(vertice);
        fixtureDef.shape = shape1;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = WALKING_ENEMY_BIT;
        fixtureDef.filter.maskBits = GROUND_BIT | PLAYER_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
        PolygonShape weapon1 = new PolygonShape();
        Vector2[] weaponVector = new Vector2[4];
        weaponVector[0] = new Vector2(0, -1).scl(1/PPM);
        weaponVector[1] = new Vector2(36, -1).scl(1/PPM);
        weaponVector[2] = new Vector2(36, 25).scl(1/PPM);
        weaponVector[3] = new Vector2(0, 25).scl(1/PPM);
        weapon1.set(weaponVector);
        fixtureDef.shape = weapon1;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = ENEMY_BIT;
        fixtureDef.filter.categoryBits = PLAYER_BIT | SWORD_BIT | BULLET_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
        PolygonShape weapon2 = new PolygonShape();
        Vector2[] weapon2Vector = new Vector2[4];
        weapon2Vector[0] = new Vector2(0, 9).scl(1/PPM);
        weapon2Vector[1] = new Vector2(-34, 9).scl(1/PPM);
        weapon2Vector[2] = new Vector2(-34, -16).scl(1/PPM);
        weapon2Vector[3] = new Vector2(0, -16).scl(1/PPM);
        weapon2.set(weapon2Vector);
        fixtureDef.shape = weapon2;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = ENEMY_BIT;
        fixtureDef.filter.categoryBits = PLAYER_BIT | SWORD_BIT | BULLET_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void bulletHit() {
        HP -= 10;
        if(HP <= 0){
            deleted();
        }
    }

    @Override
    public void swordHit() {
        HP -= 10;
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
        stateTimer = 0;
    }

    @Override
    public void reverseVelocity(boolean x, boolean y) {
            if (x) {
                if(velocity.x != 0) {
                    checkVelocity = velocity.x;
                    velocity.x = 0;
                }
                else if(velocity.x == 0 && checkVelocity == 8) {
                    velocity.x = -8;
                }
                else if(velocity.x == 0 && checkVelocity == -8){
                    velocity.x = 8;
                }
            }
            if (y) {
                velocity.y = -velocity.y;
            }
        }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }
}
