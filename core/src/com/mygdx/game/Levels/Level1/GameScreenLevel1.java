package com.mygdx.game.Levels.Level1;

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
import com.mygdx.game.Enemies.Cannon;
import com.mygdx.game.Enemies.DefendedCannon;
import com.mygdx.game.Enemies.VerticalCannon;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.Level2.GameScreenLevel2;
import com.mygdx.game.Levels.WorldContactListener;
import com.mygdx.game.Graphics.HUD;
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

public class GameScreenLevel1 implements Screen {

    private final Familiar familiar;
    private static HUD hud;
    private MyGame game;
    private static Stage stage;

    private Player player;
    private Bullet bullet;

    public static boolean isPaused;
    private static boolean isShot;

    private OrthographicCamera camera;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;
    private final static float  BULLET_WAIT_TIME = 0.1f;
    private Cannon cannon;
    private DefendedCannon defendedCannon;
    private Portal portal;
    private Chest chest;
    private static int bulletCounter;
    private float timer;

    public GameScreenLevel1(MyGame game) {
        this.game = game;
        game.preferences.putInteger("level", 1);
        game.preferences.putInteger("location", 1);
        game.preferences.putString("weapon", "gun");
        game.preferences.putInteger("Score", 0);
        game.preferences.flush();
        hud = new HUD();
        familiar=new Familiar();
        hud=new HUD();
        stage = new Stage(new ScreenViewport());
        isShot = false;
        bulletCounter = 0;

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1600 / PPM, 900 / PPM);


        viewPort = new FitViewport(1600 / PPM, 900 / PPM, camera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level1Loc1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        world = new World(new Vector2(0, -40), true);
        b2dr = new Box2DDebugRenderer();
        new WorldCreatorLevel1(world, map);

        player = new Player(world);
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
        chest = new Chest(world, 32*47/PPM, 32*22/PPM, 0, 16/PPM);
        portal = new Portal(world, 32*47/PPM, 32*6/PPM, 0, 29/PPM);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {
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

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                isPaused = !isPaused;

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                if (HUD.SCORE>=50&&Player.HP<=Player.MAX_HP&&game.preferences.getInteger("level")!=1&&familiar.isActive(1)) {
                    familiar.setActive(1,false);

                    Player.HP += 10;
                    HUD.SCORE-=50;
                }
                else if(HUD.SCORE>=50&&Player.HP<=25&&familiar.isActive(1)) {
                    familiar.setActive(1, false);
                    Player.HP += 10;
                    HUD.SCORE -= 50;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                Familiar.setActive(2,false);
            }
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
            game.setScreen(new GameScreenLevel2(game));
        }
        chest.update(dt);
        player.update(dt);
        camera.update();
        renderer.setView(camera);
    }

        @Override
        public void render (float delta){
            //update(delta);

            Gdx.gl.glClearColor(0, 0, 0, 0); //setting bg color
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk
            if(isPaused){
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

                game.batch.draw(player.getFrameLegs(delta), (player.b2body.getPosition().x - 18/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
                game.batch.draw(player.getFrameChest(delta), (player.b2body.getPosition().x - 18/PPM), (player.b2body.getPosition().y - 36/PPM), 32/PPM, 64/PPM);
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
