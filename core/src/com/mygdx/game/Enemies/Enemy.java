package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;

public abstract class Enemy extends Sprite {
    protected World world;
    protected GameScreenLevel1 screen;
    public Body b2body;
    protected int HP;
    public Enemy(World world, float x, float y){
        this.world = world;
        setPosition(x, y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
    public abstract void bulletHit();
    public abstract void swordHit();
    public abstract void fire();
    public abstract void deleted();
}
