package com.cilys.linphoneforhotal.call;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseLinphoneAc;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.event.EventImpl;
import com.cilys.linphoneforhotal.event.LinPhoneBean;
import com.cilys.linphoneforhotal.service.LinphoneService;
import com.cilys.linphoneforhotal.utils.LinphoneUtils;
import com.cilys.linphoneforhotal.utils.Sp;
import com.cilys.linphoneforhotal.utils.TimeUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import org.linphone.core.Address;
import org.linphone.core.Call;
import org.linphone.core.CallParams;
import org.linphone.core.Core;
import org.linphone.core.ProxyConfig;

public class PhoneAc extends BaseLinphoneAc {
    public final static int SHOW_TYPE_OUT = 1;      //显示外呼布局
    public final static int SHOW_TYPE_INCOMING = 2; //显示来电布局
    public final static int SHOW_TYPE_CALL = 3;     //显示通话布局

    private int showType = SHOW_TYPE_OUT;
    private String outNumber;

    @Override
    protected int getLayout() {
        return R.layout.ac_phone;
    }

    @Override
    protected void initUI() {
        super.initUI();

        showType = getIntent().getIntExtra("SHOW_TYPE", SHOW_TYPE_OUT);

        initOutView(showType == SHOW_TYPE_OUT);
        initIncomingView();
        initCallView();

        showView(showType);
    }

    private void showView(int type) {
        if (type == SHOW_TYPE_INCOMING) {
            getViewFromCache(R.id.ll_out_model).setVisibility(View.GONE);
            getViewFromCache(R.id.ll_incoming_model).setVisibility(View.VISIBLE);
            getViewFromCache(R.id.ll_call_model).setVisibility(View.GONE);
        } else if (type == SHOW_TYPE_CALL) {
            getViewFromCache(R.id.ll_out_model).setVisibility(View.GONE);
            getViewFromCache(R.id.ll_incoming_model).setVisibility(View.GONE);
            getViewFromCache(R.id.ll_call_model).setVisibility(View.VISIBLE);
        } else {
            getViewFromCache(R.id.ll_out_model).setVisibility(View.VISIBLE);
            getViewFromCache(R.id.ll_incoming_model).setVisibility(View.GONE);
            getViewFromCache(R.id.ll_call_model).setVisibility(View.GONE);
        }

        setBackgroundByScreen(getScreenModel());
    }

    private TextView tv_call_time;
    private void initCallView(){
        TextView tv_call_room = findView(R.id.tv_call_room);
        setTextToView(tv_call_room, LinphoneUtils.getDisplayName(getCurrentCall()));

        ImageView img_call_avatar = findView(R.id.img_call_avatar);

        TextView tv_call_custom_name = findView(R.id.tv_call_custom_name);

        TextView tv_call_custom_status = findView(R.id.tv_call_custom_status);

        tv_call_time = findView(R.id.tv_call_time);

        LinearLayout ll_call_mute = findView(R.id.ll_call_mute);
        ll_call_mute.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        LinearLayout ll_call_hold = findView(R.id.ll_call_hold);
        ll_call_hold.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        LinearLayout ll_call_speaker = findView(R.id.ll_call_speaker);
        ll_call_speaker.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        ImageView img_call_end_call = findView(R.id.img_call_end_call);
        img_call_end_call.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                endCall();
            }
        });
    }

    private void initIncomingView(){
        final Call call = getCurrentCall();

        TextView tv_incoming_room = findView(R.id.tv_incoming_room);
        if (call != null) {
            setTextToView(tv_incoming_room, LinphoneUtils.getAddressDisplayName(
                    call.getRemoteAddress()));
        }

        TextView tv_incoming_custom_name = findView(R.id.tv_incoming_custom_name);

        TextView tv_incoming_custom_status = findView(R.id.tv_incoming_custom_status);

        ImageView img_incoming_decline = findView(R.id.img_incoming_decline);
        img_incoming_decline.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                terminateCurrentCallOrConferenceOrAll();
            }
        });

        ImageView img_incoming_call_accept = findView(R.id.img_incoming_call_accept);
        img_incoming_call_accept.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (getLinphoneCore() != null && call != null) {
                    CallParams params = getLinphoneCore().createCallParams(call);
                    params.enableVideo(false);

                    call.acceptWithParams(params);
                }
            }
        });
    }

    public void terminateCurrentCallOrConferenceOrAll() {
        Core core = getLinphoneCore();
        if (core == null) {
            return;
        }
        Call call = core.getCurrentCall();
        if (call != null) {
            call.terminate();
        } else if (core.isInConference()) {
            core.terminateConference();
        } else {
            core.terminateAllCalls();
        }

        finish();
    }

    private TextView tv_out_room;
    private void initOutView(boolean show) {
        tv_out_room = findView(R.id.tv_out_room);

        ImageView img_out_avatar = findView(R.id.img_out_avatar);

        TextView tv_out_custom_name = findView(R.id.tv_out_custom_name);

        TextView tv_out_custom_status = findView(R.id.tv_out_custom_status);

        LinearLayout ll_out_mute = findView(R.id.ll_out_mute);
        ll_out_mute.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        LinearLayout ll_out_speaker = findView(R.id.ll_out_speaker);
        ll_out_speaker.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        ImageView img_out_end_call = findView(R.id.img_out_end_call);
        img_out_end_call.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                endCall();
                finish();
            }
        });

        if (show) {
            outNumber = getIntent().getStringExtra(INTENT_CALL_NUMBER);
            if (outNumber == null || outNumber.length() < 1) {
                finish();
            }
            setTextToView(tv_out_room, outNumber);

            call(outNumber);
        }
    }

    /**
     * 外呼
     * @param phone
     */
    private void call(String phone){
        Core core = getLinphoneCore();

        ProxyConfig cfg = getLinphoneConfig();

        if (cfg == null) {
            //提示未登陆
            showToast("未配置账户");
        } else {
            cfg.setRoute(Sp.getStr(this, "SP_SIP_PROXY_SERVER", "core1-hk.netustay.com"));
            Address addressToCall = core.interpretUrl(phone);
            CallParams params = core.createCallParams(null);

            params.enableVideo(false);

            if (addressToCall != null) {
                addressToCall.setDisplayName(phone);
                core.inviteAddressWithParams(addressToCall, params);
            }
        }
    }

    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);
        if (e.what == EventImpl.CALL_STATE_CHANGED) {
            if (e.obj instanceof LinPhoneBean) {
                LinPhoneBean bean = (LinPhoneBean) e.obj;
                if (bean.getCallState() == Call.State.IncomingReceived) {
                    if (showType == SHOW_TYPE_OUT || showType == SHOW_TYPE_CALL) {
                        //如果是呼叫、接听页面，则不处理被叫请求（原因：测试时发现主叫时，
                        // 不知道是什么原因，还会有收到IncomingReceived的状态，导致页面错乱显示成被叫页面。
                        // 为了避免页面错乱，故不处理正在主叫、以及正在通话时收到的被叫请求
                        return;
                    }

                    showType = SHOW_TYPE_INCOMING;
                    showView(SHOW_TYPE_INCOMING);
                } else if (bean.getCallState() == Call.State.Connected) {
                    if (LinphoneService.getInstance() != null) {
                        LinphoneService.getInstance().setNeedSystemTimer(true);
                    }
                    showType = SHOW_TYPE_CALL;
                    timeCount = 0;
                    showView(SHOW_TYPE_CALL);
                } else if (bean.getCallState() == Call.State.End || bean.getCallState() == Call.State.Released) {
                    if (LinphoneService.getInstance() != null) {
                        LinphoneService.getInstance().setNeedSystemTimer(false);
                    }

                    finish();
                }
            }
        } else if (e.what == EventImpl.SYSTEM_TIMER) {
            timeCount ++;

            setTextToView(tv_call_time, TimeUtils.fomcatTimeToSecond(timeCount));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private long timeCount = -1;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            if (outNumber != null) {
                outState.putString("OUT_NUMBER", outNumber);
            }
            if (timeCount > -1) {
                outState.putLong("CALL_TIME", timeCount);
            }

            debugToast("保存showType = " + showType);

            outState.putInt("SHOW_TYPE", showType);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String number = savedInstanceState.getString("OUT_NUMBER", null);
            if (number != null) {
                setTextToView(tv_out_room, number);
            }
            long time = savedInstanceState.getLong("CALL_TIME", -1L);
            if (time > -1) {
                setTextToView(tv_call_time, TimeUtils.fomcatTimeToSecond(time));
            }

            int show = savedInstanceState.getInt("SHOW_TYPE", SHOW_TYPE_OUT);

            debugToast("取出showType = " + show);

            showView(show);
        }
    }

    private void setBackgroundByScreen(int screen) {
        if (screen == Configuration.ORIENTATION_LANDSCAPE) {
            setBackgroundById(R.id.root, R.mipmap.ic_call_bg_land);

            if (showType == SHOW_TYPE_INCOMING) {
                setBackgroundById(R.id.ll_incoming_model, R.mipmap.ic_incoming_model_bg);
            } else if (showType == SHOW_TYPE_CALL) {
                setBackgroundById(R.id.ll_call_model, R.mipmap.ic_incoming_model_bg);
            } else {
                setBackgroundById(R.id.ll_out_model, R.mipmap.ic_incoming_model_bg);
            }
        } else {
            setBackgroundById(R.id.root, R.mipmap.ic_call_bg);

            if (showType == SHOW_TYPE_INCOMING) {
                setBackgroundById(R.id.ll_incoming_model, R.mipmap.ic_incoming_model_bg);
            } else if (showType == SHOW_TYPE_CALL) {
                setBackgroundById(R.id.ll_call_model, R.mipmap.ic_incoming_model_bg);
            } else {
                setBackgroundById(R.id.ll_out_model, R.mipmap.ic_incoming_model_bg);
            }
        }
    }
}