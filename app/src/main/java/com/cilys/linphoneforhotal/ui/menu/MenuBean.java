package com.cilys.linphoneforhotal.ui.menu;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

public class MenuBean implements Serializable {


    private @DrawableRes int resourceId;
    private String name;

    public MenuBean() {
    }

    public MenuBean(int resourceId, String name) {
        this.resourceId = resourceId;
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
