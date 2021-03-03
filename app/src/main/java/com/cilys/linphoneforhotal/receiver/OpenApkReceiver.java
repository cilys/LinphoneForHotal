package com.cilys.linphoneforhotal.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cilys.linphoneforhotal.ui.home.HomeAc;
import com.cilys.linphoneforhotal.utils.ToastUtils;

public class OpenApkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            ToastUtils.show(context, "更新apk成功...");
            Intent localIntent = new Intent(context, HomeAc.class);
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(localIntent);
        }
    }
}
