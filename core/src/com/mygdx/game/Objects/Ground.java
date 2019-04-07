package com.mygdx.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Levels.Level1.InteractiveTileObject;

import static com.mygdx.game.MyGame.PPM;

public class Ground extends InteractiveTileObject {
    public Ground(World world, TiledMap map, Rectangle rectangle){
        super(world, map, rectangle);
        fixture.setUserData(this);
    }

    @Override
    public void isGrounded() {
        Gdx.app.log("Ground", "Collision");
    }
}
