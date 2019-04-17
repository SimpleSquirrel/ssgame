package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGame;

import static com.mygdx.game.MyGame.PPM;
import static javax.swing.UIManager.getString;

public class HUD  {


    private float WORLD_TIME;
    public static int SCORE;
    private int COUNT_OF_DEATH;

    public static Label score ;
    public  Label countOfDeath;
    private static Stage stage;
    private static Label.LabelStyle labelStyle;

    public HUD(){
        stage = new Stage(new ScreenViewport());
        WORLD_TIME=0;
        SCORE=0;
        COUNT_OF_DEATH=0;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Amble-Light.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        BitmapFont font24 = generator.generateFont(parameter);
        generator.dispose();

         labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;
        score=new Label(String.format("%04d",SCORE),labelStyle);
        //countOfDeath=new Label(String.format("%03d",COUNT_OF_DEATH),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        score.setSize(3,1);
        score.setPosition(13,10);
        score.setColor(1,1,1,1);
    }
    public static void render(){

        score.setText(String.format("%04d",SCORE));
    }


}
