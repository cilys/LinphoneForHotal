package com.cilys.linphoneforhotal;

import android.app.Application;

import java.util.Locale;

import cn.jpush.android.api.JPushInterface;

public class App extends Application {
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static App getInstance() {
        return instance;
    }

    private Locale localLanguage;

    public static void setInstance(App instance) {
        App.instance = instance;
    }

    public Locale getLocalLanguage() {
        return localLanguage;
    }

    public void setLocalLanguage(Locale localLanguage) {
        this.localLanguage = localLanguage;
    }
}
