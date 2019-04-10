package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.MyGame;

import static com.mygdx.game.MyGame.*;

public class Cannon extends Enemy {

    private Texture textureCannon;
    private Sprite spriteCannon;
    private boolean setToDestroy;
    private boolean destroyed;
    public Cannon(GameScreenLevel1 level1, float x, float y) {
        super(level1, x, y);
        textureCannon = new Texture("Enemies/Cannon.png");
        spriteCannon = new Sprite(textureCannon);
        setBounds(getX(), getY(), 32/PPM, 32/PPM);
        setToDestroy = false;
        destroyed = false;
    }
    public void update(){
        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
        }
        else if(!destroyed) {
            setPosition(0, (1 + 32 * 7) / PPM);
            setRegion(spriteCannon);
        }
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, (2 + 32*7)/PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14/PPM, 16/PPM);
        fdef.filter.categoryBits = ENEMY_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT | ENEMY_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void bulletHit() {
        setToDestroy = true;
    }
}
