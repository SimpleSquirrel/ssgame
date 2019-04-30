package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Player.HUD;

import static com.mygdx.game.MyGame.*;

public class Cannon extends Enemy {

    Bullet bullet;

    private TextureAtlas atlas;

    private Texture textureCannon;
    private Sprite spriteCannon;
    private boolean setToDestroy;
    private boolean destroyed;
    private boolean attack;
    private static final float shootTimer = 2f;
    private float stateTimer;
    private float timer;
    private Animation BTOOM;
    private Sprite btoom1;
    private Sprite btoom2;
    private Sprite btoom3;
    public Array<Bullet> cannonBullets = new Array<Bullet>();

    public Cannon(World world, float x, float y) {
        super(world, x, y);
        System.out.println(x  + " " + y);
        HP = 10;
        atlas = new TextureAtlas("Animations/Btoom.txt");
        btoom1 = atlas.createSprite("Btoom1");
        btoom2 = atlas.createSprite("Btoom2");
        btoom3 = atlas.createSprite("Btoom3");
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(btoom1);
        frames.add(btoom2);
        frames.add(btoom3);
        BTOOM = new Animation(0.05f, frames);
        textureCannon = new Texture("Enemies/Cannon.png");
        spriteCannon = new Sprite(textureCannon);
        setBounds(getX(), getY(), 32/PPM, 32/PPM);
        setToDestroy = false;
        destroyed = false;
        attack = false;
    }
    public void update(float delta){
        stateTimer += delta;
        timer += delta;
        if(setToDestroy && !destroyed && stateTimer > 0.3f){
            world.destroyBody(b2body);
            attack = false;
            setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
            stateTimer = 0;
            destroyed = true;
            HUD.SCORE+=25;
        }
        if(attack){
            if (stateTimer >= shootTimer) {
                if (!spriteCannon.isFlipX()) {
                    bullet = new Bullet(world, b2body.getPosition().x, b2body.getPosition().y, 24/PPM, 8/PPM);
                    bullet.bulletBody.setLinearVelocity(4f, 0);
                    cannonBullets.add(bullet);
                } else {
                    bullet = new Bullet(world, b2body.getPosition().x, b2body.getPosition().y, -24/PPM, 8/PPM);
                    bullet.bulletBody.setLinearVelocity(-4f, 0);
                    cannonBullets.add(bullet);
                }
                stateTimer = 0;
            }
        }
        for (Bullet bullet:cannonBullets){
            bullet.update(delta);
            if(bullet.isDestroyed())
                playerBullets.removeValue(bullet, true);
        }
        }

    public void draw(Batch batch){
        for (Bullet bullet:cannonBullets){
            if(!bullet.isDestroyed()) {
                bullet.draw(batch);
            }
        }
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX() + 20/PPM, getY() + 16/PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14/PPM, 16/PPM);
        fdef.filter.categoryBits = ENEMY_BIT;
        fdef.filter.maskBits = GROUND_BIT | BULLET_BIT | SWORD_BIT | PLAYER_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        PolygonShape sensor = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-1600, 15).scl(1/PPM);
        vertice[1] = new Vector2(1600, 15).scl(1/PPM);
        vertice[2] = new Vector2(1600, 10).scl(1/PPM);
        vertice[3] = new Vector2(-1600, 10).scl(1/PPM);
        sensor.set(vertice);

        fdef.shape = sensor;
        fdef.isSensor = true;
        fdef.filter.categoryBits = SENSOR_BIT;
        fdef.filter.maskBits = PLAYER_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void bulletHit() {
        HP -= 10;
        if(HP <= 0) {
            deleted();
        }
    }

    @Override
    public void swordHit() {
        HP -= 20;
        if(HP <= 0) {
            deleted();
        }
    }
    @Override
    public void deleted(){
        setToDestroy = true;
        stateTimer = 0;
    }

    public void fire(){
        attack = true;
    }
    public boolean isDestroyed(){
        return destroyed;
    }
    public Sprite babax(){
        Sprite sprite;
        if(setToDestroy && !destroyed){
            sprite = (Sprite) BTOOM.getKeyFrame(stateTimer, false);
        }
        else if (destroyed){
            sprite = Assets.spriteEmpty;
        }
        else {
            sprite = spriteCannon;
        }
        return sprite;
    }
}
