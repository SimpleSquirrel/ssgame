package com.mygdx.game.Screens.DialogScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.Levels.NovelScreen;
import com.mygdx.game.MyGame;
import com.mygdx.game.Screens.LoadScreen;

import java.util.Locale;

import static com.mygdx.game.Familiars.Familiar.FOUR;
import static com.mygdx.game.Screens.DialogScreens.FirstMeet.dialogmusic;

public class JhinFirstEncounter implements Screen {
    private MyGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private NovelScreen novelScreen;

    private Sprite sprite;
    private SpriteBatch batch;

    private final int picToLeft = 0;
    private final int picToRight = 1250;

    private static Sprite left, right;

    private BitmapFont font;
    private Label label;
    Label.LabelStyle labelStyle;
    private String line = "sfaasdadadfasdfa";

    private Locale ukrLocale;
    final String ukr = "абвгдеєжзиіїйклмнопрстуфхцчшщьюяabcdefghijklmnopqrstuvwxyzАБВГДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private int counter1 = 0;
    private int counter2 = 0;

    private int a=0;
    public JhinFirstEncounter(MyGame game){
        this.game = game;
        dialogmusic.loop(0.05f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);
        viewport = new FitViewport(1600, 900, camera);

        sprite = new Sprite();
        ukrLocale = new Locale("ua", "ua");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("test.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = ukr;
        parameter.borderWidth = 1;
        parameter.size = 16;
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = new Color(0,0.2f,0,0.8f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        label = new Label(line, labelStyle);
        label.setSize(0,0);
        label.setPosition(450,175);
        label.setColor(1,1,1,1);

        camera = new OrthographicCamera(); //Initialising camera
        camera.setToOrtho(false, 1600, 900); //setting sizes for camera
    }

    private void spLeft(Sprite sprite) { game.batch.draw(sprite, 0, 0, 1600, 900);}
    private void spRight(Sprite sprite) { game.batch.draw(sprite, 150, -100, 1600, 900);}
    private void say(String line) {
        game.batch.draw(Assets.spriteNovelBox, 250, -50, 1100, 400);
        label.setText(line);
        label.draw(game.batch,1);
    }
    private void iter()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))a++;
        if(a > 4){
            dialogmusic.stop();
            game.setScreen(new LoadScreen(game));
        }
        if(a == 2){
            if(counter1 == 0) {
                FOUR.play(0.2f);
                counter1++;
            }
        }
        if(a == 3){
            if(counter2 == 0) {
                FOUR.play(0.2f);
                counter2++;
            }
        }
    }

    private void setScene(int i,String line, Sprite left, Sprite right)
    {
        if(a==i) {
            spLeft(left);
            spRight(right);
            say(line);
        }
    }

    @Override
    public void show() {

    }
    public void update(){
    }
    @Override
    public void render(float delta) {
        iter();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(Assets.spriteDialogBg, 0, 0, 1600, 900);
        setScene(0, "Тихо! Навіть не думай щось говорити!", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(1, "Але як же інформація про четверту локацію?!", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(2, "О ні, ти розбудив Джина! Тепер він вічно буде повторювати цифру...", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(3, "Забудь. Пора вже йти.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(4, "Але ж інформація...", Assets.spriteRobotActive, Assets.spriteCatInactive);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
    }
}
