package com.mygdx.game.Levels.Level7;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Objects.Circular;
import com.mygdx.game.Objects.Ground;
import com.mygdx.game.Objects.Sensor;
import com.mygdx.game.Objects.Walls;

public class WorldCreatorLevel7 {
    World world;
    TiledMap map;

    public WorldCreatorLevel7(World world, TiledMap map) {
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
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

            new Circular(world, map, ellipse);
        }
    }
}
