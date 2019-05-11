package com.mygdx.game.Levels.Level6;

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
import com.mygdx.game.Levels.Level4.WorldCreatorLevel4;
import com.mygdx.game.Levels.WorldContactListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.Objects.*;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Screens.LoadScreen;
import com.mygdx.game.Screens.MenuScreen;

import static com.mygdx.game.Graphics.HUD.*;
import static com.mygdx.game.MyGame.*;

public class GameScreenLevel6 implements Screen {
    private MyGame game;
    private static HUD hud;
    private Labels labels;
    private static Stage stage;
    private Familiar familiar;

    private Player player;
    private Weapon weapon;

    private static boolean isPaused;

    private OrthographicCamera camera;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;
    private Cannon cannon;
    private Cannon cannon1;
    private Cannon cannon2;
    private Cannon cannon3;
    private Cannon cannon4;
    private Cannon cannon5;
    private Cannon cannon6;
    private Cannon cannon7;
    private Cannon cannon8;
    private Cannon cannon9;
    private Biter biter;
    private Biter biter1;
    private Biter biter2;
    private Biter biter3;
    private Biter biter4;
    private Biter biter5;
    private ArmoredBiter armoredBiter;
    private Wonder wonder;
    private Portal portal;
    private Chest chest;
    public static float destroyTimer;
    private Sound bgmusic = Gdx.audio.newSound(Gdx.files.internal("Sound/bgmusic1.mp3"));

    public GameScreenLevel6(MyGame game){
        this.game = game;
        bgmusic.loop(0.05f);
labels=new Labels(game);
        hud.SCORE = game.preferences.getInteger("score");
        game.preferences.putInteger("familiar1", 1);
        game.preferences.putInteger("familiar2", 2);
        game.preferences.flush();
        stage = new Stage(new ScreenViewport());

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1600 / PPM, 900 / PPM);

        viewPort = new FitViewport(1600 / PPM, 900 / PPM, camera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level2Loc1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        world = new World(new Vector2(0, -40), true);
        b2dr = new Box2DDebugRenderer();
        new WorldCreatorLevel4(world, map);

        cannon = new Cannon(world, 0, 32*5/PPM, false);
        cannons.add(cannon);
        cannon1 = new Cannon(world, 0, 32*7/PPM, false);
        cannons.add(cannon1);
        cannon2 = new Cannon(world, 0, 32*9/PPM, false);
        cannons.add(cannon2);
        cannon3 = new Cannon(world, 0, 32*11/PPM, false);
        cannons.add(cannon3);
        cannon4 = new Cannon(world, 0, 32*13/PPM, false);
        cannons.add(cannon4);
        cannon5 = new Cannon(world, 0, 32*15/PPM, false);
        cannons.add(cannon5);
        cannon6 = new Cannon(world, 0, 32*17/PPM, false);
        cannons.add(cannon6);
        cannon7 = new Cannon(world, 0, 32*19/PPM, false);
        cannons.add(cannon7);
        cannon8 = new Cannon(world, 0, 32*21/PPM, false);
        cannons.add(cannon8);
        cannon9 = new Cannon(world, 0, 32*23/PPM, false);
        cannons.add(cannon9);
        biter = new Biter(world, 32*45/PPM, 32*20/PPM, false);
        biters.add(biter);
        biter1 = new Biter(world, 32*43/PPM, 32*20/PPM, false);
        biters.add(biter1);
        biter2 = new Biter(world, 32*41/PPM, 32*20/PPM, false);
        biters.add(biter2);
        biter3 = new Biter(world, 32*39/PPM, 32*20/PPM, false);
        biters.add(biter3);
        biter4 = new Biter(world, 32*37/PPM, 32*20/PPM, false);
        biters.add(biter4);
        biter5 = new Biter(world, 32*35/PPM, 32*20/PPM, false);
        biters.add(biter5);
        armoredBiter = new ArmoredBiter(world, 32*5/PPM, 32/PPM, false);
        armoredBiters.add(armoredBiter);
        wonder = new Wonder(world, 32*30/PPM, 32*16/PPM, false);
        wonders.add(wonder);
        portal = new Portal(world, 32*5/PPM, 32/PPM, -20/PPM, 29/PPM, true);
        chest = new Chest(world, 32*48/PPM, 32*25/PPM, 0, 16/PPM, false, game, game.preferences.getBoolean("IronChest1IsOpened"));
        player = new Player(world, 32/PPM, 32/PPM);

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
                for (Bullet bullet : cannon.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Biter biter : biters) {
                    biter.deleted();
                }
                for (Cactus cactus : cactuses) {
                    cactus.deleted();
                }
                for (Bullet bullet : cannon1.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet:cannon2.cannonBullets){
                    bullet.deleteBullet();
                }
                for (Bullet bullet : cannon3.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet : cannon4.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet : cannon5.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet : cannon6.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet : cannon7.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet : cannon8.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet : cannon9.cannonBullets) {
                    bullet.deleteBullet();
                }
                for (Bullet bullet:playerBullets){
                    bullet.deleteBullet();
                }
                for (Bullet bullet:playerBullets){
                    bullet.deleteBullet();
                }
                for (ArmoredBiter armoredBiter:armoredBiters){
                    armoredBiter.deleted();
                }
                for (Wonder wonder: wonders){
                    wonder.deleted();
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
        for (ArmoredBiter armoredBiter:armoredBiters){
            armoredBiter.update(dt, player.b2body.getPosition().x);
            if(armoredBiter.isDestroyed() && destroyTimer > 0.4f){
                enemies.removeValue(armoredBiter, true);
                armoredBiters.removeValue(armoredBiter, true);
            }
        }
        for (Wonder wonder:wonders){
            wonder.update(dt);
        }
        portal.update();
        if(portal.isTouched){
            for (Cannon cannon : cannons) {
                cannon.deleted();
            }
            for (Bullet bullet : cannon.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Biter biter : biters) {
                biter.deleted();
            }
            for (Cactus cactus : cactuses) {
                cactus.deleted();
            }
            for (Bullet bullet : cannon1.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet:cannon2.cannonBullets){
                bullet.deleteBullet();
            }
            for (Bullet bullet : cannon3.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet : cannon4.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet : cannon5.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet : cannon6.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet : cannon7.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet : cannon8.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet : cannon9.cannonBullets) {
                bullet.deleteBullet();
            }
            for (Bullet bullet:playerBullets){
                bullet.deleteBullet();
            }
            for (Bullet bullet:playerBullets){
                bullet.deleteBullet();
            }
            for (ArmoredBiter armoredBiter:armoredBiters){
                armoredBiter.deleted();
            }
            for (Wonder wonder:wonders){
                wonder.deleted();
            }
            game.preferences.putInteger("location", 2);
            game.preferences.flush();
            bgmusic.stop();
            game.setScreen(new LoadScreen(game));
        }
        chest.update(dt);
        if(chest.thisChestIsTouched()){
            game.preferences.putBoolean("IronChest1IsOpened", true);
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
                    for (Cannon cannon : cannons) {
                        cannon.deleted();
                    }
                    for (Bullet bullet : cannon.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Biter biter : biters) {
                        biter.deleted();
                    }
                    for (Cactus cactus : cactuses) {
                        cactus.deleted();
                    }
                    for (Bullet bullet : cannon1.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet:cannon2.cannonBullets){
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet : cannon3.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet : cannon4.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet : cannon5.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet : cannon6.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet : cannon7.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet : cannon8.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet : cannon9.cannonBullets) {
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet:playerBullets){
                        bullet.deleteBullet();
                    }
                    for (Bullet bullet:playerBullets){
                        bullet.deleteBullet();
                    }
                    for (ArmoredBiter armoredBiter:armoredBiters){
                        armoredBiter.deleted();
                    }
                    for (Wonder wonder: wonders){
                        wonder.deleted();
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
            game.batch.draw(player.shielded(), (player.b2body.getPosition().x - 23/PPM), (player.b2body.getPosition().y - 36/PPM), 48/PPM, 75/PPM);
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
            for (ArmoredBiter armoredBiter :armoredBiters){
                if(armoredBiter.isSetToDestroy()){
                    game.batch.draw(armoredBiter.babax(delta), armoredBiter.positionX - 30/PPM, armoredBiter.positionY - 45/PPM, 60/PPM, 80/PPM);
                }
                else {
                    game.batch.draw(armoredBiter.spriteArmoredBiter(delta), armoredBiter.b2body.getPosition().x - 30/PPM, armoredBiter.b2body.getPosition().y - 45/PPM, 60/PPM, 80/PPM);
                }
            }
            for (Wonder wonder:wonders) {
                if (!wonder.isTouched()) {
                    game.batch.draw(wonder.getSprite(), wonder.b2body.getPosition().x - 15 / PPM, wonder.b2body.getPosition().y - 18 / PPM, 32 / PPM, 32 / PPM);
                } else {
                    game.batch.draw(wonder.getSprite(), wonder.b2body.getPosition().x - 40 / PPM, wonder.b2body.getPosition().y - 25 / PPM, 80 / PPM, 80 / PPM);
                }
            }
            chest.draw(game.batch);
            portal.draw(game.batch);
            game.batch.end();
            if(chest.isTouched){
                Labels.drawLabel(delta,9);
            }
            Labels.drawLabel(delta,1);
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
