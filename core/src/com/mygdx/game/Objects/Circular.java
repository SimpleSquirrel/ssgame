package com.mygdx.game.Objects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Levels.InteractiveCircleObject;

import static com.mygdx.game.MyGame.SPIKE_BIT;

public class Circular extends InteractiveCircleObject {

    public Circular(World world, TiledMap map, Ellipse ellipse) {
        super(world, map, ellipse);
        fixture.setUserData(this);
        setCategoryBits(SPIKE_BIT);
    }
}
