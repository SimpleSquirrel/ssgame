package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Graphics.Assets;
import com.mygdx.game.MyGame;


public class WeaponScreen implements Screen {

    private MyGame game;
    private SpriteBatch batch;
    private Viewport viewport;

    private Label   labelWeapon1,
            labelWeapon2,
            labelWeapon3,
            labelWeapon4,
            labelWeapon5,
            labelWeapon6,
            labelWeapon7,
            labelWeapon8,
            labelWeapon9,
            labelWeapon10,
            labelWeapon11,
            labelWeapon12,
            labelWeapon13;

    private Label labelDiscription, labelDiscriptionWeaponName;
    private Label labelWeaponCost;
    private Label labelBuy;
    private Label labelMoney;
    private String[] disc = new String[13];
    private String[][] keys = new String[13][4];
    private int high = -1;
    private static int h ;
    private int score;
    private boolean size;

    private String unknown = "???";

    public WeaponScreen(MyGame game)
    {
        Sprite sprite = new Sprite();
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera(); //Initialising camera
        camera.setToOrtho(false, 1600, 900); //setting sizes for camera
        batch = new SpriteBatch();
        viewport= new FitViewport(1600,900, camera);
        size= Gdx.graphics.getHeight() > 800;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("test.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        String ukr = "абвгдеєжзиіїйклмнопрстуфхцчшщьюяabcdefghijklmnopqrstuvwxyzАБВГДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        parameter.characters = ukr;
        parameter.borderWidth = 1;
        parameter.size = 12;
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = new Color(0,0.2f,0,0.8f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        //Оружия
        labelWeapon1 = new Label(unknown, labelStyle);    labelWeapon2 = new Label(unknown, labelStyle);
        labelWeapon3 = new Label(unknown, labelStyle);    labelWeapon4 = new Label(unknown, labelStyle);
        labelWeapon5 = new Label(unknown, labelStyle);    labelWeapon6 = new Label(unknown, labelStyle);
        labelWeapon7 = new Label(unknown, labelStyle);    labelWeapon8 = new Label(unknown, labelStyle);
        labelWeapon9 = new Label(unknown, labelStyle);    labelWeapon10= new Label(unknown, labelStyle);
        labelWeapon11= new Label(unknown, labelStyle);    labelWeapon12= new Label(unknown, labelStyle);
                                labelWeapon13= new Label(unknown, labelStyle);
        //Имена(не трогать)
        labelWeapon1.setName("1");
        labelWeapon2.setName("2");
        labelWeapon3.setName("3");
        labelWeapon4.setName("4");
        labelWeapon5.setName("5");
        labelWeapon6.setName("6");
        labelWeapon7.setName("7");
        labelWeapon8.setName("8");
        labelWeapon9.setName("9");
        labelWeapon10.setName("10");
        labelWeapon11.setName("11");
        labelWeapon12.setName("12");
        labelWeapon13.setName("13");

        //Шаблонные характеристики 1600*900
        score = MyGame.preferences.getInteger("score");
        if(size) {
            h=900;
            defaults(labelWeapon1, 100, 700);
            defaults(labelWeapon2, 300, 700);
            defaults(labelWeapon3, 500, 700);
            defaults(labelWeapon4, 100, 550);
            defaults(labelWeapon5, 300, 550);
            defaults(labelWeapon6, 500, 550);
            defaults(labelWeapon7, 100, 400);
            defaults(labelWeapon8, 300, 400);
            defaults(labelWeapon9, 500, 400);
            defaults(labelWeapon10, 100, 250);
            defaults(labelWeapon11, 300, 250);
            defaults(labelWeapon12, 500, 250);
            defaults(labelWeapon13, 300, 100);
            //Название оружия
            labelDiscriptionWeaponName = new Label("", labelStyle);
            defaults(labelDiscriptionWeaponName, 1250,700);
            labelDiscriptionWeaponName.setFontScale(2);
            //Описание
            labelDiscription = new Label("", labelStyle);
            labelDiscription.setPosition(1150,550);
            labelWeaponCost = new Label("", labelStyle);
            labelWeaponCost.setPosition(1150,250);
            labelWeaponCost.setSize(75,0);
            //
            labelBuy = new Label("Купити", labelStyle);
            defaults(labelBuy, 1275,150);
            //
            labelMoney = new Label("Запас детальок: " + score, labelStyle);
            labelMoney.setPosition(1150,275);
            labelMoney.setSize(75,0);
        }
        if(!size) {
            h=720;
            //Шаблонные характеристики 1366*720
            defaults(labelWeapon1, 50, 620);
            defaults(labelWeapon2, 250, 620);
            defaults(labelWeapon3, 450, 620);
            defaults(labelWeapon4, 50, 470);
            defaults(labelWeapon5, 250, 470);
            defaults(labelWeapon6, 450, 470);
            defaults(labelWeapon7, 50, 320);
            defaults(labelWeapon8, 250, 320);
            defaults(labelWeapon9, 450, 320);
            defaults(labelWeapon10, 50, 170);
            defaults(labelWeapon11, 250, 170);
            defaults(labelWeapon12, 450, 170);
            defaults(labelWeapon13, 250, 40);
            //Название оружия
            labelDiscriptionWeaponName = new Label("", labelStyle);
            defaults(labelDiscriptionWeaponName, 1050,700);
            labelDiscriptionWeaponName.setFontScale(2);
            //Описание
            labelDiscription = new Label("", labelStyle);
            labelDiscription.setPosition(950,550);
            labelWeaponCost = new Label("", labelStyle);
            labelWeaponCost.setPosition(950,250);
            labelWeaponCost.setSize(75,0);

            labelBuy = new Label("Купити", labelStyle);
            defaults(labelBuy, 1075,150);

            labelMoney = new Label("Запас детальок: " + score, labelStyle);
            labelMoney.setPosition(950,275);
            labelMoney.setSize(75,0);
        }

        //Описание оружия
        disc[0] = "Пушка, яка вистрілює дві пулі за раз.\nНайгірша зброя, яка могла випасти.";
        disc[1] = "Три пулі за раз. Непогано.";
        disc[2] = "Чотири пулі за раз. Джин одобрює.";
        disc[3] = "Пентакілл! Кхм. П'ять пуль за раз.";
        disc[4] = "Пулька одна, але летить в два рази швидше.";
        disc[5] = "Дві пулі з подвійною швидкістю.";
        disc[6] = "Три пулі з подвійною швидкістю.";
        disc[7] = "Чотири пулі з подвійною швидкістю.\nДжин одобрює.";
        disc[8] = "Одна пуля з потрійною швидкістю.";
        disc[9] = "Дві пулі з потрійною швидкістю.";
        disc[10] = "Одна пуля, яка летить швидше Флеша.";
        disc[11] = "Просто дробаш.";
        disc[12] = "Ви пройшли пятнашки.\nТаке не кожному під силу.\nВашою нагородою буде дробаш,\nпулі якого летять з подвійною швидкістю.";
        //Ключ на True/False                      Стоимость                      Текст на вывод при True
        keys[0][0]    = "doubleGun";              keys[0][2] = "500";              keys[0][1]   =   "Дібілган";
        keys[1][0]    = "tripleGun";              keys[1][2] = "600";              keys[1][1]   =   "Трібілган";
        keys[2][0]    = "quadraGun";              keys[2][2] = "700";              keys[2][1]   =   "Джинаган";
        keys[3][0]    = "pentaGun";               keys[3][2] = "800";              keys[3][1]   =   "Петяган";
        keys[4][0]    = "fastGun";                keys[4][2] = "600";              keys[4][1]   =   "Бистраган";
        keys[5][0]    = "doubleFastGun";          keys[5][2] = "800";              keys[5][1]   =   "Дібілбистраган";
        keys[6][0]    = "tripleFastGun";          keys[6][2] = "1000";              keys[6][1]   =   "Трібілбистраган";
        keys[7][0]    = "quadraFastGun";          keys[7][2] = "1200";              keys[7][1]   =   "Джинабистраган";
        keys[8][0]    = "veryFastGun";            keys[8][2] = "1000";              keys[8][1]   =   "Дужебистраган";
        keys[9][0]    = "veryFastDoubleGun";      keys[9][2] = "1250";             keys[9][1]   =   "Дужебистрадібілган";
        keys[10][0]   = "ultraFastGun";           keys[10][2] = "1300";            keys[10][1]  =   "Ульрабистраган";
        keys[11][0]   = "shotgun";                keys[11][2] = "1400";            keys[11][1]  =   "Дробаган";
        keys[12][0]   = "fastShotgun";            keys[12][2] = "1500";            keys[12][1]  =   "Дробадібілган";

        keys[0][3]    = "doubleGunBought";
        keys[1][3]    = "tripleGunBought";
        keys[2][3]    = "quadraGunBought";
        keys[3][3]    = "pentaGunBought";
        keys[4][3]    = "fastGunBought";
        keys[5][3]    = "doubleFastGunBought";
        keys[6][3]    = "tripleFastGunBought";
        keys[7][3]    = "quadraFastGunBought";
        keys[8][3]    = "veryFastGunBought";
        keys[9][3]    = "veryFastDoubleGunBought";
        keys[10][3]   = "ultraFastGunBought";
        keys[11][3]   = "shotgunBought";
        keys[12][3]   = "fastShotgunBought";
    }

    private void defaults(Label label, int x, int y)
    {
        label.setPosition(x,y);
        label.setSize(75, 0);
        label.setAlignment(4,3);
    }

    private void drawWeapons()
    {
        if (Gdx.input.getX() <  1100 && Gdx.input.getX() >  675 &&
                Gdx.input.getY() < 700  && Gdx.input.getY() > 0)
            batch.draw(Assets.spriteOpenedDoor, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        else
            batch.draw(Assets.spriteClosedDoor, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(Assets.spriteRobot,100,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //Оружия
        batch.draw(Assets.spriteLable, labelWeapon1.getX()- 95, labelWeapon1.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon2.getX()- 95, labelWeapon2.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon3.getX()- 95, labelWeapon3.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon4.getX()- 95, labelWeapon4.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon5.getX()- 95, labelWeapon5.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon6.getX()- 95, labelWeapon6.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon7.getX()- 95, labelWeapon7.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon8.getX()- 95, labelWeapon8.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon9.getX()- 95, labelWeapon9.getY()- 90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon10.getX()-95, labelWeapon10.getY()-90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon11.getX()-95, labelWeapon11.getY()-90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon12.getX()-95, labelWeapon12.getY()-90, 300,250);
        batch.draw(Assets.spriteLable, labelWeapon13.getX()-95, labelWeapon13.getY()-90, 300,250);
        switch (whoIsTouched)
        {
            case 1: batch.draw(Assets.spriteLightLable, labelWeapon1.getX() -95, labelWeapon1.getY() -90, 300,250);break;
            case 2: batch.draw(Assets.spriteLightLable, labelWeapon2.getX() -95, labelWeapon2.getY() -90, 300,250);break;
            case 3: batch.draw(Assets.spriteLightLable, labelWeapon3.getX() -95, labelWeapon3.getY() -90, 300,250);break;
            case 4: batch.draw(Assets.spriteLightLable, labelWeapon4.getX() -95, labelWeapon4.getY() -90, 300,250);break;
            case 5: batch.draw(Assets.spriteLightLable, labelWeapon5.getX() -95, labelWeapon5.getY() -90, 300,250);break;
            case 6: batch.draw(Assets.spriteLightLable, labelWeapon6.getX() -95, labelWeapon6.getY() -90, 300,250);break;
            case 7: batch.draw(Assets.spriteLightLable, labelWeapon7.getX() -95, labelWeapon7.getY() -90, 300,250);break;
            case 8: batch.draw(Assets.spriteLightLable, labelWeapon8.getX() -95, labelWeapon8.getY() -90, 300,250);break;
            case 9: batch.draw(Assets.spriteLightLable, labelWeapon9.getX() -95, labelWeapon9.getY() -90, 300,250);break;
            case 10:batch.draw(Assets.spriteLightLable, labelWeapon10.getX()-95, labelWeapon10.getY()-90, 300,250);break;
            case 11:batch.draw(Assets.spriteLightLable, labelWeapon11.getX()-95, labelWeapon11.getY()-90, 300,250);break;
            case 12:batch.draw(Assets.spriteLightLable, labelWeapon12.getX()-95, labelWeapon12.getY()-90, 300,250);break;
            case 13:batch.draw(Assets.spriteLightLable, labelWeapon13.getX()-95, labelWeapon13.getY()-90, 300,250);break;
        }

        labelWeapon1.draw(batch, 1);
        labelWeapon2.draw(batch, 1);
        labelWeapon3.draw(batch, 1);
        labelWeapon4.draw(batch, 1);
        labelWeapon5.draw(batch, 1);
        labelWeapon6.draw(batch, 1);
        labelWeapon7.draw(batch, 1);
        labelWeapon8.draw(batch, 1);
        labelWeapon9.draw(batch, 1);
        labelWeapon10.draw(batch, 1);
        labelWeapon11.draw(batch, 1);
        labelWeapon12.draw(batch, 1);
        labelWeapon13.draw(batch, 1);



        //Описание
        if(anyWeaponPressed)
        {
            if(size) {
                batch.draw(Assets.spritePaper, 1000, 100, 600, 700);
            }
            if(!size) {
                batch.draw(Assets.spritePaper, 800, 100, 600, 700);
            }
            labelBuy.draw(batch, 1);
            labelDiscription.draw(batch,1);

            labelDiscriptionWeaponName.draw(batch,1);

            labelWeaponCost.draw(batch,1);
            labelMoney.draw(batch,1);
            if (Gdx.input.getX() < labelBuy.getX() + 100 && Gdx.input.getX() > labelBuy.getX() - 100 &&
                    Gdx.input.getY() < h - labelBuy.getY() + 25  && Gdx.input.getY() > h - labelBuy.getY() - 25) {
                if (Gdx.input.isTouched()) {
                    if ((MyGame.preferences.getBoolean(keys[whoIsTouched-1][0]) &&
                            score >= Integer.parseInt(String.valueOf(labelWeaponCost.getText().replace("Ціна: ", "")))) &&
                            score > 0 &&
                            !MyGame.preferences.getBoolean(keys[whoIsTouched - 1][3]))
                    {
                        score -= Integer.parseInt(String.valueOf(labelWeaponCost.getText().replace("Ціна: ", "")));
                        MyGame.preferences.putInteger("score", score);
                        labelMoney.setText("Всього детальок: " + score);
                        MyGame.preferences.putBoolean(keys[whoIsTouched-1][3], true);
                        MyGame.preferences.flush();
                        //System.out.println(keys[whoIsTouched-1][3] + " = " + game.preferences.getBoolean(keys[whoIsTouched-1][3]));
                        labelDiscriptionWeaponName.setText(keys[whoIsTouched-1][1]);
                        labelDiscription.setText(disc[whoIsTouched-1]);

                        if(high < whoIsTouched - 1)
                            high = whoIsTouched - 1;
                    }
                }
            }

        }
    }
    private int whoIsTouched;
    private boolean anyWeaponPressed = false;
    private void weaponPress(Label label, String line, String key) {
        if (Gdx.input.getX() < label.getX() + 150 && Gdx.input.getX() > label.getX() - 25 &&
                Gdx.input.getY() < h - label.getY() + 25 && Gdx.input.getY() > h - label.getY() - 100) {
            if (Gdx.input.isTouched()) {
                whoIsTouched = Integer.parseInt(label.getName());
                if (MyGame.preferences.getBoolean(key)) {

                    labelDiscription.setText(line);
                    labelDiscriptionWeaponName.setText(keys[Integer.parseInt(label.getName()) - 1][1]);
                    labelWeaponCost.setText("Ціна: " + keys[Integer.parseInt(label.getName()) - 1][2]);
                    anyWeaponPressed = true;

                } else {
                    labelDiscription.setText(unknown);
                    labelDiscriptionWeaponName.setText(unknown);
                    labelWeaponCost.setText("Ціна: " + unknown);
                    anyWeaponPressed = true;
                }
            }
        }
    }

    private String expen()
    {
        String line = "";
        int highest = 0;
        for(int i = 0; i < 13; i++)
        {
            if(MyGame.preferences.getBoolean(keys[i][3]))
            {
                if (highest < Integer.parseInt(keys[i][2]))
                {
                    line = keys[i][0];
                }
            }
        }
        return line;
    }

    private void checkers()
    {
        if (Gdx.input.getX() <  1100 && Gdx.input.getX() >  675 &&
                Gdx.input.getY() < 700  && Gdx.input.getY() > 0)
        {
            if (Gdx.input.isTouched())
            {
                MyGame.preferences.putString("weapon", expen());
                MyGame.preferences.flush();
                System.out.println(expen());
                game.setScreen(new LoadScreen(this.game));
            }
        }


        weaponPress(labelWeapon1, disc[0], keys[0][0]);
        weaponPress(labelWeapon2, disc[1], keys[1][0]);
        weaponPress(labelWeapon3, disc[2], keys[2][0]);
        weaponPress(labelWeapon4, disc[3], keys[3][0]);
        weaponPress(labelWeapon5, disc[4], keys[4][0]);
        weaponPress(labelWeapon6, disc[5], keys[5][0]);
        weaponPress(labelWeapon7, disc[6], keys[6][0]);
        weaponPress(labelWeapon8, disc[7], keys[7][0]);
        weaponPress(labelWeapon9, disc[8], keys[8][0]);
        weaponPress(labelWeapon10, disc[9], keys[9][0]);
        weaponPress(labelWeapon11, disc[10], keys[10][0]);
        weaponPress(labelWeapon12, disc[11], keys[11][0]);
        weaponPress(labelWeapon13, disc[12], keys[12][0]);

        if(MyGame.preferences.getBoolean(keys[0][0])) labelWeapon1.setText(keys[0][0]);
        if(MyGame.preferences.getBoolean(keys[1][0])) labelWeapon2.setText(keys[1][0]);
        if(MyGame.preferences.getBoolean(keys[2][0])) labelWeapon3.setText(keys[2][0]);
        if(MyGame.preferences.getBoolean(keys[3][0])) labelWeapon4.setText(keys[3][0]);
        if(MyGame.preferences.getBoolean(keys[4][0])) labelWeapon5.setText(keys[4][0]);
        if(MyGame.preferences.getBoolean(keys[5][0])) labelWeapon6.setText(keys[5][0]);
        if(MyGame.preferences.getBoolean(keys[6][0])) labelWeapon7.setText(keys[6][0]);
        if(MyGame.preferences.getBoolean(keys[7][0])) labelWeapon8.setText(keys[7][0]);
        if(MyGame.preferences.getBoolean(keys[8][0])) labelWeapon9.setText(keys[8][0]);
        if(MyGame.preferences.getBoolean(keys[9][0])) labelWeapon10.setText(keys[9][0]);
        if(MyGame.preferences.getBoolean(keys[10][0])) labelWeapon11.setText(keys[10][0]);
        if(MyGame.preferences.getBoolean(keys[11][0])) labelWeapon12.setText(keys[11][0]);
        if(MyGame.preferences.getBoolean(keys[12][0])) labelWeapon13.setText(keys[12][0]);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1); //setting bg color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Idk

        batch.begin();
        checkers();
        drawWeapons();


        batch.end();

    }

    @Override
    public void resize(int width, int height) {
    viewport.update(width,height);
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
