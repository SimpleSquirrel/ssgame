package com.mygdx.game.Objects;

public class Familiar {
    public static float timer1=0;
    public static float timer2=0;
    public static float KD=3;
    static boolean active1=true;
    static boolean active2=true;

    public Familiar(){


    }
    public static boolean isActive(int a){
        if(a==1) {
            if (active1) {
                return true;
            }
            return false;
        }
        else if(a==2){
            if (active2) {
                return true;
            }
            return false;
        }
        return false;
    }
    public static void setActive(int b,boolean a){
        if(b==1) {
            if (a) {
                active1 = true;
            }
            if (!a) {
                active1 = false;
            }
        }
        if(b==2) {
            if (a) {
                active2 = true;
            }
            if (!a) {
                active2 = false;
            }
        }
    }
    static public void reload(int a,float dt){
        if(a==1) {
            if (!active1) {
                timer1 += dt;
                if (timer1 >= KD) {
                    active1 = true;
                    timer1 = 0;
                }
            } else {
                timer1 = 0;
            }
        }
        if(a==2) {
            if (!active2) {
                timer2 += dt;
                if (timer2 >= KD) {
                    active2 = true;
                    timer2 = 0;
                }
            } else {
                timer2 = 0;
            }
        }
    }
}
