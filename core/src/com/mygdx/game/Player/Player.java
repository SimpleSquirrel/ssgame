package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Sprite {
    public World world;
    public Body b2body;
    public float PPM = 100;

    public Player(World world){
        this.world = world;
         definePlayer();
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/PPM, 32/PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14/PPM, 30/PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
