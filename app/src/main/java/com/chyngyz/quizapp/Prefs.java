package com.chyngyz.quizapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        this.sharedPreferences = context.getSharedPreferences("ThemeSPrefs", Context.MODE_PRIVATE);
    }

    public void setTheme(int value){
        sharedPreferences
                .edit()
                .putInt("theme", value)
                .apply();
    }

    public int getTheme (){
        return sharedPreferences.getInt("theme", 0);
    }

    public void setThemePos(int pos) {
        sharedPreferences
                .edit()
                .putInt("position", pos)
                .apply();
    }

    public int getThemePos(){
        return sharedPreferences.getInt("position", 0);
    }
}
