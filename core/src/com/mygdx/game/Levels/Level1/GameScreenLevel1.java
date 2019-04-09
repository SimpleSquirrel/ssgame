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
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGame;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Screens.DeathScreen;
import com.mygdx.game.Screens.ExitScreen;
import com.mygdx.game.Objects.Bullet;

import java.util.ArrayList;

import static com.mygdx.game.MyGame.PPM;


public class GameScreenLevel1 implements Screen {
    //Bullets
    ArrayList<Bullet>bullets;
    float shootTimer;

    MyGame game;

    private Player player;

    OrthographicCamera camera;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Viewport viewPort;
    private final static float  BULLET_WAIT_TIME= 1f;

    public GameScreenLevel1(MyGame game) {

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

        new WorldCreatorLevel1(world, map);

        player = new Player(world);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {
    }

    public void input(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(WorldContactListener.isGrounded  == true) {
                player.b2body.applyLinearImpulse(new Vector2(0, 5f), player.b2body.getWorldCenter(), true);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            this.pause();
            game.setScreen(new ExitScreen(game));
        }
        if(player.b2body.getPosition().y<-10 ){
            game.setScreen(new DeathScreen(game));
        }
    }

    public void update(float dt) {
        input(dt);
        world.step(1 / 60f, 6, 2);
        camera.update();
        renderer.setView(camera);

    }

        @Override
        public void render ( float delta){
            //add Bullet
            shootTimer+=delta;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)&& shootTimer>=BULLET_WAIT_TIME) {
                bullets.add(new Bullet (player.b2body.getPosition().x, player.b2body.getPosition().y));
                shootTimer=0;
            }
            ArrayList<Bullet> bulletsToRemove =new ArrayList<Bullet>();
            for(Bullet bullet:bullets){
                bullet.update(delta);
                if(bullet.remove){
                    bulletsToRemove.add(bullet);
                }
                bullets.removeAll(bulletsToRemove);
            }

            update(delta);
            Gdx.gl.glClearColor(0, 0, 0, 0); //setting bg color
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

            renderer.render();

            b2dr.render(world, camera.combined);

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);
            game.batch.begin();
            for(Bullet bullet :bullets){
                bullet.render(game.batch);
            }
            game.batch.end();
        }

        @Override
        public void resize ( int width, int height){
            viewPort.update(width, height);
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
