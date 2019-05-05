package com.mygdx.game.Familiars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Enemies.Enemy;
import com.mygdx.game.MyGame;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.MyGame.enemies;

public class Familiar extends Sprite {
    MyGame game;
    Player player;
    public enum Familiar1{HEAL, RAGE, SHIELD, FOUR}
    public enum Familiar2{HEAL, RAGE, SHIELD, FOUR, NONE}
    public Familiar1 currentFamiliar1;
    public Familiar2 currentFamiliar2;
    private final static float couldownRage = 10f;
    private final static float couldownHeal = 5f;
    private final static float couldownShield = 5f;
    private final static float couldownFOUR = 4f;
    private float timer1;
    private float timer2;
    private boolean stop;
    private final float FOURtimer = 0.5f;
    public Familiar(MyGame game, Player player){
        this.game = game;
        this.player = player;
        timer1 = 0;
        timer2 = 0;
        stop = false;
        switch (game.preferences.getInteger("familiar1")){
            case 1:
                currentFamiliar1 = Familiar1.HEAL;
                break;
            case 2:
                currentFamiliar1 = Familiar1.SHIELD;
                break;
            case 3:
                currentFamiliar1 = Familiar1.RAGE;
                break;
            case 4:
                currentFamiliar1 = Familiar1.FOUR;
                break;
        }
        switch (game.preferences.getInteger("familiar2")){
            case 1:
                currentFamiliar2 = Familiar2.HEAL;
                break;
            case 2:
                currentFamiliar2 = Familiar2.SHIELD;
                break;
            case 3:
                currentFamiliar2 = Familiar2.RAGE;
                break;
            case 4:
                currentFamiliar2 = Familiar2.FOUR;
                break;
            case 5:
                currentFamiliar2 = Familiar2.NONE;
                break;
        }
    }
    public void update(float delta){
        timer1 += delta;
        timer2 += delta;
        if(currentFamiliar1 != Familiar1.FOUR) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                getFamiliar1();
            }
        }
        else {
            getFamiliar1();
        }
        if(currentFamiliar2 != Familiar2.FOUR) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                getFamiliar2();
            }
        }
        else {
            getFamiliar2();
        }
        if(currentFamiliar2 == Familiar2.RAGE && Enemy.rageActive){
            if (timer2 >= 3f){
                Enemy.rageActive = false;
            }
        }
        if(currentFamiliar1 == Familiar1.RAGE && Enemy.rageActive){
            if (timer2 >= 3f){
                Enemy.rageActive = false;
            }
        }
        if(stop){
            if(timer2 <= FOURtimer) {
                for (Enemy enemy : enemies) {
                    enemy.b2body.setLinearVelocity(0, 0);
                    enemy.attack = false;
                }
            }
        }
    }
    private void getFamiliar1(){
        switch (currentFamiliar1){
            case HEAL:
                if(timer1 >= couldownHeal) {
                    player.heal();
                    timer1 = 0;
                }
                break;
            case RAGE:
                if(timer1 >= couldownRage) {
                    Enemy.rageActive = true;
                    timer1 = 0;
                }
                break;
            case FOUR:
                if(timer1 >= couldownFOUR) {
                    Enemy.FOURboolean = true;
                    timer1 = 0;
                }
                break;
            case SHIELD:
                if(timer1 >= couldownShield) {
                    player.shield();
                    timer1 = 0;
                }
                break;
        }
    }
    private void getFamiliar2(){
        switch (currentFamiliar2){
            case HEAL:
                if(timer2 >= couldownHeal) {
                    player.heal();
                    timer2 = 0;
                }
                break;
            case RAGE:
                if(timer2 >= couldownRage) {
                    Enemy.rageActive = true;
                    timer2 = 0;
                }
                break;
            case FOUR:
                if(timer2 >= couldownFOUR) {
                    System.out.println("4");
                    stop = true;
                    timer2 = 0;
                }
                break;
            case SHIELD:
                if(timer2 >= couldownShield) {
                    player.shield();
                    timer2 = 0;
                }
            case NONE:
                break;
        }
    }

}
