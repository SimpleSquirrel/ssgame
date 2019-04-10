package com.mygdx.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level1.InteractiveTileObject;

public class Chest extends InteractiveTileObject {
    public Chest(GameScreenLevel1 level1, Rectangle rectangle){
        super(level1, rectangle);
        fixture.setUserData(this);
    }

    @Override
    public void isGrounded() {
        Gdx.app.log("Chest", "Collision");
    }
}
