package com.cilys.linphoneforhotal.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.event.EventBus;
import com.cilys.linphoneforhotal.event.EventImpl;

public class BaseFg extends Fragment implements EventImpl {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getInstance().onSub(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unSub(this);
    }

    @Override
    public void onTrigger(Event event) {

    }

}
