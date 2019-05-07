package com.mygdx.game.Graphics;

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
import com.mygdx.game.Familiars.Familiar;
import com.mygdx.game.MyGame;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.MyGame.PLAYER_BIT;
import static com.mygdx.game.MyGame.PPM;
import static javax.swing.UIManager.getString;

public class HUD  {


    private float WORLD_TIME;
    public static int SCORE;
    private int COUNT_OF_DEATH;
    private Viewport viewport;
    private OrthographicCamera camera;

    public static Label score ;
    public  static  Label timer1;
    public  static  Label timer2;
    public  static  float TIMER1=0;
    public  static  float TIMER2=0;
    private static  float Max2;
    private final String ukr = "абвгдеєжзиіїйклмнопрстуфхцчшщьюяabcdefghijklmnopqrstuvwxyzАБВГДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private static  float Max1;
    private  static  String NONE="  ggпріврпвыtydf";
    public  Label countOfDeath;
    private static Stage stage;
    private static Label.LabelStyle labelStyle;
    private MyGame game;
    public HUD(){
        this.game=game;
       // camera.setToOrtho(false,1600/PPM, 900/PPM);
        viewport = new FitViewport(1600/PPM, 900/PPM); //camera);
        stage = new Stage(viewport);
        WORLD_TIME=0;
        SCORE=MyGame.Score;
        COUNT_OF_DEATH=0;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Amble-Light.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.characters = ukr;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        BitmapFont font24 = generator.generateFont(parameter);
        generator.dispose();




        labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;
        score=new Label(String.format("%02d",SCORE),labelStyle);
        timer1=new Label(NONE,labelStyle);
        timer2=new Label(NONE,labelStyle);

        if(Familiar.currentFamiliar1== Familiar.Familiar1.HEAL){
            Max1=Familiar.couldownHeal;
        }
        if(Familiar.currentFamiliar1== Familiar.Familiar1.SHIELD){
            Max1=Familiar.couldownShield;
        }
        if(Familiar.currentFamiliar1== Familiar.Familiar1.RAGE){
            Max1=Familiar.couldownRage;
        }
        if(Familiar.currentFamiliar1== Familiar.Familiar1.FOUR){
            Max1=Familiar.couldownFOUR;
        }
        if(Familiar.currentFamiliar2== Familiar.Familiar2.HEAL){
            Max2=Familiar.couldownHeal;
        }
        if(Familiar.currentFamiliar2== Familiar.Familiar2.SHIELD){
            Max2=Familiar.couldownShield;
        }
        if(Familiar.currentFamiliar2== Familiar.Familiar2.RAGE){
            Max2=Familiar.couldownRage;
        }
        if(Familiar.currentFamiliar2== Familiar.Familiar2.FOUR){
            Max2=Familiar.couldownFOUR;
        }
        if(Familiar.timer1>=Max1){
            TIMER1=0;
        }
        else {
            TIMER1=Max1-Familiar.timer1;
        }
        if(Familiar.timer2>=Max2){
            TIMER2=0;
        }
        else {
            TIMER2=Max2-Familiar.timer2;
        }



        //countOfDeath=new Label(String.format("%03d",COUNT_OF_DEATH),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        score.setSize(3,1);
        score.setPosition(67,Gdx.graphics.getHeight()-60);
        score.setColor(1,1,1,1);
        timer1.setSize(3,1);
        timer1.setPosition(1242,715);
        timer1.setColor(1,1,1,1);
        timer2.setSize(3,1);
        timer2.setPosition(1302,715);
        timer2.setColor(1,1,1,1);
    }
    public static void HealthBar(){


    }
    public static void render(){

        if(Familiar.timer1>Max1){
            TIMER1=0;
            timer1.setText(NONE);
        }
        else {
            TIMER1=Max1-Familiar.timer1;
            timer1.setText(String.format("%01.0f",TIMER1));
        }
        if(Familiar.timer2>Max2){
            TIMER2=0;
            timer2.setText(NONE);
        }
        else {
            TIMER2=Max2-Familiar.timer2;
            timer2.setText(String.format("%01.0f",TIMER2));
        }
        score.setText(String.format("%02d",SCORE));



        if(Player.HP>Player.MAX_HP){
            Player.HP=Player.MAX_HP;
        }



    }
    public static double hp(){
        double vhp;
        vhp=(double)Player.HP/(double)Player.MAX_HP;
        vhp=vhp*100;
        return vhp;
    }


}
