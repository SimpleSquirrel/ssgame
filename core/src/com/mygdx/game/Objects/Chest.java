package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.MyGame;

import static com.mygdx.game.MyGame.*;

public class Chest extends Sprite {
    public enum State {OPENED, CLOSED}
    private MyGame game;
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private Sprite chestOpened;
    private Sprite chestClosed;
    private TextureAtlas atlas;
    private float stateTimer;
    public boolean isTouched;
    private boolean JastOpen=true;
    private boolean isFlip;
    private boolean getWeapon;
    private int weapon;
    private int counter;

    public Chest(World world, float x, float y, float CheckX, float CheckY, boolean flip, MyGame game, boolean isOpened) {
        this.world = world;
        this.game = game;
        isFlip = flip;
        atlas = new TextureAtlas("Objects/WoodenChest.txt");
        chestClosed = atlas.createSprite("ChestClosed");
        chestOpened = atlas.createSprite("ChestOpen");
        if(isFlip){
            chestClosed.flip(true, false);
            chestOpened.flip(true, false);
        }
        setBounds(0, 0, 32 / PPM, 32 / PPM);
        if(isOpened) {
            counter = 1;
            isTouched = true;
            currentState = State.OPENED;
            previousState = State.OPENED;
        }
        else {
            counter = 0;
            isTouched = false;
            currentState = State.CLOSED;
            previousState = State.CLOSED;
        }
        stateTimer = 0;
        getWeapon = false;
        defineChest(x, y, CheckX, CheckY);
    }

    public void update(float delta){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getSprite(delta));
        if(counter == 0) {
            if (getWeapon) {
                System.out.println("asa");
                weapon = (int) (Math.random() * 12 + 1);
                addWeapon(weapon);
                counter++;
                getWeapon = false;
            }
        }
    }

    public Sprite getSprite(float delta){
        currentState = getState();
        Sprite sprite;
        switch (currentState){
            case OPENED:
                sprite = chestOpened;
                if(JastOpen) {
                    HUD.SCORE += 200;
                    JastOpen=false;
                }
                break;
            case CLOSED:
                sprite = chestClosed;
                break;
            default:
                sprite = chestClosed;
        }
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return sprite;
    }

    public State getState(){
        if(isTouched){
            return State.OPENED;
        }
        else {
            return State.CLOSED;
        }
    }

    public void defineChest(float x, float y, float CheckX, float CheckY){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x + CheckX, y + CheckY);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14/PPM, 13/PPM);
        fdef.filter.categoryBits = CHEST_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void contact(){
        isTouched = true;
        getWeapon = true;
    }

    public boolean thisChestIsTouched(){
        return isTouched;
    }

    public void addWeapon(int weapon){
        switch (weapon){
            case 1:
                if(!game.preferences.getBoolean("doubleGun")) {
                    game.preferences.putBoolean("doubleGun", true);
                    game.preferences.flush();
                    System.out.println("1");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 2:
                if(!game.preferences.getBoolean("tripleGun")) {
                    game.preferences.putBoolean("tripleGun", true);
                    game.preferences.flush();
                    System.out.println("2");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 3:
                if(!game.preferences.getBoolean("quadraGun")) {
                    game.preferences.putBoolean("quadraGun", true);
                    game.preferences.flush();
                    System.out.println("3");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 4:
                if(!game.preferences.getBoolean("pentaGun")) {
                    game.preferences.putBoolean("pentaGun", true);
                    game.preferences.flush();
                    System.out.println("4");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 5:
                if(!game.preferences.getBoolean("fastGun")) {
                    game.preferences.putBoolean("fastGun", true);
                    game.preferences.flush();
                    System.out.println("5");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 6:
                if(!game.preferences.getBoolean("doubleFastGun")) {
                    game.preferences.putBoolean("doubleFastGun", true);
                    game.preferences.flush();
                    System.out.println("6");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 7:
                if(!game.preferences.getBoolean("tripleFastGun")) {
                    game.preferences.putBoolean("tripleFastGun", true);
                    game.preferences.flush();
                    System.out.println("7");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 8:
                if(!game.preferences.getBoolean("quadraFastGun")) {
                    game.preferences.putBoolean("quadraFastGun", true);
                    game.preferences.flush();
                    System.out.println("8");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 9:
                if(!game.preferences.getBoolean("veryFastGun")) {
                    game.preferences.putBoolean("veryFastGun", true);
                    game.preferences.flush();
                    System.out.println("9");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 10:
                if(!game.preferences.getBoolean("veryFastDoubleGun")) {
                    game.preferences.putBoolean("veryFastDoubleGun", true);
                    game.preferences.flush();
                    System.out.println("10");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 11:
                if(!game.preferences.getBoolean("ultraFastGun")) {
                    game.preferences.putBoolean("ultraFastGun", true);
                    game.preferences.flush();
                    System.out.println("11");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
            case 12:
                if(!game.preferences.getBoolean("shotgun")) {
                    game.preferences.putBoolean("shotgun", true);
                    game.preferences.flush();
                    System.out.println("12");
                    break;
                }
                else {
                    weapon = (int) (Math.random() * 12 + 1);
                    addWeapon(weapon);
                    break;
                }
        }
    }
}
