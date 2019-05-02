package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import static com.mygdx.game.MyGame.PLAYER_BIT;

public class VerticalCannon extends Enemy {
    Bullet bullet;

    private TextureAtlas atlas;

    private Texture textureVerticalCannon;
    private Sprite spriteVerticalCannon;
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
    public Array<Bullet> verticalCannonBullets = new Array<Bullet>();

    public VerticalCannon(World world, float x, float y, boolean flip) {
        super(world, x, y, flip);
        System.out.println(x  + " " + y);
        HP = 20;
        atlas = new TextureAtlas("Animations/Btoom.txt");
        btoom1 = atlas.createSprite("Btoom1");
        btoom2 = atlas.createSprite("Btoom2");
        btoom3 = atlas.createSprite("Btoom3");
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(btoom1);
        frames.add(btoom2);
        frames.add(btoom3);
        BTOOM = new Animation(0.05f, frames);
        textureVerticalCannon = new Texture("Enemies/VerticalCannon.png");
        spriteVerticalCannon = new Sprite(textureVerticalCannon);
        setBounds(getX(), getY(), 32/PPM, 50/PPM);
        setToDestroy = false;
        destroyed = false;
        attack = true;

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
            HUD.SCORE+=15;
        }
        if(attack){
            if (stateTimer >= shootTimer) {
                if (!spriteVerticalCannon.isFlipX()) {
                    bullet = new Bullet(world, b2body.getPosition().x, b2body.getPosition().y, 4/PPM, 30/PPM);
                    bullet.bulletBody.setLinearVelocity(0, 4f);
                    verticalCannonBullets.add(bullet);
                } else {
                    bullet = new Bullet(world, b2body.getPosition().x, b2body.getPosition().y, -4/PPM, 30/PPM);
                    bullet.bulletBody.setLinearVelocity(0, -4f);
                    verticalCannonBullets.add(bullet);
                }
                stateTimer = 0;
            }
        }
        for (Bullet bullet:verticalCannonBullets){
            bullet.update(delta);
            if(bullet.isDestroyed())
                playerBullets.removeValue(bullet, true);
        }
    }

    public void draw(Batch batch){
        for (Bullet bullet:verticalCannonBullets){
            if(!bullet.isDestroyed()) {
                bullet.draw(batch);
            }
        }
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX() + 25/PPM, getY() - 4/PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8/PPM, 25/PPM);
        fdef.filter.categoryBits = ENEMY_BIT;
        fdef.filter.maskBits = GROUND_BIT | BULLET_BIT | SWORD_BIT | PLAYER_BIT;
        fdef.shape = shape;
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
    public void fire() {

    }

    @Override
    public void deleted(){
        setToDestroy = true;
        stateTimer = 0;
    }

    @Override
    public void reverseVelocity(boolean x, boolean y) {

    }
    @Override
    public boolean isDestroyed(){return destroyed;}
    public Sprite babax(){
        Sprite sprite;
        if(setToDestroy && !destroyed){
            sprite = (Sprite) BTOOM.getKeyFrame(stateTimer, false);
        }
        else if (destroyed){
            sprite = Assets.spriteEmpty;
        }
        else {
            sprite = spriteVerticalCannon;
        }
        return sprite;
    }
}
