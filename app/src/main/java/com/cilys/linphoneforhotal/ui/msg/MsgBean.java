package com.cilys.linphoneforhotal.ui.msg;

import java.io.Serializable;

public class MsgBean implements Serializable, Cloneable {
    private String title;
    private String name;
    private String info;
    private String time;
    private boolean newMsg;

    public MsgBean() {
    }

    public MsgBean(String title) {
        this.title = title;
    }

    public MsgBean(String name, String info, String time, boolean newMsg) {
        this.name = name;
        this.info = info;
        this.time = time;
        this.newMsg = newMsg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isNewMsg() {
        return newMsg;
    }

    public void setNewMsg(boolean newMsg) {
        this.newMsg = newMsg;
    }

    @Override
    protected MsgBean clone() throws CloneNotSupportedException {
        return (MsgBean)super.clone();
    }
}