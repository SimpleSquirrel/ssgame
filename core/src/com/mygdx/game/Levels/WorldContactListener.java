package com.mygdx.game.Levels;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Enemies.Enemy;
import com.mygdx.game.Enemies.Pie;
import com.mygdx.game.Enemies.Wonder;
import com.mygdx.game.Objects.*;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.MyGame.*;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case BULLET_BIT | ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == ENEMY_BIT){
                    ((Enemy)fixA.getUserData()).bulletHit();
                    ((Bullet)fixB.getUserData()).deleteBullet();
                }
                else {
                    ((Enemy)fixB.getUserData()).bulletHit();
                    ((Bullet)fixA.getUserData()).deleteBullet();
                }
                break;
            case BULLET_BIT | GROUND_BIT:
                if(fixA.getFilterData().categoryBits == BULLET_BIT){
                    ((Bullet)fixA.getUserData()).deleteBullet();
                }
                else {
                    ((Bullet)fixB.getUserData()).deleteBullet();
                }
                break;
            case BULLET_BIT | PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == BULLET_BIT){
                    ((Bullet)fixA.getUserData()).deleteBullet();
                    ((Player)fixB.getUserData()).bulletHit();
                }
                else {
                    ((Bullet)fixB.getUserData()).deleteBullet();
                    ((Player)fixA.getUserData()).bulletHit();
                }
                break;
            case PLAYER_BIT | SENSOR_BIT:
                if(fixA.getFilterData().categoryBits == SENSOR_BIT){
                    ((Enemy)fixA.getUserData()).fire();
                }
                else {
                    ((Enemy)fixB.getUserData()).fire();
                }
                break;
            case SWORD_BIT | ENEMY_BIT:
                if((fixA.getFilterData().categoryBits == SWORD_BIT)){
                    ((Enemy)fixB.getUserData()).swordHit();
                }
                else{
                    ((Enemy)fixA.getUserData()).swordHit();
                }
                break;
            case BULLET_BIT:
                ((Bullet)fixA.getUserData()).deleteBullet();
                ((Bullet)fixB.getUserData()).deleteBullet();
                break;
            case PLAYER_BIT | CHEST_BIT:
                if(fixA.getFilterData().categoryBits == CHEST_BIT){
                    ((Chest)fixA.getUserData()).contact();
                }
                else {
                    ((Chest)fixB.getUserData()).contact();
                }
                break;
            case PLAYER_BIT | LUXURY_CHEST_BIT:
                if(fixA.getFilterData().categoryBits == LUXURY_CHEST_BIT){
                    ((LuxuryChest)fixA.getUserData()).contact();
                }
                else {
                    ((LuxuryChest)fixB.getUserData()).contact();
                }
                break;
            case BULLET_BIT | CHEST_BIT:
                if(fixA.getFilterData().categoryBits == BULLET_BIT){
                    ((Bullet)fixA.getUserData()).deleteBullet();
                }
                else {
                    ((Bullet)fixB.getUserData()).deleteBullet();
                }
                break;
            case BULLET_BIT | PORTAL_BIT:
                if(fixA.getFilterData().categoryBits == BULLET_BIT){
                    ((Bullet)fixA.getUserData()).deleteBullet();
                }
                else {
                    ((Bullet)fixB.getUserData()).deleteBullet();
                }
                break;
            case PLAYER_BIT | PORTAL_BIT:
                if(fixA.getFilterData().categoryBits == PORTAL_BIT){
                    ((Portal)fixA.getUserData()).contact();
                }
                else {
                    ((Portal)fixB.getUserData()).contact();
                }
                break;
            case SPIKE_BIT | PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player)fixA.getUserData()).spikeHit();
                }
                else {
                    ((Player)fixB.getUserData()).spikeHit();
                }
                break;
            case ENEMY_BIT | GROUND_BIT:
                if (fixA.getFilterData().categoryBits == ENEMY_BIT){
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                }
                else {
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                }
                break;
            case ENEMY_BIT | PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player)fixA.getUserData()).swordHit();
                    ((Player)fixA.getUserData()).hitByEnemy(fixB.getBody().getLinearVelocity().x);
                    ((Enemy)fixB.getUserData()).playSound();
                }
                else {
                    ((Player)fixB.getUserData()).swordHit();
                    ((Player)fixB.getUserData()).hitByEnemy(fixA.getBody().getLinearVelocity().x);
                    ((Enemy)fixA.getUserData()).playSound();
                }
                break;
            case SHIELD_BIT | BULLET_BIT:
                if(fixA.getFilterData().categoryBits == SHIELD_BIT){
                    ((Player)fixA.getUserData()).destroyShield();
                    ((Bullet)fixB.getUserData()).deleteBullet();
                }
                else {
                    ((Player)fixB.getUserData()).destroyShield();
                    ((Bullet)fixA.getUserData()).deleteBullet();
                }
                break;
            case SHIELD_BIT | ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == SHIELD_BIT){
                    ((Player)fixA.getUserData()).destroyShield();
                    ((Player)fixA.getUserData()).hitByEnemy(fixB.getBody().getPosition().x);
                }
                else {
                    ((Player)fixB.getUserData()).destroyShield();
                    ((Player)fixB.getUserData()).hitByEnemy(fixA.getBody().getPosition().x);
                }
                break;
            case BOSS_BIT | PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player)fixA.getUserData()).vedmeDIOhit();
                    ((Enemy)fixB.getUserData()).bulletHit();
                }
                else {
                    ((Player)fixB.getUserData()).vedmeDIOhit();
                    ((Enemy)fixA.getUserData()).bulletHit();
                }
                break;
            case PLAYER_BIT | WONDER_BIT:
                if(fixA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player)fixA.getUserData()).wonderTouch();
                    ((Wonder)fixB.getUserData()).playerTouch();
                }
                else {
                    ((Player)fixB.getUserData()).wonderTouch();
                    ((Wonder)fixA.getUserData()).playerTouch();
                }
                break;
            case WONDER_BIT | GROUND_BIT:
                if (fixA.getFilterData().categoryBits == WONDER_BIT){
                    ((Wonder)fixA.getUserData()).reverseVelocity(true, false);
                }
                else {
                    ((Wonder)fixB.getUserData()).reverseVelocity(true, false);
                }
                break;
            case PIE_BIT | ROCKET_BIT:
                if(fixA.getFilterData().categoryBits == BULLET_BIT){
                    ((Rocket)fixA.getUserData()).deleteBullet();
                    ((Pie)fixB.getUserData()).rocketHit();
                    ((Pie)fixB.getUserData()).playSound();
                }
                else {
                    ((Rocket)fixB.getUserData()).deleteBullet();
                    ((Pie)fixA.getUserData()).rocketHit();
                    ((Pie)fixA.getUserData()).playSound();
                }
                break;
            case ROCKET_BIT | SWORD_BIT:
                if(fixA.getFilterData().categoryBits == ROCKET_BIT){
                    ((Rocket)fixA.getUserData()).attackPie();
                }
                else {
                    ((Rocket)fixB.getUserData()).attackPie();
                }
                break;
            case ROCKET_BIT | PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player)fixA.getUserData()).spikeHit();
                    ((Rocket)fixB.getUserData()).deleteBullet();
                }
                else {
                    ((Player)fixB.getUserData()).spikeHit();
                    ((Rocket)fixA.getUserData()).deleteBullet();
                }
                break;
            case PIE_BIT | PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player)fixA.getUserData()).spikeHit();
                }
                else {
                    ((Player)fixB.getUserData()).spikeHit();
                }
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
