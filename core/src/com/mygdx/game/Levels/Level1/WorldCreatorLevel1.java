package com.mygdx.game.Levels.Level1;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Objects.Chest;
import com.mygdx.game.Objects.Floor;
import com.mygdx.game.Objects.Ground;
import com.mygdx.game.Objects.Walls;



public class WorldCreatorLevel1 {
    public WorldCreatorLevel1(GameScreenLevel1 level1){
        World world = level1.getWorld();
        TiledMap map = level1.getMap();

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Chest(level1, rect);
        }
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(level1, rect);
        }
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Floor(level1, rect);
        }
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Walls(level1, rect);
        }
    }
}
