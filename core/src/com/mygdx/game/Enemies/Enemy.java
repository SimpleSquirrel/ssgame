package com.mygdx.game.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;

public abstract class Enemy extends Sprite {
    protected World world;
    protected GameScreenLevel1 screen;
    public Body b2body;
    protected boolean isFlip;
    protected int HP;
    protected Vector2 velocity;
    protected float check;
    public Enemy(World world, float x, float y, boolean flip){
        isFlip = flip;
        this.world = world;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(2, 0);
    }

    protected abstract void defineEnemy();
    public abstract void bulletHit();
    public abstract void swordHit();
    public abstract void fire();
    public abstract void deleted();
    public void reverseVelocity(boolean x, boolean y){
        if(x){
            if(velocity.x > 0){
                check = velocity.x;
                velocity.x -= 2;
            }
            else if (velocity.x == 0 && check > 0){
                velocity.x -= 2;
            }
            else if (velocity.x < 0){
                check = velocity.x;
                velocity.x += 2;
            }
            else if (velocity.x == 0 && check < 0){
                velocity.x += 2;
            }
        }
        if(y){
            velocity.y = -velocity.y;
        }
    }
}
