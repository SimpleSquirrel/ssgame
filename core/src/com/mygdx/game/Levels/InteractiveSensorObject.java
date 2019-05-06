package com.mygdx.game.Levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.MyGame.*;

public abstract class InteractiveSensorObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle rectangle;
    protected Body body;
    protected Fixture fixture;

    public InteractiveSensorObject(World world, TiledMap map, Rectangle rectangle){
        this.world = world;
        this.map = map;
        this.rectangle = rectangle;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rectangle.getX() + rectangle.getWidth()/2)/PPM,(rectangle.getY() + rectangle.getHeight()/2)/PPM);
        body = world.createBody(bdef);

        shape.setAsBox((rectangle.getWidth()/2)/PPM, (rectangle.getHeight()/2)/PPM);
        fdef.filter.categoryBits = GROUND_BIT;
        fdef.filter.maskBits = WALKING_ENEMY_BIT | ENEMY_BIT;
        fdef.shape = shape;
        body.createFixture(fdef);
        fixture = body.createFixture(fdef);
    }

    public void setCategoryBits(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setUserData(filter);
    }
}
