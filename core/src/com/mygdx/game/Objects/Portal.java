package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.MyGame.*;

public class Portal extends Sprite {
    private Chest chest;
    public World world;
    public Body b2body;
    private Texture texturePortal;
    public static Sprite spritePortal;
    public boolean isTouched;
    private boolean isFlip;

    public Portal(World world, float x, float y, float CheckX, float CheckY, boolean flip) {
        this.world = world;
        isFlip = flip;
        texturePortal = new Texture("Objects/Portal.png");
        spritePortal = new Sprite(texturePortal);
        if(isFlip){
            spritePortal.flip(true, false);
        }
        setBounds(0, 0, 32 / PPM, 58 / PPM);
        isTouched = false;
        definePortal(x, y, CheckX, CheckY);
    }

    public void update(){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(spritePortal);
    }

    public void definePortal(float x, float y, float CheckX, float CheckY){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x + CheckX, y + CheckY);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14/PPM, 28/PPM);
        fdef.filter.categoryBits = PORTAL_BIT;
        fdef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void contact(){
        isTouched = true;
    }
}
