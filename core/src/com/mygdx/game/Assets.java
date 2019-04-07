package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {
    //MenuScreen
    public static Texture textureMenuScreenBack;
    public static Sprite spriteMenuScreenBack;
    public static Texture textureNewGameButtonActive;
    public static Texture textureContinueGameButtonActive;
    public static Texture texturePreferencesButtonActive;
    public static Texture textureExitButtonActive;
    public static Sprite spriteNewGameButtonActive;
    public static Sprite spriteContinueGameButtonActive;
    public static Sprite spritePreferencesButtonActive;
    public static Sprite spriteExitButtonActive;
    public static Texture textureNewGameButtonInactive;
    public static Texture textureContinueGameButtonInactive;
    public static Texture texturePreferencesButtonInactive;
    public static Texture textureExitButtonInactive;
    public static Sprite spriteNewGameButtonInactive;
    public static Sprite spriteContinueGameButtonInactive;
    public static Sprite spritePreferencesButtonInactive;
    public static Sprite spriteExitButtonInactive;
    //PreferencesScreen
    public static Texture texturePreferencesScreenBack;
    public static Sprite spritePreferencesScreenBack;
    public static Texture textureMusicButtonActive;
    public static Sprite spriteMusicButtonActive;
    public static Texture textureMusicButtonInactive;
    public static Sprite spriteMusicButtonInactive;
    public static Texture textureMusicScroller0;
    public static Sprite spriteMusicScroller0;
    public static Texture textureMusicScroller10;
    public static Sprite spriteMusicScroller10;
    public static Texture textureMusicScroller20;
    public static Sprite spriteMusicScroller20;
    public static Texture textureMusicScroller30;
    public static Sprite spriteMusicScroller30;
    public static Texture textureMusicScroller40;
    public static Sprite spriteMusicScroller40;
    public static Texture textureMusicScroller50;
    public static Sprite spriteMusicScroller50;
    public static Texture textureMusicScroller60;
    public static Sprite spriteMusicScroller60;
    public static Texture textureMusicScroller70;
    public static Sprite spriteMusicScroller70;
    public static Texture textureMusicScroller80;
    public static Sprite spriteMusicScroller80;
    public static Texture textureMusicScroller90;
    public static Sprite spriteMusicScroller90;
    public static Texture textureMusicScroller100;
    public static Sprite spriteMusicScroller100;
    public static Texture textureTurnUp;
    public static Sprite spriteTurnUp;
    public static Texture textureTurnDown;
    public static Sprite spriteTurnDown;
    //Game Itself
    public static Texture textureMC;
    public static Sprite spriteMC;
    public static Texture texturePie;
    public static Sprite spritePie;



    public static void load() {
        //Menu
        textureMenuScreenBack = new Texture(Gdx.files.internal("MenuScreen/Back.jpg"));
        textureMenuScreenBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMenuScreenBack = new Sprite(textureMenuScreenBack);
        spriteMenuScreenBack.flip(false, true);

        textureNewGameButtonActive = new Texture(Gdx.files.internal("MenuScreen/NewGame.png"));
        textureNewGameButtonActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteNewGameButtonActive = new Sprite(textureNewGameButtonActive);
        spriteNewGameButtonActive.flip(false, true);

        textureContinueGameButtonActive = new Texture(Gdx.files.internal("MenuScreen/ContinueGame.png"));
        textureContinueGameButtonActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteContinueGameButtonActive = new Sprite(textureContinueGameButtonActive);
        spriteContinueGameButtonActive.flip(false, true);

        texturePreferencesButtonActive = new Texture(Gdx.files.internal("MenuScreen/Preferences.png"));
        texturePreferencesButtonActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePreferencesButtonActive = new Sprite(texturePreferencesButtonActive);
        spritePreferencesButtonActive.flip(false, true);

        textureExitButtonActive = new Texture(Gdx.files.internal("MenuScreen/Exit.png"));
        textureExitButtonActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteExitButtonActive = new Sprite(textureExitButtonActive);
        spriteExitButtonActive.flip(false, true);

        textureNewGameButtonInactive = new Texture(Gdx.files.internal("MenuScreen/NewGame0.png"));
        textureNewGameButtonInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteNewGameButtonInactive = new Sprite(textureNewGameButtonInactive);
        spriteNewGameButtonInactive.flip(false, true);

        textureContinueGameButtonInactive = new Texture(Gdx.files.internal("MenuScreen/ContinueGame0.png"));
        textureContinueGameButtonInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteContinueGameButtonInactive = new Sprite(textureContinueGameButtonInactive);
        spriteContinueGameButtonInactive.flip(false, true);

        texturePreferencesButtonInactive = new Texture(Gdx.files.internal("MenuScreen/Preferences0.png"));
        texturePreferencesButtonInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePreferencesButtonInactive = new Sprite(texturePreferencesButtonInactive);
        spritePreferencesButtonInactive.flip(false, true);

        textureExitButtonInactive = new Texture(Gdx.files.internal("MenuScreen/Exit0.png"));
        textureExitButtonInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteExitButtonInactive = new Sprite(textureExitButtonInactive);
        spriteExitButtonInactive.flip(false, true);
        //Preferences
        texturePreferencesScreenBack = new Texture(Gdx.files.internal("PreferencesScreen/Back.jpg"));
        texturePreferencesScreenBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePreferencesScreenBack = new Sprite(texturePreferencesScreenBack);
        spritePreferencesScreenBack.flip(false, true);

        textureMusicButtonActive = new Texture(Gdx.files.internal("PreferencesScreen/Button.png"));
        textureMusicButtonActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicButtonActive = new Sprite(textureMusicButtonActive);
        spriteMusicButtonActive.flip(false, true);

        textureMusicButtonInactive = new Texture(Gdx.files.internal("PreferencesScreen/Button0.png"));
        textureMusicButtonInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicButtonInactive = new Sprite(textureMusicButtonInactive);
        spriteMusicButtonInactive.flip(false, true);

        textureMusicScroller0 = new Texture(Gdx.files.internal("PreferencesScreen/Sound0.png"));
        textureMusicScroller0.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller0 = new Sprite(textureMusicScroller0);
        spriteMusicScroller0.flip(false, true);

        textureMusicScroller10 = new Texture(Gdx.files.internal("PreferencesScreen/Sound10.png"));
        textureMusicScroller10.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller10 = new Sprite(textureMusicScroller10);
        spriteMusicScroller10.flip(false, true);

        textureMusicScroller20 = new Texture(Gdx.files.internal("PreferencesScreen/Sound20.png"));
        textureMusicScroller20.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller20 = new Sprite(textureMusicScroller20);
        spriteMusicScroller20.flip(false, true);

        textureMusicScroller30 = new Texture(Gdx.files.internal("PreferencesScreen/Sound30.png"));
        textureMusicScroller30.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller30 = new Sprite(textureMusicScroller30);
        spriteMusicScroller30.flip(false, true);

        textureMusicScroller40 = new Texture(Gdx.files.internal("PreferencesScreen/Sound40.png"));
        textureMusicScroller40.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller40 = new Sprite(textureMusicScroller40);
        spriteMusicScroller40.flip(false, true);

        textureMusicScroller50 = new Texture(Gdx.files.internal("PreferencesScreen/Sound50.png"));
        textureMusicScroller50.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller50 = new Sprite(textureMusicScroller50);
        spriteMusicScroller50.flip(false, true);

        textureMusicScroller60 = new Texture(Gdx.files.internal("PreferencesScreen/Sound60.png"));
        textureMusicScroller60.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller60 = new Sprite(textureMusicScroller60);
        spriteMusicScroller60.flip(false, true);

        textureMusicScroller70 = new Texture(Gdx.files.internal("PreferencesScreen/Sound70.png"));
        textureMusicScroller70.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller70 = new Sprite(textureMusicScroller70);
        spriteMusicScroller70.flip(false, true);

        textureMusicScroller80 = new Texture(Gdx.files.internal("PreferencesScreen/Sound80.png"));
        textureMusicScroller80.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller80 = new Sprite(textureMusicScroller80);
        spriteMusicScroller80.flip(false, true);

        textureMusicScroller90 = new Texture(Gdx.files.internal("PreferencesScreen/Sound90.png"));
        textureMusicScroller90.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller90 = new Sprite(textureMusicScroller90);
        spriteMusicScroller90.flip(false, true);

        textureMusicScroller100 = new Texture(Gdx.files.internal("PreferencesScreen/Sound100.png"));
        textureMusicScroller100.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteMusicScroller100 = new Sprite(textureMusicScroller100);
        spriteMusicScroller100.flip(false, true);

        textureTurnUp = new Texture(Gdx.files.internal("PreferencesScreen/TurnUp.png"));
        textureTurnUp.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnUp = new Sprite(textureTurnUp);
        spriteTurnUp.flip(false, true);

        textureTurnDown = new Texture(Gdx.files.internal("PreferencesScreen/TurnDown.png"));
        textureTurnDown.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnDown = new Sprite(textureTurnDown);
        spriteTurnDown.flip(false, true);

        textureMC = new Texture(Gdx.files.internal("Game/MC.png"));
        spriteMC = new Sprite(textureMC);

        texturePie = new Texture(Gdx.files.internal("Game/Pie.png"));
        spritePie = new Sprite(texturePie);
    }
}