package com.cilys.linphoneforhotal.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.cilys.linphoneforhotal.App;
import com.cilys.linphoneforhotal.BuildConfig;
import com.cilys.linphoneforhotal.call.PhoneAc;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.event.EventImpl;
import com.cilys.linphoneforhotal.event.LinPhoneBean;
import com.cilys.linphoneforhotal.service.LinphoneService;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.utils.L;

import org.linphone.core.Call;
import org.linphone.core.Core;
import org.linphone.core.ProxyConfig;

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



        if (BuildConfig.DEBUG || true) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    protected void afterInit(){

    }

    protected int getScreenModel(){
        Configuration cf= this.getResources().getConfiguration(); //获取设置的配置信息
        if (cf != null) {
            //获取屏幕方向
            return cf.orientation;
        } else {
            return Configuration.ORIENTATION_PORTRAIT;
        }
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

        App.getInstance().setTypeLastActivity(App.TYPE_LAST_AC_DEFAULT);
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
    protected <V extends View> V getViewFromCache(@IdRes int id){
        if (viewCache != null) {
            try{
                V v = (V)viewCache.get(id);
                if (v != null) {
                    return v;
                }
            }catch (Exception e) {
                L.printException(e);
            }
        }
        return findView(id);
    }
    protected void setBackgroundById(@IdRes int viewId, @DrawableRes int resourceId) {
        if (viewCache == null) {
            viewCache = new HashMap<>();
        }

        View v = viewCache.get(viewId);
        if (v == null) {
            v = findView(viewId);

            if (v != null) {
                viewCache.put(viewId, v);
            }
        }

        if (v != null) {
            ImageUtils.load(this, resourceId, v);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
    }

    private boolean isResume = false;

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }

    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);
        if (e.what == EventImpl.CALL_STATE_CHANGED) {
            if (this instanceof PhoneAc) {

            } else {
                if (e.obj instanceof LinPhoneBean) {
                    if (((LinPhoneBean)e.obj).getCallState() == Call.State.IncomingReceived) {
                        Intent i = new Intent(this, PhoneAc.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("SHOW_TYPE", PhoneAc.SHOW_TYPE_INCOMING);
                        startActivity(i);
                    }
                }
            }
        }
    }

    //    @Override
//    protected void onEvent(final Event e) {
//        super.onEvent(e);
//
//        if (e.what == EventImpl.CALL_STATE_CHANGED) {
//            if (e.obj instanceof LinPhoneBean) {
//                LinPhoneBean bean = (LinPhoneBean) e.obj;
//                if (bean.getCallState() == Call.State.IncomingReceived) {
//
//                    if (App.getInstance().getTypeLastActivity() == App.TYPE_LAST_AC_OUT) {
//                        return;
//                    }
//
//                    if (this instanceof IncomingAc) {
//
//                    } else {
//                        Intent i = new Intent(this, IncomingAc.class);
//                        startActivity(i);
//                    }
//                } else if (bean.getCallState() == Call.State.Connected) {
//                    if (App.getInstance().getTypeLastActivity() == App.TYPE_LAST_AC_INCOMING
//                        || App.getInstance().getTypeLastActivity() == App.TYPE_LAST_AC_OUT) {
//
//                        Intent i = new Intent(this, CallAc.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(i);
//                    } else {
//                        endCall();
//                    }
//
//                } else if (bean.getCallState() == Call.State.End || bean.getCallState() == Call.State.Released) {
//                    if (this instanceof OutAc || this instanceof IncomingAc || this instanceof CallAc) {
//                        showToast("呼叫已结束");
//                        finish();
//                    }
//                }
//            }
//        } else if (e.what == EventImpl.REGISTION_STATE_CHANGED) {
//
//        }
//    }
}