package com.mygdx.game.Screens.DialogScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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

public class FirstMeet implements Screen {
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

    private int a=0;

    public static final Sound dialogmusic = Gdx.audio.newSound(Gdx.files.internal("Music/07We'reTheResistors.mp3"));
    public FirstMeet(MyGame game){
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
        if(a > 22){
            dialogmusic.stop();
            game.setScreen(new LoadScreen(game));
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
        setScene(0, "Ти все ж прокинувся? Хахаха, не даремно я стільки деталей витратив!", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(1, "Ти про що? Де ми? І хто ти?", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(2, "Я Кіт-Механік! Це я тебе полагодив!\n\nА тепер, раз я тобі допоміг, допоможи й ти мені!", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(3, "Ні.", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(4, "Що ти тільки що сказав? Я тебе полагодив, я тебе і зламаю!", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(5, "Та я жартую! Що треба зробити?", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(6, "Да так, дрібниці. Просто здійснити державний переворот,\n\nперемогти СС-Робота і стати головним, щоб такі, як я, перестали ховатися!", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(7, "Супер. А не занадто ти хочеш за простий ремонт?", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(8, "Що зробиш, інфляція, долар росте, такі тепер ціни.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(9, "Ехх, чому в мене відчуття, що мене обманули?", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(10, "Так, немає часу на балачки, час вирушати.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(11, "Але, напевно, треба тобі пояснити, що да як.\n\nВ тебе є два вида атаки - постріл на Q, удар мечем на W\n\nПостріл наносить ворогам невелику кількість ушкоджень.\n\nА меч відразу їх убиває.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(12, "Але не думай, що підійти на достатню для удару відстань просто.\n\nЯкщо можеш вбити ворога з пістолета, краще не ризикуй.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(13, "І ще я можу тебе трохи відремонтувати, якщо що.\n\nЩоб отримати ремонт, просто натисни клавішу E.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(14, "А тепер про ворогів. За даними розвідки...", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(15, "Якої розвідки?.", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(16, "Не перебивай! Так от, в нас на шляху стануть лише пушки.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(17, "Вони починяють стріляти відразу як побачать тебе.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(18, "Є три види пушок: звичайна, захищена і вертикальна.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(19, "Захищену можна вбити лише ударивши по ній мечем ззаду.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(20, "А вертикальна завжди стіляє вгору.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(21, "Навіщо?", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(22, "Птахів відстрілює. А тепер вже точно, час вирушати!", Assets.spriteRobotInactive, Assets.spriteCatActive);
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
