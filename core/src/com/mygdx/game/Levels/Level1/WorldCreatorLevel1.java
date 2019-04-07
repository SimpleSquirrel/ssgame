package com.mygdx.game.Levels.Level1;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class WorldCreatorLevel1 {
    private float PPM = 100;
    public WorldCreatorLevel1(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/PPM, (rect.getY() + rect.getHeight()/2)/PPM);
            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/PPM, (rect.getHeight()/2)/PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/PPM, (rect.getY() + rect.getHeight()/2)/PPM);
            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/PPM, (rect.getHeight()/2)/PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}
