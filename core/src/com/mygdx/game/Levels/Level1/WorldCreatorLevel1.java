package com.mygdx.game.Levels.Level1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Objects.Ground;
import com.mygdx.game.Objects.Walls;



public class WorldCreatorLevel1 extends Sprite {
    World world;
    TiledMap map;
    public WorldCreatorLevel1(World world, TiledMap map){
        this.world = world;
        this.map = map;
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(world, map, rect);
        }
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Walls(world, map, rect);
        }
    }
}
