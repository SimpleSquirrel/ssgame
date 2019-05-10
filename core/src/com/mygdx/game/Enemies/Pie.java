package com.mygdx.game.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Levels.Level10.GameScreenLevel10;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Objects.Rocket;

import static com.mygdx.game.MyGame.*;

public class Pie extends Enemy {
    private Bullet bullet;
    private Cupcake cupcake1;
    private Cupcake cupcake2;
    private Cupcake cupcake3;
    private Cupcake cupcake4;
    private Cupcake cupcake5;
    private Cupcake cupcake6;
    private Cupcake cupcake7;
    private Cupcake cupcake8;
    private Cupcake cupcake9;
    private Cupcake cupcake10;
    private Cupcake cupcake11;
    private Cupcake cupcake12;
    private Cupcake cupcake13;
    private Cupcake cupcake14;
    private Cupcake cupcake15;
    private Cupcake cupcake16;
    private Cupcake cupcake17;
    private Cupcake cupcake18;
    private Cupcake cupcake19;
    private Cupcake cupcake20;
    private Rocket rocket;
    public Array<Rocket> rockets = new Array<Rocket>();
    public Array<Bullet> pieBullets = new Array<Bullet>();
    private Texture textureBullet;
    private Sprite spriteBullet;
    private Texture textureRocket;
    private Sprite spriteRocket;
    private Texture texturePieDamage;
    private Sprite spritePieDamdge;
    private Texture texturePieStand1;
    private Texture texturePieStand2;
    private Texture texturePieStand3;
    private Sprite spritePieStand1;
    private Sprite spritePieStand2;
    private Sprite spritePieStand3;
    private Animation animationPieStand;
    private Texture texturePieAttack1;
    private Texture texturePieAttack2;
    private Texture texturePieAttack3;
    private Texture texturePieAttack4;
    private Texture texturePieAttack5;
    private Texture texturePieAttack6;
    private Texture texturePieAttack7;
    private Sprite spritePieAttack1;
    private Sprite spritePieAttack2;
    private Sprite spritePieAttack3;
    private Sprite spritePieAttack4;
    private Sprite spritePieAttack5;
    private Sprite spritePieAttack6;
    private Sprite spritePieAttack7;
    private Animation animationPieTransformation;
    private Animation animationPieTransformationBack;
    private Texture texturePieRolling1;
    private Texture texturePieRolling2;
    private Sprite spritePieRolling1;
    private Sprite spritePieRolling2;
    private Animation animationPieRolling;
    private Texture texturePieShots1;
    private Texture texturePieShots2;
    private Texture texturePieShots3;
    private Texture texturePieShots4;
    private Texture texturePieShots5;
    private Texture texturePieShots6;
    private Texture texturePieShots7;
    private Texture texturePieShots8;
    private Texture texturePieShots9;
    private Texture texturePieShots10;
    private Texture texturePieShots11;
    private Texture texturePieShots12;
    private Texture texturePieShots13;
    private Sprite spritePieShots1;
    private Sprite spritePieShots2;
    private Sprite spritePieShots3;
    private Sprite spritePieShots4;
    private Sprite spritePieShots5;
    private Sprite spritePieShots6;
    private Sprite spritePieShots7;
    private Sprite spritePieShots8;
    private Sprite spritePieShots9;
    private Sprite spritePieShots10;
    private Sprite spritePieShots11;
    private Sprite spritePieShots12;
    private Sprite spritePieShots13;
    private Animation animationPieShots;
    private Texture texturePieSummoning1;
    private Texture texturePieSummoning2;
    private Texture texturePieSummoning3;
    private Texture texturePieSummoning4;
    private Sprite spritePieSummoning1;
    private Sprite spritePieSummoning2;
    private Sprite spritePieSummoning3;
    private Sprite spritePieSummoning4;
    private Animation animationPieSummoning;
    private Texture texturePieRocket1;
    private Texture texturePieRocket2;
    private Texture texturePieRocket3;
    private Texture texturePieRocket4;
    private Texture texturePieRocketCenter;
    private Texture texturePieRocketUp;
    private Texture texturePieRocketDown;
    private Texture texturePieRocketCenterShot;
    private Texture texturePieRocketUpShot;
    private Texture texturePieRocketShotDown;
    private Sprite spritePieRocket1;
    private Sprite spritePieRocket2;
    private Sprite spritePieRocket3;
    private Sprite spritePieRocket4;
    private Sprite spritePieRocketCenter;
    private Sprite spritePieRocketUp;
    private Sprite spritePieRocketDown;
    private Sprite spritePieRocketCenterShot;
    private Sprite spritePieRocketUpShot;
    private Sprite spritePieRocketShotDown;
    private Animation animationRocketShot;
    private Texture texturePieDefeat1;
    private Texture texturePieDefeat2;
    private Texture texturePieDefeat3;
    private Texture texturePieDefeat4;
    private Texture texturePieDefeat5;
    private Texture texturePieDefeat6;
    private Texture texturePieDefeat7;
    private Texture texturePieDefeat8;
    private Texture texturePieDefeat9;
    private Texture texturePieDefeat10;
    private Texture texturePieDefeat11;
    private Texture texturePieDefeat12;
    private Texture texturePieDefeat13;
    private Texture texturePieDefeat14;
    private Texture texturePieDefeat15;
    private Texture texturePieDefeat16;
    private Texture texturePieDefeat17;
    private Texture texturePieDefeat18;
    private Texture texturePieDefeat19;
    private Texture texturePieDefeat20;
    private Texture texturePieDefeat21;
    private Texture texturePieDefeat22;
    private Texture texturePieDefeat23;
    private Texture texturePieDefeat24;
    private Texture texturePieDefeat25;
    private Texture texturePieDefeat26;
    private Texture texturePieDefeat27;
    private Texture texturePieDefeat28;
    private Texture texturePieDefeat29;
    private Sprite spritePieDefeat1;
    private Sprite spritePieDefeat2;
    private Sprite spritePieDefeat3;
    private Sprite spritePieDefeat4;
    private Sprite spritePieDefeat5;
    private Sprite spritePieDefeat6;
    private Sprite spritePieDefeat7;
    private Sprite spritePieDefeat8;
    private Sprite spritePieDefeat9;
    private Sprite spritePieDefeat10;
    private Sprite spritePieDefeat11;
    private Sprite spritePieDefeat12;
    private Sprite spritePieDefeat13;
    private Sprite spritePieDefeat14;
    private Sprite spritePieDefeat15;
    private Sprite spritePieDefeat16;
    private Sprite spritePieDefeat17;
    private Sprite spritePieDefeat18;
    private Sprite spritePieDefeat19;
    private Sprite spritePieDefeat20;
    private Sprite spritePieDefeat21;
    private Sprite spritePieDefeat22;
    private Sprite spritePieDefeat23;
    private Sprite spritePieDefeat24;
    private Sprite spritePieDefeat25;
    private Sprite spritePieDefeat26;
    private Sprite spritePieDefeat27;
    private Sprite spritePieDefeat28;
    private Sprite spritePieDefeat29;
    private Animation animationPieDefeat;
    private int counter;
    private int bulletCounter;
    private final int allBullets = 16;
    private float[] bullets;
    private float timer;
    private float animationTimer;
    private boolean setToDestroy;
    private boolean destroyed;
    public static boolean startAttack;
    public static boolean finishAttack;
    public static boolean rolling;
    public static boolean shooting;
    public static boolean summoning;
    public static boolean rocketing;
    private boolean isShot;
    public static boolean isHit;
    public static boolean HPisZero;
    private Sound bulletShot = Gdx.audio.newSound(Gdx.files.internal("Sound/shot.wav"));
    private Sound rocketSount = Gdx.audio.newSound(Gdx.files.internal("Sound/rocket.wav"));
    private Sound pieSound = Gdx.audio.newSound(Gdx.files.internal("Sound/pieHit.wav"));
    private Sound pieRoll = Gdx.audio.newSound(Gdx.files.internal("Sound/pieRoll.wav"));
    public Pie(World world, float x, float y, boolean flip) {
        super(world, x, y, flip);
        Array<Sprite> frames = new Array<Sprite>();
        HP = 1000;
        setToDestroy = false;
        destroyed = false;
        HPisZero = false;
        startAttack = false;
        finishAttack = false;
        rolling = false;
        shooting = false;
        isShot = false;
        summoning = false;
        rocketing = false;
        isHit = false;
        bulletCounter = 0;
        bullets = new float[16];
        bullets[0] = -2.5f;
        bullets[1] = -2f;
        bullets[2] = -1.5f;
        bullets[3] = -1f;
        bullets[4] = -0.5f;
        bullets[5] = -0.25f;
        bullets[6] = -0.15f;
        bullets[7] = 0.25f;
        bullets[8] = 0.5f;
        bullets[9] = 0.75f;
        bullets[10] = 1f;
        bullets[11] = 1.25f;
        bullets[12] = 1.5f;
        bullets[13] = 1.75f;
        bullets[14] = 2f;
        bullets[15] = 2.25f;

        counter = 0;
        textureBullet = new Texture("Bullets/Bullet.png");
        spriteBullet = new Sprite(textureBullet);

        texturePieStand1 = new Texture("Enemies/Pie/PieStand1.png");
        texturePieStand1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieStand1 = new Sprite(texturePieStand1);

        texturePieStand2 = new Texture("Enemies/Pie/PieStand2.png");
        texturePieStand2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieStand2 = new Sprite(texturePieStand2);

        texturePieStand3 = new Texture("Enemies/Pie/PieStand3.png");
        texturePieStand3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieStand3 = new Sprite(texturePieStand3);

        frames.add(spritePieStand1);
        frames.add(spritePieStand2);
        frames.add(spritePieStand3);
        frames.add(spritePieStand2);
        animationPieStand = new Animation(0.2f, frames);
        frames.clear();

        texturePieAttack1 = new Texture("Enemies/Pie/PieAttack1.png");
        texturePieAttack1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieAttack1 = new Sprite(texturePieAttack1);

        texturePieAttack2 = new Texture("Enemies/Pie/PieAttack2.png");
        texturePieAttack2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieAttack2 = new Sprite(texturePieAttack2);

        texturePieAttack3 = new Texture("Enemies/Pie/PieAttack3.png");
        texturePieAttack3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieAttack3 = new Sprite(texturePieAttack3);

        texturePieAttack4 = new Texture("Enemies/Pie/PieAttack4.png");
        texturePieAttack4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieAttack4 = new Sprite(texturePieAttack4);

        texturePieAttack5 = new Texture("Enemies/Pie/PieAttack5.png");
        texturePieAttack5.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieAttack5 = new Sprite(texturePieAttack5);

        texturePieAttack6 = new Texture("Enemies/Pie/PieAttack6.png");
        texturePieAttack6.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieAttack6 = new Sprite(texturePieAttack6);

        texturePieAttack7 = new Texture("Enemies/Pie/PieAttack7.png");
        texturePieAttack7.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieAttack7 = new Sprite(texturePieAttack7);

        frames.add(spritePieAttack1);
        frames.add(spritePieAttack2);
        frames.add(spritePieAttack3);
        frames.add(spritePieAttack4);
        frames.add(spritePieAttack5);
        frames.add(spritePieAttack6);
        frames.add(spritePieAttack7);
        animationPieTransformation = new Animation(0.1f, frames);
        frames.clear();
        frames.add(spritePieAttack7);
        frames.add(spritePieAttack6);
        frames.add(spritePieAttack5);
        frames.add(spritePieAttack4);
        frames.add(spritePieAttack3);
        frames.add(spritePieAttack2);
        frames.add(spritePieAttack1);
        animationPieTransformationBack = new Animation(0.1f, frames);
        frames.clear();

        texturePieRolling1 = new Texture("Enemies/Pie/PieAttackRoll1.png");
        texturePieRolling1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRolling1 = new Sprite(texturePieRolling1);

        texturePieRolling2 = new Texture("Enemies/Pie/PieAttackRoll2.png");
        texturePieRolling2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRolling2 = new Sprite(texturePieRolling2);

        frames.add(spritePieRolling1);
        frames.add(spritePieRolling2);
        animationPieRolling = new Animation(0.05f, frames);
        frames.clear();

        texturePieShots1 = new Texture("Enemies/Pie/PieShots1.png");
        texturePieShots1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots1 = new Sprite(texturePieShots1);

        texturePieShots2 = new Texture("Enemies/Pie/PieShots2.png");
        texturePieShots2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots2 = new Sprite(texturePieShots2);

        texturePieShots3 = new Texture("Enemies/Pie/PieShots3.png");
        texturePieShots3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots3 = new Sprite(texturePieShots3);

        texturePieShots4 = new Texture("Enemies/Pie/PieShots4.png");
        texturePieShots4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots4 = new Sprite(texturePieShots4);

        texturePieShots5 = new Texture("Enemies/Pie/PieShots5.png");
        texturePieShots5.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots5 = new Sprite(texturePieShots5);

        texturePieShots6 = new Texture("Enemies/Pie/PieShots6.png");
        texturePieShots6.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots6 = new Sprite(texturePieShots6);

        texturePieShots7 = new Texture("Enemies/Pie/PieShots7.png");
        texturePieShots7.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots7 = new Sprite(texturePieShots7);

        texturePieShots8 = new Texture("Enemies/Pie/PieShots8.png");
        texturePieShots8.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots8 = new Sprite(texturePieShots8);

        texturePieShots9 = new Texture("Enemies/Pie/PieShots9.png");
        texturePieShots9.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots9 = new Sprite(texturePieShots9);

        texturePieShots10 = new Texture("Enemies/Pie/PieShots10.png");
        texturePieShots10.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots10 = new Sprite(texturePieShots10);

        texturePieShots11 = new Texture("Enemies/Pie/PieShots11.png");
        texturePieShots11.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots11 = new Sprite(texturePieShots11);

        texturePieShots12 = new Texture("Enemies/Pie/PieShots12.png");
        texturePieShots12.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots12 = new Sprite(texturePieShots12);

        texturePieShots13 = new Texture("Enemies/Pie/PieShots13.png");
        texturePieShots13.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieShots13 = new Sprite(texturePieShots13);

        frames.add(spritePieShots1);
        frames.add(spritePieShots2);
        frames.add(spritePieShots3);
        frames.add(spritePieShots4);
        frames.add(spritePieShots5);
        frames.add(spritePieShots6);
        frames.add(spritePieShots7);
        frames.add(spritePieShots8);
        frames.add(spritePieShots9);
        frames.add(spritePieShots10);
        frames.add(spritePieShots11);
        frames.add(spritePieShots12);
        frames.add(spritePieShots13);
        frames.add(spritePieShots12);
        frames.add(spritePieShots11);
        frames.add(spritePieShots10);
        frames.add(spritePieShots9);
        frames.add(spritePieShots8);
        frames.add(spritePieShots7);
        frames.add(spritePieShots6);
        frames.add(spritePieShots5);
        frames.add(spritePieShots4);
        frames.add(spritePieShots3);
        frames.add(spritePieShots2);
        frames.add(spritePieShots1);
        animationPieShots = new Animation(0.1f, frames);
        frames.clear();

        texturePieSummoning1 = new Texture("Enemies/Pie/Summoning1.png");
        texturePieSummoning1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieSummoning1 = new Sprite(texturePieSummoning1);

        texturePieSummoning2 = new Texture("Enemies/Pie/Summoning2.png");
        texturePieSummoning2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieSummoning2 = new Sprite(texturePieSummoning2);

        texturePieSummoning3 = new Texture("Enemies/Pie/Summoning3.png");
        texturePieSummoning3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieSummoning3 = new Sprite(texturePieSummoning3);

        texturePieSummoning4 = new Texture("Enemies/Pie/Summoning4.png");
        texturePieSummoning4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieSummoning4 = new Sprite(texturePieSummoning4);

        frames.add(spritePieSummoning1);
        frames.add(spritePieSummoning2);
        frames.add(spritePieSummoning3);
        frames.add(spritePieSummoning4);
        frames.add(spritePieSummoning3);
        frames.add(spritePieSummoning2);
        frames.add(spritePieSummoning1);
        animationPieSummoning = new Animation(0.1f, frames);
        frames.clear();

        texturePieRocket1 = new Texture("Enemies/Pie/PieRocket1.png");
        texturePieRocket1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocket1 = new Sprite(texturePieRocket1);

        texturePieRocket2 = new Texture("Enemies/Pie/PieRocket2.png");
        texturePieRocket2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocket2 = new Sprite(texturePieRocket2);

        texturePieRocket3 = new Texture("Enemies/Pie/PieRocket3.png");
        texturePieRocket3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocket3 = new Sprite(texturePieRocket3);

        texturePieRocket4 = new Texture("Enemies/Pie/PieRocket4.png");
        texturePieRocket4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocket4 = new Sprite(texturePieRocket4);

        texturePieRocketUp = new Texture("Enemies/Pie/PieRAUF.png");
        texturePieRocketUp.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocketUp = new Sprite(texturePieRocketUp);

        texturePieRocketUpShot = new Texture("Enemies/Pie/PieRAUE.png");
        texturePieRocketUpShot.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocketUpShot = new Sprite(texturePieRocketUpShot);

        texturePieRocketCenter = new Texture("Enemies/Pie/PieRAF.png");
        texturePieRocketCenter.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocketCenter = new Sprite(texturePieRocketCenter);

        texturePieRocketCenterShot = new Texture("Enemies/Pie/PieRAE.png");
        texturePieRocketCenterShot.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocketCenterShot = new Sprite(texturePieRocketCenterShot);

        texturePieRocketDown = new Texture("Enemies/Pie/PieRADF.png");
        texturePieRocketDown.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocketDown = new Sprite(texturePieRocketDown);

        texturePieRocketShotDown = new Texture("Enemies/Pie/PieRADE.png");
        texturePieRocketShotDown.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieRocketShotDown = new Sprite(texturePieRocketShotDown);

        frames.add(spritePieRocket1);
        frames.add(spritePieRocket2);
        frames.add(spritePieRocket3);
        frames.add(spritePieRocket4);
        frames.add(spritePieRocketUp);
        frames.add(spritePieRocketUpShot);
        frames.add(spritePieRocketCenter);
        frames.add(spritePieRocketCenterShot);
        frames.add(spritePieRocketDown);
        frames.add(spritePieRocketShotDown);
        frames.add(spritePieRocketCenter);
        frames.add(spritePieRocket4);
        frames.add(spritePieRocket3);
        frames.add(spritePieRocket2);
        frames.add(spritePieRocket1);
        animationRocketShot = new Animation(0.1f, frames);
        frames.clear();

        textureRocket = new Texture("Objects/Rocket.png");
        textureRocket.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteRocket = new Sprite(textureRocket);

        texturePieDamage = new Texture("Enemies/Pie/PieHit.png");
        texturePieDamage.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePieDamdge = new Sprite(texturePieDamage);

        texturePieDefeat1 = new Texture("Enemies/Pie/PieDefeat1.png");
        texturePieDefeat2 = new Texture("Enemies/Pie/PieDefeat2.png");
        texturePieDefeat3 = new Texture("Enemies/Pie/PieDefeat3.png");
        texturePieDefeat4 = new Texture("Enemies/Pie/PieDefeat4.png");
        texturePieDefeat5 = new Texture("Enemies/Pie/PieDefeat5.png");
        texturePieDefeat6 = new Texture("Enemies/Pie/PieDefeat6.png");
        texturePieDefeat7 = new Texture("Enemies/Pie/PieDefeat7.png");
        texturePieDefeat8 = new Texture("Enemies/Pie/PieDefeat8.png");
        texturePieDefeat9 = new Texture("Enemies/Pie/PieDefeat9.png");
        texturePieDefeat10 = new Texture("Enemies/Pie/PieDefeat10.png");
        texturePieDefeat11 = new Texture("Enemies/Pie/PieDefeat11.png");
        texturePieDefeat12 = new Texture("Enemies/Pie/PieDefeat12.png");
        texturePieDefeat13 = new Texture("Enemies/Pie/PieDefeat13.png");
        texturePieDefeat14 = new Texture("Enemies/Pie/PieDefeat14.png");
        texturePieDefeat15 = new Texture("Enemies/Pie/PieDefeat15.png");
        texturePieDefeat16 = new Texture("Enemies/Pie/PieDefeat16.png");
        texturePieDefeat17 = new Texture("Enemies/Pie/PieDefeat17.png");
        texturePieDefeat18 = new Texture("Enemies/Pie/PieDefeat18.png");
        texturePieDefeat19 = new Texture("Enemies/Pie/PieDefeat19.png");
        texturePieDefeat20 = new Texture("Enemies/Pie/PieDefeat20.png");
        texturePieDefeat21 = new Texture("Enemies/Pie/PieDefeat21.png");
        texturePieDefeat22 = new Texture("Enemies/Pie/PieDefeat22.png");
        texturePieDefeat23 = new Texture("Enemies/Pie/PieDefeat23.png");
        texturePieDefeat24 = new Texture("Enemies/Pie/PieDefeat24.png");
        texturePieDefeat25 = new Texture("Enemies/Pie/PieDefeat25.png");
        texturePieDefeat26 = new Texture("Enemies/Pie/PieDefeat26.png");
        texturePieDefeat27 = new Texture("Enemies/Pie/PieDefeat27.png");
        texturePieDefeat28 = new Texture("Enemies/Pie/PieDefeat28.png");
        texturePieDefeat29 = new Texture("Enemies/Pie/PieDefeat29.png");
        spritePieDefeat1 = new Sprite(texturePieDefeat1);
        spritePieDefeat2 = new Sprite(texturePieDefeat2);
        spritePieDefeat3 = new Sprite(texturePieDefeat3);
        spritePieDefeat4 = new Sprite(texturePieDefeat4);
        spritePieDefeat5 = new Sprite(texturePieDefeat5);
        spritePieDefeat6 = new Sprite(texturePieDefeat6);
        spritePieDefeat7 = new Sprite(texturePieDefeat7);
        spritePieDefeat8 = new Sprite(texturePieDefeat8);
        spritePieDefeat9 = new Sprite(texturePieDefeat9);
        spritePieDefeat10 = new Sprite(texturePieDefeat10);
        spritePieDefeat11 = new Sprite(texturePieDefeat11);
        spritePieDefeat12 = new Sprite(texturePieDefeat12);
        spritePieDefeat13 = new Sprite(texturePieDefeat13);
        spritePieDefeat14 = new Sprite(texturePieDefeat14);
        spritePieDefeat15 = new Sprite(texturePieDefeat15);
        spritePieDefeat16 = new Sprite(texturePieDefeat16);
        spritePieDefeat17 = new Sprite(texturePieDefeat17);
        spritePieDefeat18 = new Sprite(texturePieDefeat18);
        spritePieDefeat19 = new Sprite(texturePieDefeat19);
        spritePieDefeat20 = new Sprite(texturePieDefeat20);
        spritePieDefeat21 = new Sprite(texturePieDefeat21);
        spritePieDefeat22 = new Sprite(texturePieDefeat22);
        spritePieDefeat23 = new Sprite(texturePieDefeat23);
        spritePieDefeat24 = new Sprite(texturePieDefeat24);
        spritePieDefeat25 = new Sprite(texturePieDefeat25);
        spritePieDefeat26 = new Sprite(texturePieDefeat26);
        spritePieDefeat27 = new Sprite(texturePieDefeat27);
        spritePieDefeat28 = new Sprite(texturePieDefeat28);
        spritePieDefeat29 = new Sprite(texturePieDefeat29);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat1);
        frames.add(spritePieDefeat2);
        frames.add(spritePieDefeat3);
        frames.add(spritePieDefeat4);
        frames.add(spritePieDefeat5);
        frames.add(spritePieDefeat6);
        frames.add(spritePieDefeat7);
        frames.add(spritePieDefeat8);
        frames.add(spritePieDefeat9);
        frames.add(spritePieDefeat10);
        frames.add(spritePieDefeat11);
        frames.add(spritePieDefeat12);
        frames.add(spritePieDefeat13);
        frames.add(spritePieDefeat14);
        frames.add(spritePieDefeat15);
        frames.add(spritePieDefeat16);
        frames.add(spritePieDefeat17);
        frames.add(spritePieDefeat18);
        frames.add(spritePieDefeat19);
        frames.add(spritePieDefeat20);
        frames.add(spritePieDefeat21);
        frames.add(spritePieDefeat22);
        frames.add(spritePieDefeat23);
        frames.add(spritePieDefeat24);
        frames.add(spritePieDefeat25);
        frames.add(spritePieDefeat26);
        frames.add(spritePieDefeat27);
        frames.add(spritePieDefeat28);
        frames.add(spritePieDefeat29);
        animationPieDefeat = new Animation(0.1f, frames);
    }

    public void update(float delta){
        timer += delta;
        animationTimer += delta;
        if(isShot) {
            for (Bullet bullet:pieBullets) {
                bullet.update();
                if(bullet.isDestroyed()){
                    pieBullets.removeValue(bullet, true);
                }
            }
        }
        if(setToDestroy && !destroyed){
            rocketing = false;
            HPisZero = true;
            destroyed = true;
            animationTimer = 0;
            GameScreenLevel10.destroyTimer = 0;
        }
        if(!destroyed) {
            if (timer >= 3f && timer <= 4f) {
                if (counter == 0) {
                    animationTimer = 0;
                    startAttack = true;
                    counter++;
                }
            } else if (timer >= 4f && timer <= 5f) {
                if (counter == 1) {
                    pieRoll.play(0.2f);
                    animationTimer = 0;
                    b2body.setLinearVelocity(-25, 0);
                    startAttack = false;
                    rolling = true;
                    counter++;
                }
            } else if (timer >= 5f && timer <= 6f) {
                if (counter == 2) {
                    b2body.setLinearVelocity(25, 0);
                    counter++;
                }
            } else if (timer >= 6f && timer <= 7f) {
                if (counter == 3) {
                    animationTimer = 0;
                    rolling = false;
                    finishAttack = true;
                    counter++;
                }
            } else if (timer >= 7f && timer <= 8f) {
                finishAttack = false;
            } else if (timer >= 8f && timer <= 11f) {
                if (counter == 4) {
                    animationTimer = 0;
                    finishAttack = false;
                    shooting = true;
                    counter++;
                }
            } else if (timer >= 11f && timer <= 13f) {
                shooting = false;
            } else if (timer >= 13f && timer <= 14f) {
                if (counter == 5) {
                    animationTimer = 0;
                    summoning = true;
                    counter++;
                    Summon();
                }
            } else if (timer >= 14f && !cupcakes.isEmpty()) {
                summoning = false;
                timer = 14f;
            } else if (timer >= 15f && timer <= 17f) {
                if (counter == 6) {
                    rocketSount.play(0.1f);
                    rocket = new Rocket(world, b2body.getPosition().x - 250 / PPM, b2body.getPosition().y - 100 / PPM, 0, 0);
                    rocket.rocketBody.setLinearVelocity(-5, 0);
                    rockets.add(rocket);
                    rocketSount.play(0.1f);
                    rocket = new Rocket(world, b2body.getPosition().x - 250 / PPM, b2body.getPosition().y, 0, 0);
                    rocket.rocketBody.setLinearVelocity(-5, 0);
                    rockets.add(rocket);
                    rocketSount.play(0.1f);
                    rocket = new Rocket(world, b2body.getPosition().x - 250 / PPM, b2body.getPosition().y + 100 / PPM, 0, 0);
                    rocket.rocketBody.setLinearVelocity(-5, 0);
                    rockets.add(rocket);
                    animationTimer = 0;
                    rocketing = true;
                    counter++;
                }
            } else if (timer >= 17f && timer <= 18f) {
                rocketing = false;
            } else if (timer >= 18f) {
                counter = 0;
                bulletCounter = 0;
                timer = 0;
            }
            if (timer >= 9f && timer <= 11f) {
                isShot = true;
                while (bulletCounter < allBullets) {
                    bulletShot.play(0.05f);
                    bullet = new Bullet(world, b2body.getPosition().x - 200 / PPM + bulletCounter * 10 / PPM, b2body.getPosition().y + 150 / PPM, 0, 0);
                    bullet.bulletBody.setLinearVelocity(-10f, bullets[bulletCounter]);
                    pieBullets.add(bullet);
                    bulletCounter++;
                }
            }
        }
    }

    private void Summon(){
        cupcake1 = new Cupcake(world, 32*30/PPM, 32*30/PPM, false);
        cupcakes.add(cupcake1);
        cupcake2 = new Cupcake(world, 32*30/PPM, 32*35/PPM, false);
        cupcakes.add(cupcake2);
        cupcake3 = new Cupcake(world, 32*30/PPM, 32*40/PPM, false);
        cupcakes.add(cupcake3);
        cupcake4 = new Cupcake(world, 32*30/PPM, 32*45/PPM, false);
        cupcakes.add(cupcake4);
        cupcake5 = new Cupcake(world, 32*30/PPM, 32*50/PPM, false);
        cupcakes.add(cupcake5);
        cupcake6 = new Cupcake(world, 32*30/PPM, 32*55/PPM, false);
        cupcakes.add(cupcake6);
        cupcake7 = new Cupcake(world, 32*30/PPM, 32*60/PPM, false);
        cupcakes.add(cupcake7);
        cupcake8 = new Cupcake(world, 32*30/PPM, 32*65/PPM, false);
        cupcakes.add(cupcake8);
        cupcake9 = new Cupcake(world, 32*30/PPM, 32*70/PPM, false);
        cupcakes.add(cupcake9);
        cupcake10 = new Cupcake(world, 32*30/PPM, 32*75/PPM, false);
        cupcakes.add(cupcake10);
        cupcake11 = new Cupcake(world, 32*30/PPM, 32*80/PPM, false);
        cupcakes.add(cupcake11);
        cupcake12 = new Cupcake(world, 32*30/PPM, 32*85/PPM, false);
        cupcakes.add(cupcake12);
        cupcake13 = new Cupcake(world, 32*30/PPM, 32*90/PPM, false);
        cupcakes.add(cupcake13);
        cupcake14 = new Cupcake(world, 32*30/PPM, 32*95/PPM, false);
        cupcakes.add(cupcake14);
        cupcake15 = new Cupcake(world, 32*30/PPM, 32*100/PPM, false);
        cupcakes.add(cupcake15);
        cupcake16 = new Cupcake(world, 32*30/PPM, 32*105/PPM, false);
        cupcakes.add(cupcake16);
        cupcake17 = new Cupcake(world, 32*30/PPM, 32*110/PPM, false);
        cupcakes.add(cupcake17);
        cupcake18 = new Cupcake(world, 32*30/PPM, 32*115/PPM, false);
        cupcakes.add(cupcake18);
        cupcake19 = new Cupcake(world, 32*30/PPM, 32*120/PPM, false);
        cupcakes.add(cupcake19);
        cupcake20 = new Cupcake(world, 32*30/PPM, 32*125/PPM, false);
        cupcakes.add(cupcake20);
    }

    public Sprite spritePieDefeat(){
        Sprite sprite;
        sprite = (Sprite)animationPieDefeat.getKeyFrame(animationTimer, false);
        return sprite;
    }

    public Sprite spriteDamage(){
        Sprite sprite;
        sprite = spritePieDamdge;
        return sprite;
    }

    public Sprite spriteRocket(){
        Sprite sprite;
        if(!Rocket.flipRocket){
            spriteRocket.rotate(200);
            sprite = spriteRocket;
        }
        else {
            sprite = spriteRocket;
        }
        return sprite;
    }

    public Sprite spriteRocketShot(){
        Sprite sprite;
        sprite = (Sprite)animationRocketShot.getKeyFrame(animationTimer, false);
        return sprite;
    }

    public Sprite spriteSummoning(){
        Sprite sprite;
        sprite = (Sprite)animationPieSummoning.getKeyFrame(animationTimer, false);
        return sprite;
    }
    public Sprite spriteBullet(){
        Sprite sprite;
        sprite = spriteBullet;
        return sprite;
    }

    public Sprite spriteShooting(){
        Sprite sprite;
        sprite = (Sprite)animationPieShots.getKeyFrame(animationTimer, false);
        return sprite;
    }

    public Sprite spritePieRolling(){
        Sprite sprite;
        sprite = (Sprite)animationPieRolling.getKeyFrame(animationTimer, true);
        return sprite;
    }

    public Sprite spriteEndTransformation(){
        Sprite sprite;
        sprite = (Sprite)animationPieTransformationBack.getKeyFrame(animationTimer, false);
        return sprite;
    }

    public Sprite spriteStartTransformation(){
        Sprite sprite;
        sprite = (Sprite)animationPieTransformation.getKeyFrame(animationTimer, false);
        return sprite;
    }
    public Sprite spriteStand(){
        Sprite sprite;
        sprite = (Sprite) animationPieStand.getKeyFrame(animationTimer, true);
        return sprite;
    }
    public void rocketHit(){
        HP -= 200;
        isHit = true;
        if(HP <= 0){
            setToDestroy = true;
        }
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(210/PPM, 125/PPM);
        fixtureDef.filter.categoryBits = PIE_BIT;
        fixtureDef.filter.maskBits = GROUND_BIT | PLAYER_BIT | BULLET_BIT | SWORD_BIT | ROCKET_BIT;
        fixtureDef.shape = polygonShape;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void bulletHit() {
    }

    @Override
    public void swordHit() {
    }

    @Override
    public void fire() {
    }

    @Override
    public void deleted() {
        setToDestroy = true;
    }

    @Override
    public void reverseVelocity(boolean x, boolean y) {
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void playSound() {
        pieSound.play(0.1f);
    }
}
