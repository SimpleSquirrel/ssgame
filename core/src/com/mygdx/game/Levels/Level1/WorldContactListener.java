package com.mygdx.game.Levels.Level1;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Enemies.DefendedCannon;
import com.mygdx.game.Enemies.Enemy;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.MyGame.*;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case BULLET_BIT | ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == ENEMY_BIT){
                    ((Enemy)fixA.getUserData()).bulletHit();
                    ((Bullet)fixB.getUserData()).deleteBullet();
                }
                else {
                    ((Enemy)fixB.getUserData()).bulletHit();
                    ((Bullet)fixA.getUserData()).deleteBullet();
                }
                break;
            case BULLET_BIT | GROUND_BIT:
                if(fixA.getFilterData().categoryBits == BULLET_BIT){
                    ((Bullet)fixA.getUserData()).deleteBullet();
                }
                else {
                    ((Bullet)fixB.getUserData()).deleteBullet();
                }
                break;
            case BULLET_BIT | PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == BULLET_BIT){
                    ((Bullet)fixA.getUserData()).deleteBullet();
                    ((Player)fixB.getUserData()).bulletHit();
                }
                else {
                    ((Bullet)fixB.getUserData()).deleteBullet();
                    ((Player)fixA.getUserData()).bulletHit();
                }
                break;
            case PLAYER_BIT | SENSOR_BIT:
                if(fixA.getFilterData().categoryBits == SENSOR_BIT){
                    ((Enemy)fixA.getUserData()).fire();
                }
                else {
                    ((Enemy)fixB.getUserData()).fire();
                }
                break;
            case SWORD_BIT | WEAK_POINT_BIT:
                if(fixA.getFilterData().categoryBits == SWORD_BIT){
                    ((Enemy)fixB.getUserData()).swordHit();
                }
                else {
                    ((Enemy)fixA.getUserData()).swordHit();
                }
                break;
            case SWORD_BIT | ENEMY_BIT:
                if((fixA.getFilterData().categoryBits == SWORD_BIT)){
                    ((Enemy)fixB.getUserData()).swordHit();
                }
                else{
                    ((Enemy)fixA.getUserData()).swordHit();
                }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
