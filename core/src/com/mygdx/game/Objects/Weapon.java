package com.mygdx.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGame;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.MyGame.PPM;
import static com.mygdx.game.MyGame.playerBullets;

public class Weapon {
    private MyGame game;
    private String currentGun;
    private Bullet bullet;
    private World world;
    private int bulletCounter;
    private int bullets;
    private float bulletReload;
    private float timer;
    private Vector2 bulletSpeed;
    private Vector2 bulletSpeedLeft;
    private Vector2 position;
    private boolean isShot;
    private float[] yVelocity;

    public Weapon(MyGame game, World world) {
        this.game = game;
        this.world = world;
        currentGun = game.preferences.getString("weapon");
        isShot = false;
        bullets = 0;
        getGun();
    }

    public void update(float delta, Vector2 position){
        timer += delta;
        this.position = position;
        input();
        updateBullet();
    }

    private void getGun() {
        if (currentGun.equals("gun")) {
            bulletSpeed = new Vector2(4f, 0);
            bulletSpeedLeft = new Vector2(-4f, 0);
            bulletCounter = 1;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("doubleGun")){
            bulletSpeed = new Vector2(4f, 0);
            bulletSpeedLeft = new Vector2(-4f, 0);
            bulletCounter = 2;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("tripleGun")){
            bulletSpeed = new Vector2(4f, 0);
            bulletSpeedLeft = new Vector2(-4f, 0);
            bulletCounter = 3;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("quadraGun")){
            bulletSpeed = new Vector2(4f, 0);
            bulletSpeedLeft = new Vector2(-4f, 0);
            bulletCounter = 4;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("pentaGun")){
            bulletSpeed = new Vector2(4f, 0);
            bulletSpeedLeft = new Vector2(-4f, 0);
            bulletCounter = 5;
            bulletReload = 0.1f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("fastGun")){
            bulletSpeed = new Vector2(8f, 0);
            bulletSpeedLeft = new Vector2(-8f, 0);
            bulletCounter = 1;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("doubleFastGun")){
            bulletSpeed = new Vector2(8f, 0);
            bulletSpeedLeft = new Vector2(-8f, 0);
            bulletCounter = 2;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("tripleFastGun")){
            bulletSpeed = new Vector2(8f, 0);
            bulletSpeedLeft = new Vector2(-8f, 0);
            bulletCounter = 3;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("quadraFastGun")){
            bulletSpeed = new Vector2(8f, 0);
            bulletSpeedLeft = new Vector2(-8f, 0);
            bulletCounter = 4;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("shotgun")){
            bulletSpeed = new Vector2(4f, 0);
            bulletSpeedLeft = new Vector2(-4f, 0);
            bulletCounter = 7;
            yVelocity = new float[bulletCounter];
            bulletReload = 0f;
            yVelocity[0] = -3f;
            yVelocity[1] = -2f;
            yVelocity[2] = -1f;
            yVelocity[3] = 0f;
            yVelocity[4] = 1f;
            yVelocity[5] = 2f;
            yVelocity[6] = 3f;
        }
        else if(currentGun.equals("veryFastGun")){
            bulletSpeed = new Vector2(12f, 0);
            bulletSpeedLeft = new Vector2(-12f, 0);
            bulletCounter = 1;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("veryFastDoubleGun")){
            bulletSpeed = new Vector2(12f, 0);
            bulletSpeedLeft = new Vector2(-12f, 0);
            bulletCounter = 2;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("ultraFastGun")){
            bulletSpeed = new Vector2(15f, 0);
            bulletSpeedLeft = new Vector2(-15f, 0);
            bulletCounter = 1;
            bulletReload = 0f;
            yVelocity = new float[bulletCounter];
            for (int i = 0; i < bulletCounter; i++){
                yVelocity[i] = 0;
            }
        }
        else if(currentGun.equals("fastShotgun")){
            bulletSpeed = new Vector2(8f, 0);
            bulletSpeedLeft = new Vector2(-8f, 0);
            bulletCounter = 7;
            yVelocity = new float[bulletCounter];
            bulletReload = 0f;
            yVelocity[0] = -3f;
            yVelocity[1] = -2f;
            yVelocity[2] = -1f;
            yVelocity[3] = 0f;
            yVelocity[4] = 1f;
            yVelocity[5] = 2f;
            yVelocity[6] = 3f;
        }
    }

    private void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            if(Player.runningRight && bullets < bulletCounter && timer >= bulletReload && bullets == 0) {
                isShot = true;
                while (bullets < bulletCounter) {
                    bullet = new Bullet(world, position.x, position.y, 20/PPM + bullets*20/PPM, 5/PPM + yVelocity[bullets]/PPM);
                    bullet.bulletBody.setLinearVelocity(bulletSpeed.x, yVelocity[bullets]);
                    playerBullets.add(bullet);
                    bullets++;
                }
            }
            else if(!Player.runningRight && bullets < bulletCounter && timer >= bulletReload && bullets == 0) {
                isShot = true;
                while (bullets < bulletCounter) {
                    bullet = new Bullet(world, position.x, position.y, -20/PPM - bullets*20/PPM, 5/PPM + yVelocity[bullets]/PPM);
                    bullet.bulletBody.setLinearVelocity(bulletSpeedLeft.x, yVelocity[bullets]);
                    playerBullets.add(bullet);
                    bullets++;
                }
            }
        }
    }
    private void updateBullet(){
        if(isShot){
            bullet.update();
            for (Bullet bullet:playerBullets){
                bullet.update();
                if(bullet.isDestroyed()){
                    playerBullets.removeValue(bullet, true);
                    bullets--;
                }
            }
        }
    }
    public void drawBullet(){
        for (Bullet bullet:playerBullets){
            if(!(bullet.bulletBody.getPosition().y < -10/PPM || bullet.bulletBody.getPosition().y > 1000/PPM || bullet.bulletBody.getPosition().x < 0 || bullet.bulletBody.getPosition().x > 1600/PPM)) {
                bullet.draw(game.batch);
            }
        }
    }
}