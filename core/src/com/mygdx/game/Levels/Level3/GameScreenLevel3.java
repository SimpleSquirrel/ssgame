package com.mygdx.game.Levels.Level3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level2.WorldCreatorLevel2;
import com.mygdx.game.Levels.WorldContactListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Objects.Chest;
import com.mygdx.game.Objects.Familiar;
import com.mygdx.game.Objects.Portal;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Screens.MenuScreen;

import static com.mygdx.game.Graphics.HUD.score;
import static com.mygdx.game.MyGame.*;

public class GameScreenLevel3 implements Screen {
    private MyGame game;
    private static HUD hud;
    private static Stage stage;

    private Player player;
    private Bullet bullet;

    private static boolean isPaused;
    private static boolean isShot;

    private OrthographicCamera camera;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;
    private final static float  BULLET_WAIT_TIME = 0.1f;
    private Biter biter;
    private Biter biter1;
    private Biter biter2;
    private Biter biter3;
    private Cactus cactus;
    private Cactus cactus1;
    private Cactus cactus2;
    private Cactus cactus3;
    private Cactus cactus4;
    private Cactus cactus5;
    private Portal portal;
    private Chest chest;
    private static int bulletCounter;
    private float timer;
    public GameScreenLevel3(MyGame game){
        this.game = game;
        hud = new HUD();

        stage = new Stage(new ScreenViewport());
        isShot = false;
        bulletCounter = 0;

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1600 / PPM, 900 / PPM);

        viewPort = new FitViewport(1600 / PPM, 900 / PPM, camera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level1Loc3.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        world = new World(new Vector2(0, -40), true);
        b2dr = new Box2DDebugRenderer();
        new WorldCreatorLevel3(world, map);
        biter = new Biter(world, 32*22/PPM, 32*4/PPM, false);
        biters.add(biter);
        biter1 = new Biter(world, 32*24/PPM, 32*4/PPM, false);
        biters.add(biter1);
        biter2 = new Biter(world, 32*33/PPM, 32*4/PPM, false);
        biters.add(biter2);
        biter3 = new Biter(world, 32*49/PPM, 32*4/PPM, false);
        biters.add(biter3);
        cactus = new Cactus(world, 32*25/PPM, 32*25/PPM, false);
        cactuses.add(cactus);
        cactus1 = new Cactus(world, 32*45/PPM, 32*10/PPM, false);
        cactuses.add(cactus1);
        cactus2 = new Cactus(world, 32*30/PPM, 32*20/PPM, false);
        cactuses.add(cactus2);
        cactus3 = new Cactus(world, 32*18/PPM, 32*16/PPM, false);
        cactuses.add(cactus3);
        cactus4 = new Cactus(world, 32*8/PPM, 32*12/PPM, false);
        cactuses.add(cactus4);
        cactus5 = new Cactus(world, 32*30/PPM, 32*25/PPM, false);
        cactuses.add(cactus5);
        portal = new Portal(world, 16/PPM, 32*16/PPM, 0, 29/PPM, true);
        chest = new Chest(world, 32*16/PPM, 32*24/PPM, 0, 16/PPM, true);

        player = new Player(world);

        world.setContactListener(new WorldContactListener());
    }
    private void input(float dt) {
        timer +=dt;
        if (!isPaused) {
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                Player.swordAttack = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 3) {
                player.b2body.applyLinearImpulse(new Vector2(0.3f, 0), player.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -3) {
                player.b2body.applyLinearImpulse(new Vector2(-0.3f, 0), player.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                player.jump();
                HUD.SCORE++;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                isPaused = !isPaused;

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                Familiar.setActive(1,false);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                Familiar.setActive(2,false);
            }
            if (player.b2body.getPosition().y < -10 || player.isDead()) {
                for (Cannon cannon:cannons){
                    cannon.deleted();
                }
                for (DefendedCannon defendedCannon:defendedCannons){
                    defendedCannon.deleted();
                }
                for (Biter biter:biters){
                    biter.deleted();
                }
                for (Cactus cactus:cactuses){
                    cactus.deleted();
                }
                game.setScreen(new DeathScreen(game));
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
                if (Player.runningRight && bulletCounter < 1 && timer >= BULLET_WAIT_TIME) {
                    bullet = new Bullet(world, player.b2body.getPosition().x, player.b2body.getPosition().y, 20/PPM, 5/PPM);
                    bullet.bulletBody.setLinearVelocity(4f, 0);
                    playerBullets.add(bullet);
                    isShot = true;
                    bulletCounter++;
                    timer = 0;
                }
                else if(!Player.runningRight && bulletCounter < 1 && timer >= BULLET_WAIT_TIME) {
                    bullet = new Bullet(world, player.b2body.getPosition().x, player.b2body.getPosition().y, -20/PPM, 5/PPM);
                    bullet.bulletBody.setLinearVelocity(-4f, 0);
                    playerBullets.add(bullet);
                    isShot = true;
                    bulletCounter++;
                    timer = 0;
                }
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
        if(isShot) {
            bullet.update(dt);
            for(Bullet bullet : playerBullets) {
                bullet.update(dt);
                if(bullet.isDestroyed()) {
                    playerBullets.removeValue(bullet, true);
                    bulletCounter--;
                }
            }
        }
        for (Cannon cannon:cannons) {
            cannon.update(dt);
            if(cannon.cannonBullets.isEmpty() && cannon.isDestroyed()){
                cannons.removeValue(cannon, true);
            }
        }
        for (DefendedCannon defendedCannon:defendedCannons){
            defendedCannon.update(dt);
            if(defendedCannon.defendedCannonBullets.isEmpty() && defendedCannon.isDestroyed()){
                defendedCannons.removeValue(defendedCannon, true);
            }
        }
        for (Biter biter:biters){
            biter.update(dt, player.b2body.getPosition().x);
            if(biter.isDestroyed()){
                biters.removeValue(biter, true);
            }
        }
        for (Cactus cactus:cactuses){
            cactus.update(dt);
            if(cactus.isDestroyed()){
                cactuses.removeValue(cactus, true);
            }
        }
        portal.update();
        if(portal.isTouched){
            this.dispose();
            game.setScreen(new GameScreenLevel3(game));
        }
        chest.update(dt);
        player.update(dt);
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
                    for (Cannon cannon:cannons){
                        cannon.deleted();
                    }
                    for (DefendedCannon defendedCannon:defendedCannons){
                        defendedCannon.deleted();
                    }
                    for (Biter biter:biters){
                        biter.deleted();
                    }
                    for (Cactus cactus:cactuses){
                        cactus.deleted();
                    }
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

            //b2dr.render(world, camera.combined);

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);
            game.batch.begin();
            //familiar reload
            if (Familiar.isActive(1)) {
                game.batch.draw(Assets.spriteFamiliar1Active, 20 / PPM, 850 / PPM, 40 / PPM, 40 / PPM);
            }
            else{
                game.batch.draw(Assets.spriteFamiliar1Inactive, 20 / PPM, 850 / PPM, 40 / PPM, 40 / PPM);
                Familiar.reload(1,delta);
            }
            if (Familiar.isActive(2)) {
                game.batch.draw(Assets.spriteFamiliar2Active, 100 / PPM, 850 / PPM, 40 / PPM, 40 / PPM);
            }
            else{
                game.batch.draw(Assets.spriteFamiliar2Inactive, 100 / PPM, 850 / PPM, 40 / PPM, 40 / PPM);
                Familiar.reload(2,delta);
            }
            hud.render();
            stage.addActor(score);

            game.batch.draw(player.getFrameLegs(delta), (player.b2body.getPosition().x - 14/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
            game.batch.draw(player.getFrameChest(delta), (player.b2body.getPosition().x - 14/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
            if(isShot) {
                for (Bullet bullet : playerBullets) {
                    bullet.draw(game.batch);
                }
            }
            for (Cannon cannon:cannons) {
                cannon.draw(game.batch);
                game.batch.draw(cannon.babax(), cannon.b2body.getPosition().x - 15/PPM, cannon.b2body.getPosition().y - 16/PPM, 32/PPM, 32/PPM);
            }
            for (DefendedCannon defendedCannon:defendedCannons){
                defendedCannon.draw(game.batch);
                game.batch.draw(defendedCannon.babax(), defendedCannon.b2body.getPosition().x - 15/PPM, defendedCannon.b2body.getPosition().y - 16/PPM, 32/PPM, 32/PPM);
            }
            for (VerticalCannon verticalCannon:verticalCannons){
                verticalCannon.draw(game.batch);
                game.batch.draw(verticalCannon.babax(), verticalCannon.b2body.getPosition().x - 15/PPM, verticalCannon.b2body.getPosition().y - 29/PPM, 32/PPM, 64/PPM);
            }
            for (Biter biter:biters){
                game.batch.draw(biter.spriteBiter(delta), biter.b2body.getPosition().x - 30/PPM, biter.b2body.getPosition().y - 45/PPM, 60/PPM, 80/PPM);
            }
            for (Cactus cactus:cactuses){
                game.batch.draw(cactus.spriteCactus(delta), cactus.b2body.getPosition().x - 37/PPM, cactus.b2body.getPosition().y - 45/PPM, 80/PPM, 80/PPM);
            }
            chest.draw(game.batch);
            portal.draw(game.batch);
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
