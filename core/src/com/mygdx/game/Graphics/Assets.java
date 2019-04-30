package com.mygdx.game.Graphics;

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


    //Game Itself
    public static Texture textureMC;
    public static Sprite spriteMC;
    public static Texture texturePie;
    public static Sprite spritePie;
    //DeathScreen

    // Sprites
    public static Sprite spriteDeathScreenBack;
    public static Sprite spriteDeathScreenDaActive;
    public static Sprite spriteDeathScreenDaInActive;
    public static Sprite spriteDeathScreenTosheDa;
    public static Sprite spriteDeathScreenNoInActive;

    // textures
    public static Texture textureDeathScreenBack;
    public static Texture textureDeathScreenDaActive;
    public static Texture textureDeathScreenDaInActive;
    public static Texture textureDeathScreenTosheDa;
    public static Texture textureDeathScreenNoInactive;
    //ExitScreen
        //Sprites
    public static Sprite spriteNoActive;
    public static Sprite spriteExitScreenBack;
        //Textures
    public static Texture textureNoActive;
    public static Texture textureExitScreenBack;
    //New Game Screen
        //BackGround
    public static Sprite spriteNewGameScreenBack;
    public static Texture textureNewGameScreenBack;
    //Bullet

    //LoadScreen
    public static Texture textureLoadBack;
    public static Sprite loadBack;

    public static Texture loadAnimation;


    //Familiar
        //1
    public static Sprite spriteFamiliar1Active;
    public static Texture textureFamiliar1Active;
    public static Sprite spriteFamiliar1Inactive;
    public static Texture textureFamiliar1Inactive;
        //2
    public static Sprite spriteFamiliar2Active;
    public static Texture textureFamiliar2Active;
    public static Sprite spriteFamiliar2Inactive;
    public static Texture textureFamiliar2Inactive;

    //Preferences Screen

    public static Texture texturePreferencesScreenBack;
    public static Sprite spritePreferencesScreenBack;

    public static Sprite spriteShesternyaVerh;
    public static Texture textureShesternyaVerh;

    public static Sprite spriteShesternyaNiz;
    public static Texture textureShesternyaNiz;

    public static Sprite spriteBigSkalaVerh;
    public static Texture textureBigSkalaVerh;

    public static Sprite spriteBigSkalaNiz;
    public static Texture textureBigSkalaNiz;

    public static Sprite spriteGrom;
    public static Texture textureGrom;

    public static Sprite spriteGuchnistMuz;
    public static Texture textureGuchnistMuz;

    public static Sprite spriteGuchnistZv;
    public static Texture textureGuchnistZv;

    public static Sprite spriteNadpisMusica;
    public static Texture textureNadpisMuzica;

    public static Sprite spriteNadpisZvuki;
    public static Texture textureNadpisZvuki;

    public static Sprite spriteNoteList;
    public static Texture textureNoteList;

    public static Sprite spritePoleProtsNiz;
    public static Texture texturePoleProtsNiz;

    public static Sprite spritePoleProtsVerh;
    public static Texture texturePoleProtsVerh;

    public static Sprite spriteSkalaNiz;
    public static Texture textureSkalaNiz;

    public static Sprite spriteSkalaVerh;
    public static Texture textureSkalaVerh;

    public static Texture textureTurnUpActive1;
    public static Sprite spriteTurnUpActive1;

    public static Texture textureTurnDownActive1;
    public static Sprite spriteTurnDownActive1;

    public static Texture textureTurnUpActive2;
    public static Sprite spriteTurnUpActive2;

    public static Texture textureTurnDownActive2;
    public static Sprite spriteTurnDownActive2;

    public static Texture textureTurnUpInactive;
    public static Sprite spriteTurnUpInactive;

    public static Texture textureTurnDownInactive;
    public static Sprite spriteTurnDownInactive;

    //Loading
    public static Texture textureLoad1;
    public static Texture textureLoad2;
    public static Texture textureLoad3;
    public static Texture textureLoad4;
    public static Sprite spriteLoad1;
    public static Sprite spriteLoad2;
    public static Sprite spriteLoad3;
    public static Sprite spriteLoad4;
    public static Texture textureEmpty;
    public static Sprite spriteEmpty;

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


        textureTurnUpActive1 = new Texture(Gdx.files.internal("PreferencesScreen/TurnUpActive.png"));
        textureTurnUpActive1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnUpActive1 = new Sprite(textureTurnUpActive1);
        //spriteTurnUp.flip(false, true);

        textureTurnUpActive2 = new Texture(Gdx.files.internal("PreferencesScreen/TurnUpActive.png"));
        textureTurnUpActive2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnUpActive2 = new Sprite(textureTurnUpActive2);
        //spriteTurnUp.flip(false, true);

        textureTurnUpInactive = new Texture(Gdx.files.internal("PreferencesScreen/TurnUpInactive.png"));
        textureTurnUpInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnUpInactive = new Sprite(textureTurnUpInactive);
        //spriteTurnUp.flip(false, true);

        textureTurnDownActive1 = new Texture(Gdx.files.internal("PreferencesScreen/TurnDownActive.png"));
        textureTurnDownActive1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnDownActive1 = new Sprite(textureTurnDownActive1);
       // spriteTurnDown.flip(false, true);

        textureTurnDownActive2 = new Texture(Gdx.files.internal("PreferencesScreen/TurnDownActive.png"));
        textureTurnDownActive2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnDownActive2 = new Sprite(textureTurnDownActive2);
        // spriteTurnDown.flip(false, true);

        textureTurnDownInactive = new Texture(Gdx.files.internal("PreferencesScreen/TurnDownInactive.png"));
        textureTurnDownInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteTurnDownInactive = new Sprite(textureTurnDownInactive);
        // spriteTurnDown.flip(false, true);

        textureMC = new Texture(Gdx.files.internal("Game/MC.png"));
        spriteMC = new Sprite(textureMC);

        texturePie = new Texture(Gdx.files.internal("Game/Pie.png"));
        spritePie = new Sprite(texturePie);

        textureDeathScreenBack = new Texture(Gdx.files.internal("DeathScreen/Back.png"));
        textureDeathScreenBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteDeathScreenBack = new Sprite(textureDeathScreenBack);
        //spriteDeathScreenBack.flip(false, true);
        //Bootom DA
        //Active
        textureDeathScreenDaActive= new Texture(Gdx.files.internal("DeathScreen/DaActive.png"));
        textureDeathScreenDaActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteDeathScreenDaActive = new Sprite(textureDeathScreenDaActive);
        //InActive
        textureDeathScreenDaInActive= new Texture(Gdx.files.internal("DeathScreen/DaInActive.png"));
        textureDeathScreenDaInActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteDeathScreenDaInActive = new Sprite(textureDeathScreenDaInActive);
        //Bottom TosheDa
        textureDeathScreenTosheDa= new Texture(Gdx.files.internal("DeathScreen/TosheDa.png"));
        textureDeathScreenTosheDa.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteDeathScreenTosheDa = new Sprite(textureDeathScreenTosheDa);
        //Bottom No Inactive
        textureDeathScreenNoInactive= new Texture(Gdx.files.internal("DeathScreen/NoInactive.png"));
        textureDeathScreenNoInactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteDeathScreenNoInActive = new Sprite(textureDeathScreenNoInactive);
        // ExitScreen
        //bottom NO Active
        textureNoActive= new Texture(Gdx.files.internal("ExitScreen/NoActive.png"));
        textureNoActive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteNoActive = new Sprite(textureNoActive);
        //BackGround
        textureExitScreenBack= new Texture(Gdx.files.internal("ExitScreen/Back.png"));
        textureExitScreenBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteExitScreenBack = new Sprite(textureExitScreenBack);
        //newGameScreen
        //Background
        textureNewGameScreenBack= new Texture(Gdx.files.internal("NewGameScreen/Back.png"));
        textureNewGameScreenBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteNewGameScreenBack = new Sprite(textureNewGameScreenBack);
        // Bullet

        //LoadScreen
        loadAnimation = new Texture(Gdx.files.internal("LoadScreen/loadAnimation.png"));
        loadAnimation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // Familiar
            //1
        textureFamiliar1Active= new Texture(Gdx.files.internal("Familiar/familiar1Active.png"));
        textureFamiliar1Active.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteFamiliar1Active = new Sprite(textureFamiliar1Active);

        textureFamiliar1Inactive= new Texture(Gdx.files.internal("Familiar/familiar1Inactive.png"));
        textureFamiliar1Inactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteFamiliar1Inactive = new Sprite(textureFamiliar1Inactive);
            //2
        textureFamiliar2Active= new Texture(Gdx.files.internal("Familiar/2A.png"));
        textureFamiliar2Active.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteFamiliar2Active = new Sprite(textureFamiliar2Active);

        textureLoadBack = new Texture(Gdx.files.internal("LoadScreen/loadBack.png"));
        textureLoadBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureFamiliar2Inactive= new Texture(Gdx.files.internal("Familiar/2I.png"));
        textureFamiliar2Inactive.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteFamiliar2Inactive = new Sprite(textureFamiliar2Inactive);

        //Preferences Scereen

        textureShesternyaNiz= new Texture(Gdx.files.internal("PreferencesScreen/SestNiz.png"));
        textureShesternyaNiz.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteShesternyaNiz = new Sprite(textureShesternyaNiz);
        textureShesternyaVerh= new Texture(Gdx.files.internal("PreferencesScreen/SestVerh.png"));
        textureShesternyaVerh.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteShesternyaVerh = new Sprite(textureShesternyaVerh);

        textureBigSkalaNiz= new Texture(Gdx.files.internal("PreferencesScreen/BigSkalaNiz.png"));
        textureBigSkalaNiz.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteBigSkalaNiz = new Sprite(textureBigSkalaNiz);
        textureBigSkalaVerh= new Texture(Gdx.files.internal("PreferencesScreen/BigSkalaVerh.png"));
        textureBigSkalaVerh.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteBigSkalaVerh = new Sprite(textureBigSkalaVerh);

        textureGrom= new Texture(Gdx.files.internal("PreferencesScreen/Grom.png"));
        textureGrom.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteGrom = new Sprite(textureGrom);

        textureGuchnistMuz= new Texture(Gdx.files.internal("PreferencesScreen/GuchnistMuz.png"));
        textureGuchnistMuz.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteGuchnistMuz = new Sprite(textureGuchnistMuz);

        textureGuchnistZv= new Texture(Gdx.files.internal("PreferencesScreen/GuchnistZv.png"));
        textureGuchnistZv.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteGuchnistZv = new Sprite(textureGuchnistZv);

        textureNadpisMuzica= new Texture(Gdx.files.internal("PreferencesScreen/NadpisMuzica.png"));
        textureNadpisMuzica.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteNadpisMusica = new Sprite(textureNadpisMuzica);

        textureNadpisZvuki= new Texture(Gdx.files.internal("PreferencesScreen/NadpisZvuki.png"));
        textureNadpisZvuki.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteNadpisZvuki = new Sprite(textureNadpisZvuki);

        textureNoteList= new Texture(Gdx.files.internal("PreferencesScreen/NoteList.png"));
        textureNoteList.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteNoteList = new Sprite(textureNoteList);

        texturePoleProtsNiz= new Texture(Gdx.files.internal("PreferencesScreen/PoleProtsNiz.png"));
        texturePoleProtsNiz.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePoleProtsNiz = new Sprite(texturePoleProtsNiz);
        texturePoleProtsVerh= new Texture(Gdx.files.internal("PreferencesScreen/PoleProtsVerh.png"));
        texturePoleProtsVerh.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spritePoleProtsVerh = new Sprite(texturePoleProtsVerh);

        textureSkalaNiz= new Texture(Gdx.files.internal("PreferencesScreen/SkalaNiz.png"));
        textureSkalaNiz.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteSkalaNiz = new Sprite(textureSkalaNiz);
        textureSkalaVerh= new Texture(Gdx.files.internal("PreferencesScreen/SkalaVerh.png"));
        textureSkalaVerh.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteSkalaVerh = new Sprite(textureSkalaVerh);
        //Loading
        textureLoad1 = new Texture(Gdx.files.internal("Loading/Load0.png"));
        textureLoad1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteLoad1 = new Sprite(textureLoad1);
        textureLoad2 = new Texture(Gdx.files.internal("Loading/Load1.png"));
        textureLoad2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteLoad2 = new Sprite(textureLoad2);
        textureLoad3 = new Texture(Gdx.files.internal("Loading/Load2.png"));
        textureLoad3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteLoad3 = new Sprite(textureLoad3);
        textureLoad4 = new Texture(Gdx.files.internal("Loading/Load3.png"));
        textureLoad4.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteLoad4 = new Sprite(textureLoad4);
        loadAnimation = new Texture(Gdx.files.internal("LoadScreen/loadAnimation.png"));
        loadAnimation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        textureLoadBack = new Texture(Gdx.files.internal("LoadScreen/loadBack.png"));
        textureLoadBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        textureEmpty = new Texture(Gdx.files.internal("Animations/Empty.png"));
        textureEmpty.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteEmpty = new Sprite(textureEmpty);
    }
}