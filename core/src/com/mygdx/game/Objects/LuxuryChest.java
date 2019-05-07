package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.MiniGames.GameOfFifteen;
import com.mygdx.game.MyGame;

import javax.swing.*;
import java.awt.*;

import static com.mygdx.game.MyGame.*;

public class LuxuryChest extends Sprite {
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
    private boolean runMiniGame;
    private boolean created15;
    private boolean isSolved;
    JFrame frame = new JFrame();
    private GameOfFifteen gameOfFifteen = new GameOfFifteen(4, 550, 30);

    public LuxuryChest(World world, float x, float y, float CheckX, float CheckY, boolean flip, MyGame game, boolean isSolved) {
        this.world = world;
        this.game = game;
        this.isSolved = isSolved;
        isFlip = flip;
        atlas = new TextureAtlas("Objects/LuxuryChest.txt");
        chestClosed = atlas.createSprite("LuxChestClosed");
        chestOpened = atlas.createSprite("LuxChestOpened");
        if(isFlip){
            chestClosed.flip(true, false);
            chestOpened.flip(true, false);
        }
        setBounds(0, 0, 32 / PPM, 32 / PPM);
        if(isSolved) {
            isTouched = true;
            currentState = State.OPENED;
            previousState = State.OPENED;
        }
        else {
            isTouched = false;
            currentState = State.CLOSED;
            previousState = State.CLOSED;
        }
        stateTimer = 0;
        runMiniGame = false;
        created15 = false;
        defineChest(x, y, CheckX, CheckY);
    }

    public void update(float delta){
        stateTimer += delta;
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getSprite(delta));
        if(runMiniGame && !isSolved){
            stateTimer = 0;
            created15 = true;
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("Game of Fifteen");
            frame.setResizable(false);
            frame.add(gameOfFifteen, BorderLayout.CENTER);
            frame.pack();
            // center on the screen
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            runMiniGame = false;
        }
        if(created15 && stateTimer > 300f){
            frame.dispose();
        }
        if(gameOfFifteen.isSolved()){
            game.preferences.putBoolean("fastShotgun", true);
            game.preferences.flush();
            frame.dispose();
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
                    runMiniGame = true;
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
        fdef.filter.categoryBits = LUXURY_CHEST_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void contact(){
        isTouched = true;
    }

    public boolean thisChestIsOpened(){
        return gameOfFifteen.isSolved();
    }
}
