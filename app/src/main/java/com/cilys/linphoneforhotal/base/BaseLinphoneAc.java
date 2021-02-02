package com.cilys.linphoneforhotal.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;

import com.bumptech.glide.Glide;
import com.cilys.linphoneforhotal.CallAc;
import com.cilys.linphoneforhotal.IncomingAc;
import com.cilys.linphoneforhotal.OutAc;
import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.event.EventBus;
import com.cilys.linphoneforhotal.event.EventImpl;
import com.cilys.linphoneforhotal.event.LinPhoneBean;
import com.cilys.linphoneforhotal.service.LinphoneService;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.utils.L;

import org.linphone.core.Call;
import org.linphone.core.Core;
import org.linphone.core.CoreListenerStub;
import org.linphone.core.ProxyConfig;
import org.linphone.core.RegistrationState;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseLinphoneAc extends BaseAc {
    public final String INTENT_CALL_NUMBER = "INTENT_CALL_NUMBER";

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initUI();
        init();
        afterInit();
    }

    protected void afterInit(){

    }

    protected abstract @LayoutRes int getLayout();
    protected void initUI(){

    }
    protected void init() {

    }

    protected Call getCurrentCall(){
        Core core = LinphoneService.getCore();
        if (core == null) {
            return null;
        }

        if (core.getCallsNb() > 0) {
            Call call = core.getCurrentCall();
            if (call == null) {
                call = core.getCalls()[0];
            }
            if (call != null) {
                return call;
            }
        }
        return null;
    }

    protected void endCall(){
        Call call = getCurrentCall();
        if (call != null) {
            call.terminate();
        }
    }

    protected Core getLinphoneCore(){
        if (LinphoneService.getInstance() != null) {
            return LinphoneService.getCore();
        } else {
            return null;
        }
    }

    protected ProxyConfig getLinphoneConfig (){
        if (getLinphoneCore() != null) {
            ProxyConfig cfg = getLinphoneCore().getDefaultProxyConfig();
            if (cfg != null) {
//                cfg.setRoute("core1-hk.netustay.com");
            }
            return cfg;
        }
        return null;
    }

    private Map<Integer, View> viewCache;

    protected void setBackgroundById(@IdRes int viewId, @DrawableRes int resourceId) {
        if (viewCache == null) {
            viewCache = new HashMap<>();
        }

        View v = viewCache.get(viewId);
        if (v == null) {
            v = findView(viewId);
        }

        if (v != null) {
            viewCache.put(viewId, v);

            ImageUtils.load(this, resourceId, v);
        }
    }

    @Override
    protected void onEvent(final Event e) {
        super.onEvent(e);
        if (e.what == EventImpl.CALL_STATE_CHANGED) {
            if (e.obj instanceof LinPhoneBean) {
                LinPhoneBean bean = (LinPhoneBean) e.obj;
                if (bean.getCallState() == Call.State.IncomingReceived) {
                    L.i(TAG, "getCurrentCall 0 = " + bean.getCall());

                    if (this instanceof IncomingAc) {

                    } else {
                        Intent i = new Intent(this, IncomingAc.class);
                        startActivity(i);
                    }
                } else if (bean.getCallState() == Call.State.Connected) {
                    Intent i = new Intent(this, CallAc.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
//                    if (this instanceof IncomingAc || this instanceof OutAc) {
//                        finish();
//                    }
                } else if (bean.getCallState() == Call.State.End || bean.getCallState() == Call.State.Released) {
                    if (this instanceof OutAc || this instanceof IncomingAc || this instanceof CallAc) {
                        showToast("呼叫已结束");
                        finish();
                    }
                }
            }
        } else if (e.what == EventImpl.REGISTION_STATE_CHANGED) {

        }
    }
}