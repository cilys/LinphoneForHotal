package com.cilys.linphoneforhotal.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.BuildConfig;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.event.EventBus;
import com.cilys.linphoneforhotal.event.EventImpl;
import com.cilys.linphoneforhotal.utils.L;
import com.cilys.linphoneforhotal.utils.ToastUtils;

public class BaseAc extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    protected <V extends View> V findView(@IdRes int id){
        try{
            return (V)findViewById(id);
        }catch (ClassCastException e){
            L.e(TAG, "Could not cast View to concrete class");
            return null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (eventImpl == null) {
            eventImpl = new EventImpl() {
                @Override
                public void onTrigger(Event event) {
                    if (event.what == EventImpl.CLOSE_APP) {
                        finish();
                    } else {
                        onEvent(event);
                    }
                }
            };
        }
        EventBus.getInstance().onSub(eventImpl);
    }

    protected void onEvent(Event e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventImpl != null) {
            EventBus.getInstance().unSub(eventImpl);
        }
    }

    private EventImpl eventImpl;

    protected void showToast(String str){
        ToastUtils.show(this, str);
    }

    protected void setTextToView(TextView v, String text) {
        if (v == null) {
            return;
        }
        if (text == null) {
            text = "";
        }
        v.setText(text);
    }

    protected void debugToast(String str) {
        if (BuildConfig.DEBUG) {
            showToast(str);
        }
    }
}
