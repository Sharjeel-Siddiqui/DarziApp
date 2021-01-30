package com.example.dulha_jee;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private Context myctx;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public SharedPreference(Context myctx) {
        this.myctx = myctx;
        this.sharedPref = myctx.getSharedPreferences("PRIVATE", Context.MODE_PRIVATE);
        this.editor = sharedPref.edit();
    }



    public void setCollarImageNumber(int numberImage) {
        editor.putInt("ImageNumber", numberImage);
        editor.apply();
    }

    public int getCollarImageNumber() {
        return sharedPref.getInt("ImageNumber", 0);
    }


    public void setSidePocketImageNumber(int numberImage) {
        editor.putInt("ImageNumberSidePocket", numberImage);
        editor.apply();
    }

    public int getSidePocketCollarImageNumber() {
        return sharedPref.getInt("ImageNumberSidePocket", 0);
    }

    public void setCuffImageNumber(int numberImage) {
        editor.putInt("ImageNumberCuff", numberImage);
        editor.apply();
    }

    public int getCuffImageNumber() {
        return sharedPref.getInt("ImageNumberCuff", 0);
    }


    public void saveToken(String str) {
        editor.putString("S", str);
        editor.apply();
        editor.commit();
    }

    public String getToken() {
        return sharedPref.getString("S", "0");
    }

    public void remove() {
        editor.remove("ImageNumber");
        editor.remove("ImageNumberSidePocket");
        editor.remove("ImageNumberCuff");
        editor.apply();
        editor.commit();
    }

}
