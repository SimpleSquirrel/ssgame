package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Graphics.HUD;

import static com.mygdx.game.MyGame.*;

public class DefendedCannon extends Enemy {
    Bullet bullet;

    private TextureAtlas atlas;

    private Texture textureCannon;
    private Sprite spriteCannon;
    private boolean setToDestroy;
    private boolean destroyed;
    private boolean attack;
    private static final float shootTimer = 1f;
    private float stateTimer;
    private float timer;
    private Animation BTOOM;
    private Sprite btoom1;
    private Sprite btoom2;
    private Sprite btoom3;
    public Array<Bullet> defendedCannonBullets = new Array<Bullet>();

    public DefendedCannon(World world, float x, float y, boolean flip) {
        super(world, x, y, flip);
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
        textureCannon = new Texture("Enemies/DefendedCannon.png");
        spriteCannon = new Sprite(textureCannon);
        if(isFlip){
            spriteCannon.flip(true, false);
        }
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
            setRegion((Sprite)BTOOM.getKeyFrame(timer, false));
            stateTimer = 0;
            destroyed = true;
            HUD.SCORE+=35;
        }
        if(attack){
            if (stateTimer >= shootTimer) {
                if (!isFlip) {
                    bulletShot.play(0.01f);
                    bullet = new Bullet(world, b2body.getPosition().x, b2body.getPosition().y, 24/PPM, 1/PPM);
                    bullet.bulletBody.setLinearVelocity(4f, 0);
                    defendedCannonBullets.add(bullet);
                } else {
                    bulletShot.play(0.01f);
                    bullet = new Bullet(world, b2body.getPosition().x, b2body.getPosition().y, -24/PPM, 1/PPM);
                    bullet.bulletBody.setLinearVelocity(-4f, 0);
                    defendedCannonBullets.add(bullet);
                }
                stateTimer = 0;
            }
        }
        for (Bullet bullet:defendedCannonBullets){
            bullet.update();
            if(bullet.isDestroyed())
                playerBullets.removeValue(bullet, true);
        }
    }

    public void draw(Batch batch){
        for (Bullet bullet:defendedCannonBullets){
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
        shape.setAsBox(14/PPM, 8/PPM);
        fdef.filter.categoryBits = GROUND_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT | ENEMY_BIT;
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

        PolygonShape back = new PolygonShape();
        Vector2[] weakPoint = new Vector2[4];
        if(!isFlip) {
            weakPoint[0] = new Vector2(-14, 16).scl(1 / PPM);
            weakPoint[1] = new Vector2(-14, -16).scl(1 / PPM);
            weakPoint[2] = new Vector2(-13, -16).scl(1 / PPM);
            weakPoint[3] = new Vector2(-13, 16).scl(1 / PPM);
        }
        else {
            weakPoint[0] = new Vector2(14, 16).scl(1 / PPM);
            weakPoint[1] = new Vector2(14, -16).scl(1 / PPM);
            weakPoint[2] = new Vector2(13, -16).scl(1 / PPM);
            weakPoint[3] = new Vector2(13, 16).scl(1 / PPM);
        }
        back.set(weakPoint);

        fdef.shape = back;
        fdef.isSensor = true;
        fdef.filter.categoryBits = ENEMY_BIT;
        fdef.filter.maskBits = SWORD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void bulletHit() {
        HP -= 0;
        if(HP <= 0) {
            deleted();
            explosion.play(0.1f);
        }
    }

    @Override
    public void swordHit() {
        HP -= 10;
        if(HP <= 0) {
            explosion.play(0.1f);
            deleted();
        }
    }

    public void fire(){
        attack = true;
    }

    @Override
    public void deleted() {
        setToDestroy = true;
        stateTimer = 0;
    }

    @Override
    public void reverseVelocity(boolean x, boolean y) {

    }
    @Override
    public boolean isDestroyed(){
        return destroyed;
    }

    @Override
    public void playSound() {

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