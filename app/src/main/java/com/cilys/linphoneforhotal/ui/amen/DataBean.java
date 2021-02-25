package com.cilys.linphoneforhotal.ui.amen;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

public class DataBean implements Serializable, Cloneable {
    private String picUrl;
    private String name;
    private String price;
    private int count;
    private String title;
    private @DrawableRes int picId;

    public DataBean() {
    }

    public DataBean(String title) {
        this.title = title;
    }

    public DataBean(String picUrl, String name, String price, int count) {
        this.picUrl = picUrl;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected DataBean clone() throws CloneNotSupportedException {
        return (DataBean) super.clone();
    }
}
