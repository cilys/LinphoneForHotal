package com.cilys.linphoneforhotal.ui.tv;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

public class ChannelBean implements Serializable {
    private @DrawableRes int resourceId;
    private String channel;
    private boolean selected;

    public ChannelBean() {
    }

    public ChannelBean(int resourceId) {
        this.resourceId = resourceId;
    }

    public ChannelBean(int resourceId, String channel) {
        this.resourceId = resourceId;
        this.channel = channel;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}