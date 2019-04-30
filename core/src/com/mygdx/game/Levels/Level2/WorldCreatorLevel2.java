package com.mygdx.game.Levels.Level2;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Objects.Ground;
import com.mygdx.game.Objects.Walls;

public class WorldCreatorLevel2 {
    World world;
    TiledMap map;

    public WorldCreatorLevel2(World world, TiledMap map) {
        this.world = world;
        this.map = map;
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(world, map, rect);
        }
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Walls(world, map, rect);
        }
    }
}
