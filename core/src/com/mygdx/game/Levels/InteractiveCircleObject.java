package com.mygdx.game.Levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.MyGame.*;

public abstract class InteractiveCircleObject {
    protected World world;
    protected TiledMap map;
    protected Ellipse ellipse;
    protected Body body;
    protected Fixture fixture;
    public InteractiveCircleObject(World world, TiledMap map, Ellipse ellipse){
        this.world = world;
        this.map = map;
        this.ellipse = ellipse;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((ellipse.x + ellipse.width/2)/PPM, (ellipse.y)/PPM);
        body = world.createBody(bodyDef);

        circleShape.setRadius(ellipse.height/2/PPM);
        fixtureDef.filter.categoryBits = SPIKE_BIT;
        fixtureDef.filter.maskBits = PLAYER_BIT | GROUND_BIT | SPIKE_BIT;
        fixtureDef.shape = circleShape;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        fixture = body.createFixture(fixtureDef);
    }

    public void setCategoryBits(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setUserData(filter);
    }
}
