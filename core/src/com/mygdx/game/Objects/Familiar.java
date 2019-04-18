package com.mygdx.game.Objects;

public class Familiar {
    public static float timer=0;
    public static float KD=3;
    static boolean active=true;

    public Familiar(){


    }
    public static boolean isActive(){
        if(active){
            return true;
        }
        return false;
    }
    public static void setActive(boolean a){
        if(a){
            active=true;
        }
        if (!a){
            active=false;
        }
    }
    static public void reload(float dt){
        if (!active) {
            timer+=dt;
            if(timer>=KD){
                active=true;
                timer=0;
            }
        }
        else {
            timer=0;
        }

    }
}
