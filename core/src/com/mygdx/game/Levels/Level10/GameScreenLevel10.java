package com.mygdx.game.Levels.Level10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Enemies.*;
import com.mygdx.game.Familiars.Familiar;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level3.WorldCreatorLevel3;
import com.mygdx.game.Levels.Level7.WorldCreatorLevel7;
import com.mygdx.game.Levels.WorldContactListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.Objects.*;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Screens.LoadScreen;
import com.mygdx.game.Screens.MenuScreen;

import static com.mygdx.game.Graphics.HUD.*;
import static com.mygdx.game.MyGame.*;

public class GameScreenLevel10 implements Screen {
    private MyGame game;
    private static HUD hud;
    private static Stage stage;

    private Player player;
    private Weapon weapon;

    private static boolean isPaused;

    private OrthographicCamera camera;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;

    private Pie pie;
    private Cupcake cupcake;
    public static float destroyTimer;
    private Sound bgmusic = Gdx.audio.newSound(Gdx.files.internal("Music/04AllofUs.mp3"));
    private Sound lazer = Gdx.audio.newSound(Gdx.files.internal("Sound/lazer.wav"));
    private Sound letter = Gdx.audio.newSound(Gdx.files.internal("Sound/letterHit.wav"));
    private int counter;

    public GameScreenLevel10(MyGame game){
        this.game = game;
        bgmusic.loop(0.05f);
        counter = 0;

        hud.SCORE = game.preferences.getInteger("score");
        stage = new Stage(new ScreenViewport());

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1600 / PPM, 900 / PPM);

        viewPort = new FitViewport(1600 / PPM, 900 / PPM, camera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level2Loc5.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        world = new World(new Vector2(0, -40), true);
        b2dr = new Box2DDebugRenderer();
        new WorldCreatorLevel3(world, map);

        //cupcake = new Cupcake(world, 32*40/PPM, 32*30/PPM, false);
        //cupcakes.add(cupcake);
        pie = new Pie(world, 32*42/PPM, 32*5/PPM, false);

        player = new Player(world, 32/PPM + 15/PPM, 32/PPM);

        hud = new HUD();
        weapon = new Weapon(game, world);

        world.setContactListener(new WorldContactListener());
    }
    private void input(float dt) {
        if (!isPaused) {
            if (player.b2body.getPosition().y < -10 || player.isDead()) {
                for (Bullet bullet:playerBullets){
                    bullet.deleteBullet();
                }
                for (Cupcake cupcake:cupcakes){
                    cupcake.deleted();
                }
                bgmusic.stop();
                game.setScreen(new DeathScreen(game));
            }
            } else {
                if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                    isPaused = !isPaused;
                }
            }
        }

    public void update(float dt) {
        input(dt);
        world.step(1 / 60f, 6, 2);
        destroyTimer += dt;
        for (Cupcake cupcake:cupcakes){
            cupcake.update(dt, player.b2body.getPosition().x);
            if(cupcake.isDestroyed() && destroyTimer >= 0.4f){
                cupcakes.removeValue(cupcake, true);
            }
        }
        for (Rocket rocket:pie.rockets){
            rocket.update();
            if(rocket.isDestroyed()){
                pie.rockets.removeValue(rocket, true);
            }
        }
        pie.update(dt);
        if(pie.isDestroyed() && destroyTimer > 5f){
            game.setScreen(new DeathScreen(game));
        }
        player.update(dt);
        weapon.update(dt, player.b2body.getPosition());
        camera.update();
        renderer.setView(camera);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0); //setting bg color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk
        if(isPaused){
            game.batch.begin(); //start of rendering
            game.batch.draw(Assets.spriteExitScreenBack, 0, 0,Assets.spriteExitScreenBack.getWidth()/PPM,Assets.spriteExitScreenBack.getHeight()/PPM);
            if(Gdx.input.getX() <250 + 300 && Gdx.input.getX() > 250 && Gdx.input.getY() > 900-200- 250  && Gdx.input.getY() < 900-300) { //setting bounds of NewGameButton
                game.batch.draw(Assets.spriteDeathScreenDaActive, 300/PPM, 200/PPM, 300/PPM, 150/PPM); //Drawing Active
                if (Gdx.input.isTouched()) { //creating an event
                    for (Bullet bullet:playerBullets){
                        bullet.deleteBullet();
                    }
                    for (Cupcake cupcake:cupcakes){
                        cupcake.deleted();
                    }
                    bgmusic.stop();
                    game.setScreen(new MenuScreen(game)); //changing screen
                    isPaused=false;
                }
            }
            else{
                game.batch.draw(Assets.spriteDeathScreenDaInActive, 300/PPM, 200/PPM, 300/PPM, 150/PPM);
            }
            if(Gdx.input.getX() <650 + 300 && Gdx.input.getX() > 650 && Gdx.input.getY() > 900-200- 250  && Gdx.input.getY() < 900-300) { //setting bounds of NewGameButton
                game.batch.draw(Assets.spriteNoActive, 750/PPM, 200/PPM, 300/PPM, 150/PPM); //Drawing Active
                if (Gdx.input.isTouched()) { //creating an even
                    GameScreenLevel1.isPaused=false;
                }
            }
            else{
                game.batch.draw(Assets.spriteDeathScreenNoInActive, 750/PPM, 200/PPM, 300/PPM, 150/PPM);
            }
            game.batch.end(); //ending of rendering
        }
        else {
            Gdx.graphics.requestRendering();
            update(delta);
            Gdx.gl.glClearColor(0, 0, 0, 1); //setting bg color
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

            renderer.render();

            b2dr.render(world, camera.combined);

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);
            game.batch.begin();
            game.batch.draw(Assets.spriteHeadGG, 30 / PPM, 840 / PPM, 40 / PPM, 40 / PPM);
            game.batch.draw(Assets.spriteDetal, 42 / PPM, 813 / PPM, 30 / PPM, 30 / PPM);
            hud.render();
            stage.addActor(score);
            stage.addActor(timer1);
            stage.addActor(timer2);
            //Health bar
            if(HUD.hp()>0&&HUD.hp()<=10){
                game.batch.draw(Assets.spriteHealthBar1, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>10&&HUD.hp()<=20){
                game.batch.draw(Assets.spriteHealthBar2, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>20&&HUD.hp()<=30){
                game.batch.draw(Assets.spriteHealthBar3, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>30&&HUD.hp()<=40){
                game.batch.draw(Assets.spriteHealthBar4, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>40&&HUD.hp()<=50){
                game.batch.draw(Assets.spriteHealthBar5, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>50&&HUD.hp()<=60){
                game.batch.draw(Assets.spriteHealthBar6, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>60&&HUD.hp()<=70){
                game.batch.draw(Assets.spriteHealthBar7, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>70&&HUD.hp()<=80){
                game.batch.draw(Assets.spriteHealthBar8, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>80&&HUD.hp()<=90){
                game.batch.draw(Assets.spriteHealthBar9, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            else if(HUD.hp()>90&&HUD.hp()<=100){
                game.batch.draw(Assets.spriteHealthBar10, 80 / PPM, 850 / PPM, 200 / PPM, 20 / PPM);
            }
            for (Cupcake cupcake:cupcakes){
                game.batch.draw(cupcake.getSprite(), cupcake.b2body.getPosition().x - 30/PPM, cupcake.b2body.getPosition().y - 36/PPM, 64/PPM, 64/PPM);
            }
            if(!pie.isDestroyed()) {
                game.batch.draw(player.getFrameLegs(delta), (player.b2body.getPosition().x - 14 / PPM), (player.b2body.getPosition().y - 36 / PPM), 32 / PPM, 64 / PPM);
                game.batch.draw(player.getFrameChest(delta), (player.b2body.getPosition().x - 14 / PPM), (player.b2body.getPosition().y - 36 / PPM), 32 / PPM, 64 / PPM);
            }
            else {
                game.batch.draw(player.getFrameLegs(delta), 32*5/PPM, 32/PPM, 32 / PPM, 64 / PPM);
                game.batch.draw(player.getFrameChest(delta), 32*5/PPM, 32/PPM, 32 / PPM, 64 / PPM);
            }
            if(Pie.startAttack && !Pie.isHit){
                game.batch.draw(pie.spriteStartTransformation(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 200 / PPM, 700 / PPM, 700 / PPM);
            }
            else if(Pie.rolling && !Pie.isHit){
                game.batch.draw(pie.spritePieRolling(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 200 / PPM, 700 / PPM, 700 / PPM);
            }
            else if(Pie.finishAttack && !Pie.isHit){
                game.batch.draw(pie.spriteEndTransformation(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 200 / PPM, 700 / PPM, 700 / PPM);
            }
            else if(Pie.shooting && !Pie.isHit){
                game.batch.draw(pie.spriteShooting(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 320 / PPM, 700 / PPM, 600 / PPM);
            }
            else if(Pie.summoning && !Pie.isHit){
                game.batch.draw(pie.spriteSummoning(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 200 / PPM, 700 / PPM, 350 / PPM);
            }
            else if(Pie.rocketing && !Pie.isHit){
                game.batch.draw(pie.spriteRocketShot(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 320 / PPM, 700 / PPM, 600 / PPM);
            }
            else if(Pie.isHit){
                game.batch.draw(pie.spriteDamage(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 200 / PPM, 700 / PPM, 350 / PPM);
                Pie.isHit = false;
            }
            else if(Pie.HPisZero){
                game.batch.draw(pie.spritePieDefeat(), 0, 0, 1600/PPM, 900/PPM);
            }
            else {
                game.batch.draw(pie.spriteStand(), pie.b2body.getPosition().x - 350 / PPM, pie.b2body.getPosition().y - 200 / PPM, 700 / PPM, 350 / PPM);
            }
            for (Bullet bullet:pie.pieBullets){
                game.batch.draw(pie.spriteBullet(), bullet.bulletBody.getPosition().x - 2/PPM, bullet.bulletBody.getPosition().y - 2/PPM, 4/PPM, 4/PPM);
            }
            for (Rocket rocket:pie.rockets){
                game.batch.draw(pie.spriteRocket(), rocket.rocketBody.getPosition().x - 40/PPM, rocket.rocketBody.getPosition().y - 20/PPM, 80/PPM, 60/PPM);
            }
            weapon.drawBullet();
            game.batch.end();
            stage.act();
            stage.draw();

        }
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
    }
}
