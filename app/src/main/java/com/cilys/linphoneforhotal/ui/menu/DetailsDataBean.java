package com.cilys.linphoneforhotal.ui.menu;

import java.io.Serializable;

public class DetailsDataBean implements Serializable {
    private String name;
    private String price;
    private int count;

    private int picId;

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
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
}