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

}
