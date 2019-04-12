package com.mygdx.game.Objects;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level1.InteractiveTileObject;

import static com.mygdx.game.MyGame.CHEST_BIT;

public class Chest extends InteractiveTileObject {
    public Chest(GameScreenLevel1 level1, Rectangle rectangle){
        super(level1, rectangle);
        fixture.setUserData(this);
        setCategoryBits(CHEST_BIT);
    }

}
