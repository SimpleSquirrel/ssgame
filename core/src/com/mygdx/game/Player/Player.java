package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.MyGame.*;

public class Player extends Sprite {
    public enum State { RUNNING, FALLING, JUMPING, STANDING}
    private State currentState;
    private State previousState;
    public World world;
    public Body b2body;
    private Animation robotRun;
    private Animation robotHit;
    private TextureAtlas atlas;
    private float stateTimer;
    private boolean isDead;
    public static boolean runningRight;
    private Sprite spriteRobotStand;
    private Sprite spriteRobotRun1;
    private Sprite spriteRobotRun2;
    private Sprite spriteRobotRun3;
    private Sprite spriteRobotRun4;
    private Sprite spriteRobotRun5;
    private Sprite spriteRobotHit0;
    private Sprite spriteRobotHit1;
    private Sprite spriteRobotHit2;
    private Sprite spriteRobotHit3;
    private Sprite spriteRobotHit4;
    private Sprite spriteStatic;
    private Sprite spriteStatic0;
    private Sprite spriteStatic1;
    private Animation robotStatic;
    private Animation robotStatic1;
    public static int HP;
    public static int MAX_HP;
    public static boolean swordAttack;
    private FixtureDef fSwordDef = new FixtureDef();
    private FixtureDef shieldDef = new FixtureDef();
    private float swordTimer;
    private float swordAnimationTimer;
    private float x;
    private float y;
    public boolean shield;
    private int counter;

    public Player(World world, float x, float y){
        HP = 25;
        MAX_HP = HP;
        this.x = x;
        this.y = y;
        this.world = world;
        atlas = new TextureAtlas("Animations/Robot.txt");
        spriteRobotStand = atlas.createSprite("Run1");
        spriteRobotRun1 = atlas.createSprite("Run1");
        spriteRobotRun2 = atlas.createSprite("Run2");
        spriteRobotRun3 = atlas.createSprite("Run3");
        spriteRobotRun4 = atlas.createSprite("Run4");
        spriteRobotRun5 = atlas.createSprite("Run5");
        spriteRobotHit0 = atlas.createSprite("Sword0");
        spriteRobotHit1 = atlas.createSprite("Sword1");
        spriteRobotHit2 = atlas.createSprite("Sword2");
        spriteRobotHit3 = atlas.createSprite("Sword3");
        spriteRobotHit4 = atlas.createSprite("Sword4");
        spriteStatic = atlas.createSprite("Static");
        spriteStatic0 = atlas.createSprite("Static0");
        spriteStatic1 = atlas.createSprite("Static1");
        setBounds(0, 0, 32/PPM, 64/PPM);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(spriteRobotRun1);
        frames.add(spriteRobotRun2);
        frames.add(spriteRobotRun3);
        frames.add(spriteRobotRun4);
        frames.add(spriteRobotRun5);
        robotRun = new Animation(0.3f, frames);
        frames.clear();
        frames.add(spriteRobotHit0);
        frames.add(spriteRobotHit1);
        frames.add(spriteRobotHit2);
        frames.add(spriteRobotHit3);
        frames.add(spriteRobotHit4);
        robotHit = new Animation(0.1f, frames);
        frames.clear();
        frames.add(spriteStatic0);
        frames.add(spriteStatic1);
        robotStatic = new Animation(0.5f, frames);
        frames.clear();
        frames.add(spriteRobotHit0);
        frames.add(spriteStatic);
        robotStatic1 = new Animation(0.5f, frames);
        frames.clear();
        isDead = false;
        swordAttack = false;
        shield = false;
        counter = 1;
        definePlayer();
    }
    public void update(float delta) {
        swordTimer += delta;
        if(swordTimer > 0.1f && !swordAttack) {
            for (Fixture fixture : b2body.getFixtureList()) {
                if (fixture.getFilterData().categoryBits == SWORD_BIT) {
                    b2body.destroyFixture(fixture);
                }
            }
        }
        if(!shield){
            for (Fixture fixture : b2body.getFixtureList()){
                if(fixture.getFilterData().categoryBits == SHIELD_BIT){
                    b2body.destroyFixture(fixture);
                    counter = 1;
                }
            }
        }
        if (swordAttack) {
            if (runningRight) {
                PolygonShape sword = new PolygonShape();
                Vector2[] swordHitbox = new Vector2[4];
                swordHitbox[0] = new Vector2(12, -24).scl(1 / PPM);
                swordHitbox[1] = new Vector2(16, -24).scl(1 / PPM);
                swordHitbox[2] = new Vector2(16, 24).scl(1 / PPM);
                swordHitbox[3] = new Vector2(12, 24).scl(1 / PPM);
                sword.set(swordHitbox);

                fSwordDef.shape = sword;
                fSwordDef.isSensor = true;
                fSwordDef.filter.categoryBits = SWORD_BIT;
                fSwordDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | ENEMY_BIT | WEAK_POINT_BIT;
                b2body.createFixture(fSwordDef).setUserData(this);
                swordTimer = 0;
                swordAttack = false;
            } else {
                PolygonShape sword1 = new PolygonShape();
                Vector2[] swordHitbox1 = new Vector2[4];
                swordHitbox1[0] = new Vector2(-12, -24).scl(1 / PPM);
                swordHitbox1[1] = new Vector2(-16, -24).scl(1 / PPM);
                swordHitbox1[2] = new Vector2(-16, 24).scl(1 / PPM);
                swordHitbox1[3] = new Vector2(-12, 24).scl(1 / PPM);
                sword1.set(swordHitbox1);

                fSwordDef.shape = sword1;
                fSwordDef.isSensor = true;
                fSwordDef.filter.categoryBits = SWORD_BIT;
                fSwordDef.filter.maskBits = PLAYER_BIT | BULLET_BIT | ENEMY_BIT | WEAK_POINT_BIT;
                b2body.createFixture(fSwordDef).setUserData(this);
                swordTimer = 0;
                swordAttack = false;
            }
        }
        if(shield){
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(20/PPM, 35/PPM);

            shieldDef.shape = shape;
            shieldDef.filter.categoryBits = SHIELD_BIT;
            shieldDef.filter.maskBits = SPIKE_BIT | BULLET_BIT | ENEMY_BIT;
            shieldDef.isSensor = true;
            b2body.createFixture(shieldDef).setUserData(this);
        }
    }
    public Sprite getFrameLegs(float delta){
        currentState = getState();
        Sprite sprite;
        switch (currentState){
            case JUMPING:
                sprite = spriteRobotRun3;
                break;
            case FALLING:
                sprite = spriteRobotRun5;
                break;
            case STANDING:
                sprite = (Sprite) robotStatic.getKeyFrame(stateTimer, true);
                break;
            case RUNNING:
                sprite = (Sprite) robotRun.getKeyFrame(stateTimer, true);
                break;
            default:
                sprite = spriteRobotRun1;
        }
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !sprite.isFlipX()){
            sprite.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && sprite.isFlipX()){
            sprite.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return sprite;
    }
    public Sprite getFrameChest(float delta){
        swordAnimationTimer += delta;
        currentState = getState();
        Sprite sprite;
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            swordAnimationTimer = 0;
        }
        if(swordAnimationTimer >= 0 && swordAnimationTimer <= 0.218f){
            sprite = (Sprite)robotHit.getKeyFrame(stateTimer, true);
        }
        else {
            if(b2body.getLinearVelocity().x != 0 || b2body.getLinearVelocity().y != 0){
                sprite = spriteRobotHit0;
            }
            else {
                sprite = (Sprite) robotStatic1.getKeyFrame(stateTimer, true);
            }
        }
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !sprite.isFlipX()){
            sprite.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && sprite.isFlipX()){
            sprite.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return sprite;
    }
    private State getState(){
        if(b2body.getLinearVelocity().y > 0){
            return State.JUMPING;
        }
        else if(b2body.getLinearVelocity().y < -0.1){
            return State.FALLING;
        }
        else if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else {
            return State.STANDING;
        }
    }

    private void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(9/PPM, 27/PPM);
        fdef.filter.categoryBits = PLAYER_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT | ENEMY_BIT | CHEST_BIT | FLOOR_BIT | SENSOR_BIT | PORTAL_BIT | SPIKE_BIT | WALKING_ENEMY_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    public void jump(){
       // if(currentState != State.JUMPING && currentState != State.FALLING){
            b2body.applyLinearImpulse(new Vector2(0, 10f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
       // }
    }

    public void bulletHit(){
        HP -= 10;
    }
    public void swordHit(){
        HP -= 25;
    }
    public boolean isDead(){
        if(HP <= 0){
            isDead = true;
        }
        return isDead;
    }
    public void spikeHit(){
        HP -= 25;
    }
    public void hitByEnemy(float x){
        if(counter == 1) {
            x = b2body.getPosition().x - x;
            if (x > 0) {
                b2body.applyForce(new Vector2(300, 200), b2body.getWorldCenter(), true);
            } else {
                b2body.applyForce(new Vector2(-300, 200), b2body.getWorldCenter(), true);
            }
            System.out.println("Contact");
            counter++;
        }
    }
    public void heal(){
        HP += 10;
    }
    public void shield(){
        shield = true;
    }
    public void destroyShield(){
        shield = false;
    }
}
