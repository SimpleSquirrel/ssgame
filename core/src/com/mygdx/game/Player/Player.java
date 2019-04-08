package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import static com.mygdx.game.MyGame.PPM;

public class Player extends Sprite {
    public enum State { RUNNING, FALLING, JUMPING, STANDING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion robotStand;
    private Animation robotRun;
    private TextureAtlas atlas;
    private float stateTimer;
    private boolean runningRight;
    public Sprite spriteRobotStand;
    public Sprite spriteRobotRun1;
    public Sprite spriteRobotRun2;
    public Sprite spriteRobotRun3;
    public Sprite spriteRobotRun4;
    public Sprite spriteRobotRun5;

    public Player(World world){
        atlas = new TextureAtlas("Animations/Robot.txt");
        spriteRobotStand = atlas.createSprite("Анимация ног1");
        spriteRobotRun1 = atlas.createSprite("Анимация ног1");
        spriteRobotRun2 = atlas.createSprite("Анимация ног2");
        spriteRobotRun3 = atlas.createSprite("Анимация ног3");
        spriteRobotRun4 = atlas.createSprite("Анимация ног4");
        spriteRobotRun5 = atlas.createSprite("Анимация ног5");
        setBounds(0, 0, 32/PPM, 64/PPM);
        setRegion(spriteRobotRun1);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        Array<Sprite> frames = new Array<Sprite>();
        frames.add(spriteRobotRun1);
        frames.add(spriteRobotRun2);
        frames.add(spriteRobotRun3);
        frames.add(spriteRobotRun4);
        frames.add(spriteRobotRun5);
        robotRun = new Animation(0.1f, frames);
        frames.clear();
        this.world = world;
        definePlayer();
    }

    public void update(float delta){
        setPosition((b2body.getPosition().x - 14/PPM), (b2body.getPosition().y - 30/PPM));
        setRegion(getFrame(delta));
    }

    public Sprite getFrame(float delta){
        currentState = getState();
        Sprite sprite;
        switch (currentState){
            case JUMPING:
                sprite = spriteRobotRun3;
                break;
            case FALLING:
                sprite = spriteRobotRun5;
                break;
            case STANDING:
                sprite = spriteRobotRun1;
                break;
            case RUNNING:
                sprite = (Sprite) robotRun.getKeyFrame(stateTimer, true);
                break;
            default:
                sprite = spriteRobotRun1;
        }
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !sprite.isFlipX()){
            sprite.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && sprite.isFlipX()){
            sprite.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return sprite;
    }
    public State getState(){
        if(b2body.getLinearVelocity().y > 0){
            return State.JUMPING;
        }
        else if(b2body.getLinearVelocity().y < 0){
            return State.FALLING;
        }
        else if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else {
            return State.STANDING;
        }
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/PPM, 32/PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14/PPM, 30/PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
        EdgeShape boots = new EdgeShape();
        boots.set(new Vector2(-13/PPM, -30/PPM), new Vector2(13/PPM, -30/PPM));
        fdef.shape = boots;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("boots");
    }
}
