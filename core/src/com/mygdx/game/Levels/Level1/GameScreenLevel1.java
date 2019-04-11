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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Enemies.Cannon;
import com.mygdx.game.Enemies.DefendedCannon;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.MyGame;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Objects.Bullet;

import com.mygdx.game.Screens.MenuScreen;

import static com.mygdx.game.MyGame.*;


public class GameScreenLevel1 implements Screen {
    float shootTimer;

    MyGame game;

    private Player player;
    private Bullet bullet;

    public static boolean isPaused;
    public static boolean isShot;

    OrthographicCamera camera;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;
    private final static float  BULLET_WAIT_TIME = 0.1f;
    private Cannon cannon;
    private DefendedCannon defendedCannon;
    public static int bulletCounter;
    private float timer;

    public GameScreenLevel1(MyGame game) {
        isShot = false;
        bulletCounter = 0;

        shootTimer=0;

        this.game = game;


        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1600 / PPM, 900 / PPM);


        viewPort = new FitViewport(1600 / PPM, 900 / PPM, camera);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Try.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new WorldCreatorLevel1(this);

        player = new Player(this);
        cannon = new Cannon(this, 0, (32*7)/PPM);
        cannons.add(cannon);
        defendedCannon = new DefendedCannon(this, 32/PPM, (32*20)/PPM);
        defendedCannons.add(defendedCannon);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {
    }

    public void input(float dt) {
        timer +=dt;
        if (!isPaused) {

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                player.jump();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                isPaused = !isPaused;

            }
            if (player.b2body.getPosition().y < -10 || player.isDead()) {
                game.setScreen(new DeathScreen(game));
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
                if (Player.runningRight && bulletCounter < 3 && timer >= BULLET_WAIT_TIME) {
                    bullet = new Bullet(world, player.b2body.getPosition().x, player.b2body.getPosition().y, 18/PPM);
                    playerBullets.add(bullet);
                    isShot = true;
                    bulletCounter++;
                    timer = 0;
                }
                else if(!Player.runningRight && bulletCounter < 3 && timer >= BULLET_WAIT_TIME) {
                    bullet = new Bullet(world, player.b2body.getPosition().x, player.b2body.getPosition().y, -20/PPM);
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
                if(bullet.isDestroyed())
                    playerBullets.removeValue(bullet, true);
            }
        }
        for (Cannon cannon:cannons) {
            cannon.update(dt);
        }
        for (DefendedCannon defendedCannon:defendedCannons){
            defendedCannon.update(dt);
        }
        player.update();
        camera.update();
        renderer.setView(camera);

    }

        @Override
        public void render ( float delta){
            update(delta);
            Gdx.gl.glClearColor(0, 0, 0, 0); //setting bg color
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk
            if(isPaused){
                game.batch.begin(); //start of rendering
                game.batch.draw(Assets.spriteExitScreenBack, 0, 0,Assets.spriteExitScreenBack.getWidth()/PPM,Assets.spriteExitScreenBack.getHeight()/PPM);
                if(Gdx.input.getX() <250 + 300 && Gdx.input.getX() > 250 && Gdx.input.getY() > 900-200- 250  && Gdx.input.getY() < 900-300) { //setting bounds of NewGameButton
                    game.batch.draw(Assets.spriteDeathScreenDaActive, 300/PPM, 200/PPM, 300/PPM, 150/PPM); //Drawing Active
                    if (Gdx.input.isTouched()) { //creating an event
                        this.dispose();
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
                Gdx.gl.glClearColor(0, 0, 0, 0); //setting bg color
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

                renderer.render();

                b2dr.render(world, camera.combined);

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                game.batch.begin();
                game.batch.draw(player.getFrameLegs(delta), (player.b2body.getPosition().x - 14/PPM), (player.b2body.getPosition().y - 30/PPM), 32/PPM, 64/PPM);
                game.batch.draw(player.getFrameChest(delta), (player.b2body.getPosition().x - 14/PPM), (player.b2body.getPosition().y - 30/PPM), 32/PPM, 64/PPM);
                if(isShot) {
                    for (Bullet bullet : playerBullets) {
                        bullet.draw(game.batch);
                    }
                }
                for (Cannon cannon:cannons) {
                    cannon.draw(game.batch);
                }
                for (DefendedCannon defendedCannon:defendedCannons){
                    defendedCannon.draw(game.batch);
                }
                game.batch.end();
            }
        }

        @Override
        public void resize ( int width, int height){
            viewPort.update(width, height);
        }

        public TiledMap getMap(){
            return map;
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
