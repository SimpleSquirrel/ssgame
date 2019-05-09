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

import static com.mygdx.game.Screens.DialogScreens.FirstMeet.dialogmusic;

public class SquirrelFirstEncounter implements Screen {
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
    public SquirrelFirstEncounter(MyGame game){
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
        if(a > 8){
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
        setScene(0, "Ну як, сподобались шипи?", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(1, "В мене вже алергія на шипи. Якщо я їх ще раз побачу...", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(2, "Про це не хвилюйся. Моя розвідка каже...\n\n", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(3, "Та що в тебе за розвідка така?!", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(4, "Це секретна інформація.\n\nТак от, на наступній локацію буде дещо значно гірше за шипи!", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(5, "Що може бути гірше за шипи?", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(6, "Кактуси. Добре, що Психобілка згодився нам допомогти.", Assets.spriteRobotInactive, Assets.spriteCatActive);
        setScene(7, "Я не знаю, що з цих двох чудніше.", Assets.spriteRobotActive, Assets.spriteCatInactive);
        setScene(8, "Кактуси дуже швидкі і броньовані.\n\nАле Психобілка збільшує силу пуль. Так що, думаю, прорвемось.", Assets.spriteRobotInactive, Assets.spriteCatActive);
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
