package com.mygdx.game.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.MyGame;

public class Labels {

    MyGame game;
    private static SpriteBatch batch;
    final String ukr = "абвгдеєжзиіїйклмнопрстуфхцчшщьюяabcdefghijklmnopqrstuvwxyzАБВГДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    Label.LabelStyle labelStyle;

    public static float[] timer;
    private static boolean[][] chek;
    public static Label location1, location2,location3,location4,
    level1,level2,vedme,pirog,defis,upgrade,DIO,newGame,continie,nastr,exit,NGSnadpis;
    public Labels(MyGame game){
       batch= new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("16103.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = ukr;
        parameter.borderWidth = 1;
        parameter.size = 30;
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = new Color(0,0.2f,0,0);
        BitmapFont fonts = generator.generateFont(parameter);
        generator.dispose();

        labelStyle = new Label.LabelStyle();


        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("16103.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.characters = ukr;
        parameter1.borderWidth = 1;
        parameter1.size = 45;
        parameter1.color = Color.WHITE;
        parameter1.shadowOffsetX = 2;
        parameter1.shadowOffsetY = 2;
        parameter1.shadowColor = new Color(0,0.2f,0,0);
        BitmapFont fontb = generator1.generateFont(parameter1);
        generator1.dispose();

        labelStyle = new Label.LabelStyle();
        labelStyle.font = fonts;
        timer=new float[16];
        for(int i=0;i<16;i++){
            timer[i]=0;

        }
        location1= new Label("Локація 1",labelStyle);
        location2= new Label("Локація 2",labelStyle);
        location3= new Label("Локація 3",labelStyle);
        location4= new Label("Локація 4",labelStyle);
        level1= new Label("Рівень 1",labelStyle);
        level2= new Label("Рівень 2",labelStyle);
        vedme=new Label("Босс: Ведме",labelStyle);
        labelStyle.font=fontb;
        NGSnadpis= new Label("ви впевнені що хочете \n розпочати нову гру",labelStyle);
        DIO=new Label("ДIO",labelStyle);
        pirog=new Label("Босс: ПИРІГ ПИРОГ",labelStyle);
        labelStyle.font=fonts;
        defis=new Label("__________",labelStyle);
        upgrade= new Label("Апгрейд пушки отриманий!",labelStyle);


        defoult(location1);
        defoult(location2);
        defoult(location3);
        defoult(location4);

        NGSnadpis.setSize(10,5);
        NGSnadpis.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-150);
        NGSnadpis.setColor(1,1,1,1);
        NGSnadpis.setAlignment(4,3);

        level1.setSize(10,5);
        level1.setPosition(Gdx.graphics.getWidth()/2-40, Gdx.graphics.getHeight()-50);
        level1.setColor(1,1,1,1);
        level1.setAlignment(4,3);

        level2.setSize(10,5);
        level2.setPosition(Gdx.graphics.getWidth()/2-40, Gdx.graphics.getHeight()-50);
        level2.setColor(1,1,1,1);
        level2.setAlignment(4,3);

        upgrade.setSize(10,5);
        upgrade.setPosition(Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()-150);
        upgrade.setColor(1,1,1,1);
        upgrade.setAlignment(4,3);

        defis.setSize(10,5);
        defis.setPosition(Gdx.graphics.getWidth()/2-80, Gdx.graphics.getHeight()-48);
        defis.setColor(1,1,1,1);
        defis.setAlignment(4,3);

        vedme.setSize(10,5);
        vedme.setPosition(Gdx.graphics.getWidth()/2-60, Gdx.graphics.getHeight()-60);
        vedme.setColor(1,1,1,1);
        vedme.setAlignment(4,3);

        DIO.setSize(10,5);
        DIO.setPosition(Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()-60);
        DIO.setColor(1,1,1,1);
        DIO.setAlignment(4,3);

        pirog.setSize(10,5);
        pirog.setPosition(Gdx.graphics.getWidth()/2-60, Gdx.graphics.getHeight()-60);
        pirog.setColor(1,1,1,1);
        pirog.setAlignment(4,3);



        newGame= new Label("Нова\n гра",labelStyle);
        continie= new Label("Продовжити\nГру",labelStyle);
        nastr= new Label("Налаштування",labelStyle);
        exit= new Label("Вихід",labelStyle);

        newGame.setSize(10,5);
        newGame.setPosition(744,546 );
        newGame.setColor(1,1,1,1);
        newGame.setAlignment(4,3);

        continie.setSize(10,5);
        continie.setPosition(1117,539 );
        continie.setColor(1,1,1,1);
        continie.setAlignment(4,3);

        nastr.setSize(10,5);
        nastr.setPosition(758,214 );
        nastr.setColor(1,1,1,1);
        nastr.setAlignment(4,3);

        exit.setSize(10,5);
        exit.setPosition(1118,214 );
        exit.setColor(1,1,1,1);
        exit.setAlignment(4,3);

    }
    private void defoult(Label l){
        l.setSize(10,5);
        l.setPosition(Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()-100);
        l.setColor(1,1,1,1);
        l.setAlignment(4,3);
    }
    public static void drawLabel(float dt,int n){
        batch.begin();

        if(n==1&&timer[n-1]<=5f){
            location1.draw(batch,1);
        }
        if(n==2&&timer[n-1]<=5f){
            location2.draw(batch,1);
        }
        if(n==3&&timer[n-1]<=5f){
            location3.draw(batch,1);
        }
        if(n==4&&timer[n-1]<=5f){
            location4.draw(batch,1);
        }
        if(n==5&&timer[n-1]<=5f){
            level1.draw(batch,1);
        }
        if(n==6&&timer[n-1]<=5f){
            level2.draw(batch,1);
        }
        if(n==7&&timer[n-1]<=5f){

            vedme.draw(batch,1);
            DIO.draw(batch,1);
        }
        if(n==8&&timer[n-1]<=5f){
            pirog.draw(batch,1);
        }

        if(n==9&&timer[n-1]<=5f){
            upgrade.draw(batch,1);
        }

        if(n==10&&timer[n-1]<=5f){
            defis.draw(batch,1);
        }
        timer[n-1]+=dt;
        if(n==11){
            Labels.newGame.draw(batch,1);
        }
        if(n==12){
            Labels.continie.draw(batch,1);
        }
        if(n==13){
            Labels.nastr.draw(batch,1);
        }
        if(n==14){
            Labels.exit.draw(batch,1);
        }
        if(n==15){
            Labels.NGSnadpis.draw(batch,1);
        }
        batch.end();
    }
}
