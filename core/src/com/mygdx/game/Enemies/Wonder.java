package com.mygdx.game.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

import static com.mygdx.game.MyGame.*;

public class Wonder extends Enemy {
    private TextureAtlas atlas;
    private TextureAtlas atlasAttack;
    private Sprite spriteWalk1;
    private Sprite spriteWalk2;
    private Sprite spriteAttack1;
    private Sprite spriteAttack2;
    private Sprite spriteAttack3;
    private Sprite spriteAttack4;
    private Sprite spriteAttack5;
    private Sprite spriteAttack6;
    private Sprite spriteWalk01;
    private Sprite spriteWalk02;
    private Sprite spriteAttack01;
    private Sprite spriteAttack02;
    private Sprite spriteAttack03;
    private Sprite spriteAttack04;
    private Sprite spriteAttack05;
    private Sprite spriteAttack06;
    private Animation animationWalk;
    private Animation animationAttack;
    private Animation animationWalk1;
    private Animation animationAttack1;
    private boolean isTouched;
    private float timer;
    private float checkVelocity;
    private boolean setToDestroy;
    private boolean destroyed;
    private Sound omnomnom = Gdx.audio.newSound(Gdx.files.internal("Sound/wondereat.wav"));
    public Wonder(World world, float x, float y, boolean flip) {
        super(world, x, y, flip);
        atlas = new TextureAtlas("Enemies/WonderWalk.txt");
        spriteWalk1 = atlas.createSprite("Wonder1");
        spriteWalk2 = atlas.createSprite("Wonder2");
        spriteWalk01 = atlas.createSprite("Wonder1");
        spriteWalk02 = atlas.createSprite("Wonder2");
        spriteWalk01.flip(true, false);
        spriteWalk02.flip(true, false);
        atlasAttack = new TextureAtlas("Enemies/WonderAttack.txt");
        spriteAttack1 = atlasAttack.createSprite("WonderAttack1");
        spriteAttack2 = atlasAttack.createSprite("WonderAttack2");
        spriteAttack3 = atlasAttack.createSprite("WonderAttack3");
        spriteAttack4 = atlasAttack.createSprite("WonderAttack4");
        spriteAttack5 = atlasAttack.createSprite("WonderAttack5");
        spriteAttack6 = atlasAttack.createSprite("WonderAttack6");
        spriteAttack06 = atlasAttack.createSprite("WonderAttack6");
        spriteAttack01 = atlasAttack.createSprite("WonderAttack1");
        spriteAttack02 = atlasAttack.createSprite("WonderAttack2");
        spriteAttack03 = atlasAttack.createSprite("WonderAttack3");
        spriteAttack04 = atlasAttack.createSprite("WonderAttack4");
        spriteAttack05 = atlasAttack.createSprite("WonderAttack5");
        spriteAttack06 = atlasAttack.createSprite("WonderAttack6");
        spriteAttack01.flip(true, false);
        spriteAttack02.flip(true, false);
        spriteAttack03.flip(true, false);
        spriteAttack04.flip(true, false);
        spriteAttack05.flip(true, false);
        spriteAttack06.flip(true, false);
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(spriteWalk1);
        frames.add(spriteWalk2);
        animationWalk = new Animation(0.5f, frames);
        frames.clear();
        frames.add(spriteAttack1);
        frames.add(spriteAttack2);
        frames.add(spriteAttack3);
        frames.add(spriteAttack4);
        frames.add(spriteAttack5);
        frames.add(spriteAttack6);
        frames.add(spriteAttack6);
        frames.add(spriteAttack6);
        frames.add(spriteAttack6);
        frames.add(spriteAttack6);
        frames.add(Assets.spriteEmpty);
        animationAttack = new Animation(0.2f, frames);
        frames.clear();
        frames.add(spriteWalk01);
        frames.add(spriteWalk02);
        animationWalk1 = new Animation(0.5f, frames);
        frames.clear();
        frames.add(spriteAttack01);
        frames.add(spriteAttack02);
        frames.add(spriteAttack03);
        frames.add(spriteAttack04);
        frames.add(spriteAttack05);
        frames.add(spriteAttack06);
        frames.add(spriteAttack06);
        frames.add(spriteAttack06);
        frames.add(spriteAttack06);
        frames.add(spriteAttack06);
        frames.add(Assets.spriteEmpty);
        animationAttack1 = new Animation(0.2f, frames);
        frames.clear();
        isTouched = false;
        velocity = new Vector2(0.5f, 0);
        setToDestroy = false;
        destroyed = false;
    }

    public void update(float delta){
        timer += delta;
        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
        }
        if(isTouched){
            b2body.setLinearVelocity(0, 0);
        }
        else {
            b2body.setLinearVelocity(velocity);
        }
    }

    public Sprite getSprite(){
        Sprite sprite;
        if(b2body.getLinearVelocity().x > 0) {
            if (isTouched) {
                sprite = (Sprite) animationAttack1.getKeyFrame(timer, false);
            } else {
                sprite = (Sprite) animationWalk1.getKeyFrame(timer, true);
            }
        }
        else {
            if (isTouched) {
                sprite = (Sprite) animationAttack.getKeyFrame(timer, false);
            } else {
                sprite = (Sprite) animationWalk.getKeyFrame(timer, true);
            }
        }
        return sprite;
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(12/PPM, 12/PPM);
        fixtureDef.filter.categoryBits = ENEMY_BIT;
        fixtureDef.filter.maskBits = SENSOR_BIT | BULLET_BIT | SWORD_BIT | GROUND_BIT;
        fixtureDef.shape = polygonShape;
        b2body.createFixture(fixtureDef).setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(12/PPM, 12/PPM);
        fixtureDef.filter.categoryBits = WONDER_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT | GROUND_BIT;
        fixtureDef.isSensor = true;
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void bulletHit() {
    }

    @Override
    public void swordHit() {
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
        if (x) {
            if(velocity.x != 0) {
                checkVelocity = velocity.x;
                velocity.x = 0;
            }
            else if(velocity.x == 0 && checkVelocity == 0.5f) {
                velocity.x = -0.5f;
            }
            else if(velocity.x == 0 && checkVelocity == -0.5f){
                velocity.x = 0.5f;
            }
            System.out.println(velocity.x);
        }
        if (y) {
            velocity.y = -velocity.y;
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void playSound() {

    }

    public void playerTouch(){
        timer = 0;
        omnomnom.play(0.2f);
        isTouched = true;
    }

    public boolean isTouched(){
        return isTouched;
    }
}
