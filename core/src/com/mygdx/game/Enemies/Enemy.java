package com.mygdx.game.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Enemy extends Sprite {
    protected World world;
    public Body b2body;
    public boolean attack;
    protected boolean isFlip;
    protected int HP;
    protected Vector2 velocity;
    protected float check;
    public static boolean rageActive;
    protected final static float FOURtimer = 1f;
    public static boolean FOURboolean;
    protected int counter;
    protected Sound explosion = Gdx.audio.newSound(Gdx.files.internal("Sound/explosion.mp3"));
    public Enemy(World world, float x, float y, boolean flip){
        isFlip = flip;
        this.world = world;
        setPosition(x, y);
        rageActive = false;
        FOURboolean = false;
        attack = false;
        counter = 1;
        defineEnemy();
    }

    protected abstract void defineEnemy();
    public abstract void bulletHit();
    public abstract void swordHit();
    public abstract void fire();
    public abstract void deleted();
    public abstract void reverseVelocity(boolean x, boolean y);
    public abstract boolean isDestroyed();
}
