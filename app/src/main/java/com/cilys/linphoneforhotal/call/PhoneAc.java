package com.cilys.linphoneforhotal.call;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cilys.linphoneforhotal.App;
import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseLinphoneAc;
import com.cilys.linphoneforhotal.utils.LinphoneUtils;
import com.cilys.linphoneforhotal.utils.Sp;
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

    @Override
    protected int getLayout() {
        return R.layout.ac_phone;
    }

    @Override
    protected void initUI() {
        super.initUI();

        initOutView();
        initIncomingView();
        initCallView();

        int showType = getIntent().getIntExtra("SHOW_TYPE", SHOW_TYPE_OUT);
        showView(showType);
    }

    private void showView(int type) {
        if (type == SHOW_TYPE_OUT) {
            getViewFromCache(R.id.root_out).setVisibility(View.VISIBLE);
            getViewFromCache(R.id.root_incoming).setVisibility(View.GONE);
            getViewFromCache(R.id.root_call).setVisibility(View.GONE);
        } else if (type == SHOW_TYPE_INCOMING) {
            getViewFromCache(R.id.root_out).setVisibility(View.GONE);
            getViewFromCache(R.id.root_incoming).setVisibility(View.VISIBLE);
            getViewFromCache(R.id.root_call).setVisibility(View.GONE);
        } else if (type == SHOW_TYPE_CALL) {
            getViewFromCache(R.id.root_out).setVisibility(View.GONE);
            getViewFromCache(R.id.root_incoming).setVisibility(View.GONE);
            getViewFromCache(R.id.root_call).setVisibility(View.VISIBLE);
        }
    }

    private TextView tv_call_time;
    private void initCallView(){
        setBackgroundById(R.id.root_call, R.mipmap.ic_call_bg);
        setBackgroundById(R.id.ll_call_model, R.mipmap.ic_incoming_model_bg);

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
        setBackgroundById(R.id.root_incoming, R.mipmap.ic_incoming_bg);
        setBackgroundById(R.id.ll_incoming_model, R.mipmap.ic_incoming_model_bg);


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

    private void initOutView() {
        setBackgroundById(R.id.root_out, R.mipmap.ic_call_bg);
        setBackgroundById(R.id.ll_out_model, R.mipmap.ic_incoming_model_bg);

        TextView tv_out_room = findView(R.id.tv_out_room);

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

        String phone = getIntent().getStringExtra(INTENT_CALL_NUMBER);
        if (phone == null || phone.length() < 1) {
            finish();
        }
        setTextToView(tv_out_room, phone);

        call(phone);
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
                App.getInstance().setTypeLastActivity(App.TYPE_LAST_AC_OUT);

                addressToCall.setDisplayName(phone);

                core.inviteAddressWithParams(addressToCall, params);
//                finish();
            }
        }
    }
}