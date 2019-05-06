package com.mygdx.game.Objects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Levels.InteractiveSensorObject;

public class Sensor extends InteractiveSensorObject {

    public Sensor(World world, TiledMap map, Rectangle rectangle) {
        super(world, map, rectangle);
        fixture.setUserData(this);
    }
}
