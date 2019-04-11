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
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.MyGame;
import com.mygdx.game.Objects.Floor;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Objects.Bullet;

import java.util.ArrayList;
import com.mygdx.game.Screens.MenuScreen;

import static com.mygdx.game.MyGame.PPM;


public class GameScreenLevel1 implements Screen {
    //Bullets
    ArrayList<Bullet>bullets;
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
    private final static float  BULLET_WAIT_TIME= 1f;
    private Cannon cannon;
    public static int bulletCounter;

    public GameScreenLevel1(MyGame game) {
        isShot = false;
        bulletCounter = 0;

        bullets=new ArrayList<Bullet>();//Bullets
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
        cannon = new Cannon(this, (32*7)/PPM, 0);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {
    }

    public void input(float dt) {
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
                if (Player.runningRight && bulletCounter < 1) {
                    bullet = new Bullet(world, player.b2body.getPosition().x, player.b2body.getPosition().y, 18);
                    bullet.bulletBody.setLinearVelocity(new Vector2(2f, 0));
                    isShot = true;
                    bulletCounter++;
                }
                else if(!Player.runningRight && bulletCounter < 1) {
                    bullet = new Bullet(world, player.b2body.getPosition().x, player.b2body.getPosition().y, -18);
                    bullet.bulletBody.setLinearVelocity(new Vector2(-2f, 0));
                    isShot = true;
                    bulletCounter++;
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
        }
        cannon.update(dt);
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
                    bullet.draw(game.batch);
                }
                cannon.draw(game.batch);
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
