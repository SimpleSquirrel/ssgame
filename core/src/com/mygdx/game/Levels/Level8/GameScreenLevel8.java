package com.mygdx.game.Levels.Level8;

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
import com.mygdx.game.Graphics.Labels;
import com.mygdx.game.Levels.Level1.GameScreenLevel1;
import com.mygdx.game.Levels.Level7.WorldCreatorLevel7;
import com.mygdx.game.Levels.WorldContactListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Objects.Chest;
import com.mygdx.game.Objects.Portal;
import com.mygdx.game.Objects.Weapon;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Screens.LoadScreen;
import com.mygdx.game.Screens.MenuScreen;

import static com.mygdx.game.Graphics.HUD.*;
import static com.mygdx.game.MyGame.*;

public class GameScreenLevel8 implements Screen {
    private MyGame game;
    private static HUD hud;
    private static Stage stage;
    private Familiar familiar;
private Labels labels;
    private Player player;
    private Weapon weapon;

    private static boolean isPaused;

    private OrthographicCamera camera;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;
    private Portal portal;
    private Chest chest;
    public static float destroyTimer;
    private Sound bgmusic = Gdx.audio.newSound(Gdx.files.internal("Music/04AllofUs.mp3"));

    public GameScreenLevel8(MyGame game){
        this.game = game;
        bgmusic.loop(0.05f);
labels=new Labels(game);
        hud.SCORE = game.preferences.getInteger("score");
        preferences.putInteger("level", 2);
        preferences.putInteger("location", 3);
        game.preferences.putInteger("familiar1", 1);
        game.preferences.putInteger("familiar2", 5);
        game.preferences.flush();
        stage = new Stage(new ScreenViewport());

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1600 / PPM, 900 / PPM);

        viewPort = new FitViewport(1600 / PPM, 900 / PPM, camera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level2Loc4.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        world = new World(new Vector2(0, -40), true);
        b2dr = new Box2DDebugRenderer();
        new WorldCreatorLevel7(world, map);

        portal = new Portal(world, 32*2/PPM, 32*2/PPM, -20/PPM, 29/PPM, false);
        chest = new Chest(world, 32*49/PPM, 32*2/PPM, 0, 16/PPM, false, game, game.preferences.getBoolean("IronChest3IsOpened"));
        player = new Player(world, 32/PPM + 15/PPM, 32*27/PPM);

        familiar = new Familiar(game, player);
        hud = new HUD();
        weapon = new Weapon(game, world);

        world.setContactListener(new WorldContactListener());
    }
    private void input(float dt) {
        if (!isPaused) {
            if (player.b2body.getPosition().y < -10 || player.isDead()) {
                for (Cannon cannon : cannons) {
                    cannon.deleted();
                }
                for (Biter biter : biters) {
                    biter.deleted();
                }
                for (Cactus cactus : cactuses) {
                    cactus.deleted();
                }
                for (Bullet bullet:playerBullets){
                    bullet.deleteBullet();
                }
                for (Bullet bullet:playerBullets){
                    bullet.deleteBullet();
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
            if(biter.isDestroyed() && destroyTimer > 0.4f){
                enemies.removeValue(biter, true);
                biters.removeValue(biter, true);
            }
        }
        for (Cactus cactus:cactuses){
            cactus.update(dt);
            if(cactus.isDestroyed() && destroyTimer > 0.4f){
                enemies.removeValue(cactus, true);
                cactuses.removeValue(cactus, true);
            }
        }
        portal.update();
        if(portal.isTouched){
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
            for (Bullet bullet:playerBullets){
                bullet.deleteBullet();
            }
            game.preferences.putInteger("location", 4);
            game.preferences.flush();
            bgmusic.stop();
            game.setScreen(new LoadScreen(game));
        }
        chest.update(dt);
        if(chest.thisChestIsTouched()){
            game.preferences.putBoolean("IronChest3IsOpened", true);
            game.preferences.flush();
        }
        player.update(dt);
        familiar.update(dt);
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
                    for (Bullet bullet:playerBullets){
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet:playerBullets){
                        bullet.deleteBullet();
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
            familiar.drawFamiliar(delta,Familiar.currentFamiliar1,Familiar.currentFamiliar2);
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

            game.batch.draw(player.getFrameLegs(delta), (player.b2body.getPosition().x - 14/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
            game.batch.draw(player.getFrameChest(delta), (player.b2body.getPosition().x - 14/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
            weapon.drawBullet();
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
                if(biter.isSetToDestroy()){
                    game.batch.draw(biter.babax(delta), biter.positionX - 30/PPM, biter.positionY - 45/PPM, 60/PPM, 80/PPM);
                }
                else {
                    game.batch.draw(biter.spriteBiter(delta), biter.b2body.getPosition().x - 30/PPM, biter.b2body.getPosition().y - 45/PPM, 60/PPM, 80/PPM);
                }
            }
            for (Cactus cactus:cactuses){
                if(cactus.isSetToDestroy()){
                    game.batch.draw(cactus.babax(delta), cactus.positionX - 37/PPM, cactus.positionY - 45/PPM, 80/PPM, 80/PPM);
                }
                else {
                    game.batch.draw(cactus.spriteCactus(delta), cactus.b2body.getPosition().x - 37/PPM, cactus.b2body.getPosition().y - 45/PPM, 80/PPM, 80/PPM);
                }
            }
            game.batch.draw(chest.chestClosed, portal.b2body.getPosition().x - 16/PPM, portal.b2body.getPosition().y - 30/PPM, 32/PPM, 32/PPM);
            game.batch.draw(portal.spritePortal, chest.b2body.getPosition().x - 16/PPM, chest.b2body.getPosition().y - 15/PPM, 32/PPM, 58/PPM);
            //chest.draw(game.batch);
            //portal.draw(game.batch);
            game.batch.end();
            if(chest.isTouched){
                Labels.drawLabel(delta,9);
            }
            Labels.drawLabel(delta,3);
            Labels.drawLabel(delta,6);
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
