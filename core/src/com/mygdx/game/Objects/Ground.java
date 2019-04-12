package com.mygdx.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level1.InteractiveTileObject;

import static com.mygdx.game.MyGame.GROUND_BIT;

public class Ground extends InteractiveTileObject {
    public Ground(GameScreenLevel1 level1, Rectangle rectangle){
        super(level1, rectangle);
        fixture.setUserData(this);
        setCategoryBits(GROUND_BIT);
    }
}
