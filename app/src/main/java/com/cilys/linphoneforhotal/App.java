package com.cilys.linphoneforhotal;

import android.app.Activity;
import android.app.Application;

import com.cilys.linphoneforhotal.base.BaseAc;

public class App extends Application {
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    private int typeLastActivity;
    public final static int TYPE_LAST_AC_DEFAULT = 0;
    public final static int TYPE_LAST_AC_INCOMING = 1;
    public final static int TYPE_LAST_AC_OUT = 2;
    public final static int TYPE_LAST_AC_CALL = 3;

    public void setTypeLastActivity(int typeLastActivity) {
        this.typeLastActivity = typeLastActivity;
    }

    public int getTypeLastActivity() {
        return typeLastActivity;
    }
}
