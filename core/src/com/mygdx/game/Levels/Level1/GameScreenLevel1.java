package com.mygdx.game.Levels.Level1;

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
import com.mygdx.game.Enemies.Cannon;
import com.mygdx.game.Enemies.DefendedCannon;
import com.mygdx.game.Enemies.VerticalCannon;
import com.mygdx.game.Familiars.Familiar;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.WorldContactListener;
import com.mygdx.game.Graphics.HUD;
import com.mygdx.game.MyGame;
import com.mygdx.game.Objects.Bullet;
import com.mygdx.game.Objects.Chest;
import com.mygdx.game.Objects.Portal;
import com.mygdx.game.Objects.Weapon;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Screens.DialogScreens.TurtleFirstEncounter;
import com.mygdx.game.Screens.LoadScreen;
import com.mygdx.game.Screens.MenuScreen;

import static com.mygdx.game.Graphics.HUD.*;
import static com.mygdx.game.MyGame.*;

public class GameScreenLevel1 implements Screen {

    private static HUD hud;
    private MyGame game;
    private static Stage stage;
    private Familiar familiar;
    private Weapon weapon;

    private Player player;

    public static boolean isPaused;

    private OrthographicCamera camera;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;
    private Cannon cannon;
    private DefendedCannon defendedCannon;
    private Portal portal;
    private Chest chest;
    private Sound bgmusic = Gdx.audio.newSound(Gdx.files.internal("Sound/bgmusic1.mp3"));
    public static final Sound congrats = Gdx.audio.newSound(Gdx.files.internal("Sound/congratulations.wav"));

    public GameScreenLevel1(MyGame game) {
        this.game = game;
        bgmusic.loop(0.05f);
        game.preferences.putInteger("familiar1", 1);
        game.preferences.putInteger("familiar2", 5);
        game.preferences.flush();
        game.preferences.putInteger("level", 1);
        game.preferences.putInteger("location", 1);
        game.preferences.putString("weapon", "gun");
        game.preferences.putInteger("score", 0);
        game.preferences.putBoolean("doubleGun", false);
        game.preferences.putBoolean("tripleGun", false);
        game.preferences.putBoolean("quadraGun", false);
        game.preferences.putBoolean("pentaGun", false);
        game.preferences.putBoolean("fastGun", false);
        game.preferences.putBoolean("doubleFastGun", false);
        game.preferences.putBoolean("tripleFastGun", false);
        game.preferences.putBoolean("quadraFastGun", false);
        game.preferences.putBoolean("veryFastGun", false);
        game.preferences.putBoolean("veryFastDoubleGun", false);
        game.preferences.putBoolean("ultraFastGun", false);
        game.preferences.putBoolean("shotgun", false);
        game.preferences.putBoolean("fastShotgun", false);
        game.preferences.putBoolean("doubleGunBought", false);
        game.preferences.putBoolean("tripleGunBought", false);
        game.preferences.putBoolean("quadraGunBought", false);
        game.preferences.putBoolean("pentaGunBought", false);
        game.preferences.putBoolean("fastGunBought", false);
        game.preferences.putBoolean("doubleFastGunBought", false);
        game.preferences.putBoolean("tripleFastGunBought", false);
        game.preferences.putBoolean("quadraFastGunBought", false);
        game.preferences.putBoolean("veryFastGunBought", false);
        game.preferences.putBoolean("veryFastDoubleGunBought", false);
        game.preferences.putBoolean("ultraFastGunBought", false);
        game.preferences.putBoolean("shotgunBought", false);
        game.preferences.putBoolean("fastShotgunBought", false);
        game.preferences.flush();


        stage = new Stage(new ScreenViewport());

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1600 / PPM, 900 / PPM);


        viewPort = new FitViewport(1600 / PPM, 900 / PPM, camera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level1Loc1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        world = new World(new Vector2(0, -40), true);
        b2dr = new Box2DDebugRenderer();
        new WorldCreatorLevel1(world, map);

        player = new Player(world, 32/PPM, 32/PPM);
        cannon = new Cannon(world, 0, (32*7)/PPM, false);
        cannons.add(cannon);
        defendedCannon = new DefendedCannon(world, 32/PPM, (32*20)/PPM, false);
        defendedCannons.add(defendedCannon);
        VerticalCannon verticalCannon = new VerticalCannon(world, (32 * 22) / PPM, (10 * 32) / PPM, false);
        verticalCannons.add(verticalCannon);
        VerticalCannon verticalCannon1 = new VerticalCannon(world, (32 * 26) / PPM, (10 * 32) / PPM, false);
        verticalCannons.add(verticalCannon1);
        VerticalCannon verticalCannon2 = new VerticalCannon(world, (32 * 30) / PPM, (10 * 32) / PPM, false);
        verticalCannons.add(verticalCannon2);
        VerticalCannon verticalCannon3 = new VerticalCannon(world, (32 * 34) / PPM, (10 * 32) / PPM, false);
        verticalCannons.add(verticalCannon3);
        chest = new Chest(world, 32*47/PPM, 32*22/PPM, 0, 16/PPM, false, game, game.preferences.getBoolean("WoodenChest1IsOpened"));
        portal = new Portal(world, 32*47/PPM, 32*6/PPM, 0, 29/PPM, false);

        familiar = new Familiar(game, player);
        hud = new HUD();
        weapon = new Weapon(game, world);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {
    }

    private void input(float dt) {
        if (!isPaused) {
            if (player.b2body.getPosition().y < -10 || player.isDead()) {
                for (Bullet bullet:cannon.cannonBullets){
                    bullet.deleteBullet();
                }
                for (Bullet bullet:defendedCannon.defendedCannonBullets){
                    bullet.deleteBullet();
                }
                for (VerticalCannon verticalCannon:verticalCannons) {
                    for (Bullet bullet : verticalCannon.verticalCannonBullets) {
                        bullet.deleteBullet();
                    }
                }
                for (VerticalCannon verticalCannon1:verticalCannons) {
                    for (Bullet bullet : verticalCannon1.verticalCannonBullets) {
                        bullet.deleteBullet();
                    }
                }
                for (VerticalCannon verticalCannon2:verticalCannons) {
                    for (Bullet bullet : verticalCannon2.verticalCannonBullets) {
                        bullet.deleteBullet();
                    }
                }
                for (VerticalCannon verticalCannon3:verticalCannons) {
                    for (Bullet bullet : verticalCannon3.verticalCannonBullets) {
                        bullet.deleteBullet();
                    }
                }
                for (Cannon cannon:cannons){
                    cannon.deleted();
                }
                for (DefendedCannon defendedCannon:defendedCannons){
                    defendedCannon.deleted();
                }
                for (VerticalCannon verticalCannon:verticalCannons){
                    verticalCannon.deleted();
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
        for (VerticalCannon verticalCannon:verticalCannons){
            verticalCannon.update(dt);
            if(verticalCannon.verticalCannonBullets.isEmpty() && verticalCannon.isDestroyed()){
                verticalCannons.removeValue(verticalCannon, true);
            }
        }
        portal.update();
        if(portal.isTouched){
            game.preferences.putInteger("score", HUD.SCORE);
            game.preferences.putInteger("location", 2);
            game.preferences.flush();
            for (Bullet bullet:cannon.cannonBullets){
                bullet.deleteBullet();
            }
            for (Bullet bullet:defendedCannon.defendedCannonBullets){
                bullet.deleteBullet();
            }
            for (VerticalCannon verticalCannon:verticalCannons) {
                for (Bullet bullet : verticalCannon.verticalCannonBullets) {
                    bullet.deleteBullet();
                }
            }
            for (VerticalCannon verticalCannon1:verticalCannons) {
                for (Bullet bullet : verticalCannon1.verticalCannonBullets) {
                    bullet.deleteBullet();
                }
            }
            for (VerticalCannon verticalCannon2:verticalCannons) {
                for (Bullet bullet : verticalCannon2.verticalCannonBullets) {
                    bullet.deleteBullet();
                }
            }
            for (VerticalCannon verticalCannon3:verticalCannons) {
                for (Bullet bullet : verticalCannon3.verticalCannonBullets) {
                    bullet.deleteBullet();
                }
            }
            for (Cannon cannon:cannons){
                cannon.deleted();
            }
            for (DefendedCannon defendedCannon:defendedCannons){
                defendedCannon.deleted();
            }
            for (VerticalCannon verticalCannon:verticalCannons){
                verticalCannon.deleted();
            }
            for (Bullet bullet:playerBullets){
                bullet.deleteBullet();
            }
            congrats.play(0.2f);
            bgmusic.stop();
            game.setScreen(new TurtleFirstEncounter(game));
        }
        chest.update(dt);
        if(chest.thisChestIsTouched()){
            game.preferences.putBoolean("WoodenChest1IsOpened", true);
            game.preferences.flush();
        }
        player.update(dt);
        familiar.update(dt);
        weapon.update(dt, player.b2body.getPosition());
        camera.update();
        renderer.setView(camera);
    }

        @Override
        public void render (float delta){
            //update(delta);

            Gdx.gl.glClearColor(0, 0, 0, 0); //setting bg color
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk
            if(isPaused){
                System.out.println(Gdx.graphics.getWidth()+" "+Gdx.graphics.getHeight());
                game.batch.begin(); //start of rendering
                game.batch.draw(Assets.spriteExitScreenBack, 0, 0,Assets.spriteExitScreenBack.getWidth()/PPM,Assets.spriteExitScreenBack.getHeight()/PPM);
                if(Gdx.input.getX() <250 + 300 && Gdx.input.getX() > 250 && Gdx.input.getY() > 900-200- 250  && Gdx.input.getY() < 900-300) { //setting bounds of NewGameButton
                    game.batch.draw(Assets.spriteDeathScreenDaActive, 300/PPM, 200/PPM, 300/PPM, 150/PPM); //Drawing Active
                    if (Gdx.input.isTouched()) { //creating an event
                        for (VerticalCannon verticalCannon:verticalCannons) {
                            for (Bullet bullet : verticalCannon.verticalCannonBullets) {
                                bullet.deleteBullet();
                            }
                        }
                        for (VerticalCannon verticalCannon1:verticalCannons) {
                            for (Bullet bullet : verticalCannon1.verticalCannonBullets) {
                                bullet.deleteBullet();
                            }
                        }
                        for (VerticalCannon verticalCannon2:verticalCannons) {
                            for (Bullet bullet : verticalCannon2.verticalCannonBullets) {
                                bullet.deleteBullet();
                            }
                        }
                        for (VerticalCannon verticalCannon3:verticalCannons) {
                            for (Bullet bullet : verticalCannon3.verticalCannonBullets) {
                                bullet.deleteBullet();
                            }
                        }
                        for (Bullet bullet:cannon.cannonBullets){
                            bullet.deleteBullet();
                        }
                        for (Bullet bullet:defendedCannon.defendedCannonBullets){
                            bullet.deleteBullet();
                        }
                        for (Cannon cannon:cannons){
                            cannon.deleted();
                        }
                        for (DefendedCannon defendedCannon:defendedCannons){
                            defendedCannon.deleted();
                        }
                        for (VerticalCannon verticalCannon:verticalCannons){
                            verticalCannon.deleted();
                        }
                        for (Bullet bullet:playerBullets){
                            bullet.deleteBullet();
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



                game.batch.draw(player.getFrameLegs(delta), (player.b2body.getPosition().x - 18/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
                game.batch.draw(player.getFrameChest(delta), (player.b2body.getPosition().x - 18/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
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
                    game.batch.draw(verticalCannon.babax(), verticalCannon.b2body.getPosition().x - 25/PPM, verticalCannon.b2body.getPosition().y - 29/PPM, 32/PPM, 64/PPM);
                }
                chest.draw(game.batch);
                portal.draw(game.batch);
                game.batch.end();
                stage.act();
                stage.draw();

            }
        }

        @Override
        public void resize ( int width, int height){
            viewPort.update(width, height);
        }

        public  World getWorld(){
            return world;
        }

        @Override
        public void pause () {
        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {
            map.dispose();
            renderer.dispose();
            world.dispose();
            b2dr.dispose();
        }
    }
