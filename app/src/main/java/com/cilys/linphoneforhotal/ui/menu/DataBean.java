package com.cilys.linphoneforhotal.ui.menu;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

public class DataBean implements Serializable {
    private String picUrl;
    private String name;
    private float price;
    private int count;
    private String title;
    private @DrawableRes
    int picId;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataBean() {
    }

    public DataBean(String title) {
        this.title = title;
    }

    public DataBean(String id, String picUrl, String name, float price, int count) {
        this.id = id;
        this.picUrl = picUrl;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicId() {
        return picId;
    }

    public DataBean setPicId(int picId) {
        this.picId = picId;
        return this;
    }
}