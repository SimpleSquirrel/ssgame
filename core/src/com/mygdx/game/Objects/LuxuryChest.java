package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.MiniGames.GameOfFifteen;

import javax.swing.*;
import java.awt.*;

import static com.mygdx.game.MyGame.*;

public class LuxuryChest extends Sprite {
    public enum State {OPENED, CLOSED}

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

    public LuxuryChest(World world, float x, float y, float CheckX, float CheckY, boolean flip) {
        this.world = world;
        isFlip = flip;
        atlas = new TextureAtlas("Objects/LuxuryChest.txt");
        chestClosed = atlas.createSprite("LuxChestClosed");
        chestOpened = atlas.createSprite("LuxChestOpened");
        if(isFlip){
            chestClosed.flip(true, false);
            chestOpened.flip(true, false);
        }
        setBounds(0, 0, 32 / PPM, 32 / PPM);
        currentState = State.CLOSED;
        previousState = State.CLOSED;
        stateTimer = 0;
        isTouched = false;
        runMiniGame = false;
        defineChest(x, y, CheckX, CheckY);
    }

    public void update(float delta){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getSprite(delta));
        if(runMiniGame){
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("Game of Fifteen");
            frame.setResizable(false);
            frame.add(new GameOfFifteen(4, 550, 30), BorderLayout.CENTER);
            frame.pack();
            // center on the screen
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            runMiniGame = false;
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
}
