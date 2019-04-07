package com.mygdx.game.Levels.Level1;

import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {
    public static boolean isGrounded;
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "boots" || fixB.getUserData() == "boots"){
            Fixture boots = fixA.getUserData() == "boots" ? fixA : fixB;
            Fixture object = boots == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).isGrounded();
                isGrounded = true;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        isGrounded = false;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
