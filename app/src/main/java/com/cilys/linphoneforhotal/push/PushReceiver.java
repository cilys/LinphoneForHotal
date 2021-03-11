package com.cilys.linphoneforhotal.push;

import android.content.Context;


import com.cilys.linphoneforhotal.utils.L;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by admin on 2019/9/20.
 */

public class PushReceiver extends JPushMessageReceiver {

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        L.i(getClass().getSimpleName(), "customMessage = " + customMessage.toString());
    }
}
