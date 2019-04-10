package com.mygdx.game.Levels.Level1;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Enemies.Enemy;
import com.mygdx.game.Objects.Bullet;

import static com.mygdx.game.MyGame.BULLET_BIT;
import static com.mygdx.game.MyGame.ENEMY_BIT;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
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
        }

        if (fixA.getUserData() == "boots" || fixB.getUserData() == "boots") {
            Fixture boots = fixA.getUserData() == "boots" ? fixA : fixB;
            Fixture object = boots == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).isGrounded();
            }
        }

        /*if(bodyA.getUserData() == MyGame.BodyData.BULLET && bodyB.getUserData() == MyGame.BodyData.PLAYER){
            bodyA.applyLinearImpulse(new Vector2(10000f, 10000f), new Vector2(0, 0), true);
        }
        if(bodyB.getUserData() == MyGame.BodyData.BULLET && bodyA.getUserData() == MyGame.BodyData.PLAYER){
            bodyB.applyLinearImpulse(new Vector2(10000f, 10000f), new Vector2(0, 0), true);
        }
        if (fixA.getUserData() == "bullet" || fixB.getUserData() == "bullet") {
            Fixture bullet = fixA.getUserData() == "bullet" ? fixA : fixB;
            Fixture object = bullet == fixA ? fixB : fixA;

            if (object.getUserData() != null && Enemy.class.isAssignableFrom(object.getUserData().getClass())) {
                ((Enemy) object.getUserData()).bulletHit();
            }
        }*/
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
