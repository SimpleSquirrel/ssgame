package com.mygdx.game.Levels.Level1;

import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

import static com.mygdx.game.MyGame.PPM;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle rectangle;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(GameScreenLevel1 level1, Rectangle rectangle){
        this.world = level1.getWorld();
        this.map = level1.getMap();
        this.rectangle = rectangle;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rectangle.getX() + rectangle.getWidth()/2)/PPM,(rectangle.getY() + rectangle.getHeight()/2)/PPM);
        body = world.createBody(bdef);

        shape.setAsBox((rectangle.getWidth()/2)/PPM, (rectangle.getHeight()/2)/PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        fixture = body.createFixture(fdef);
    }

    public abstract void isGrounded();
}
