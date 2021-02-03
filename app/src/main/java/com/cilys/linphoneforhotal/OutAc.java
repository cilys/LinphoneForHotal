package com.cilys.linphoneforhotal;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cilys.linphoneforhotal.base.BaseLinphoneAc;
import com.cilys.linphoneforhotal.utils.Sp;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import org.linphone.core.Address;
import org.linphone.core.CallParams;
import org.linphone.core.Core;
import org.linphone.core.ProxyConfig;

/**
 * 呼叫界面
 */
public class OutAc extends BaseLinphoneAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_out;
    }

    @Override
    protected void initUI(){
        super.initUI();

        setBackgroundById(R.id.root, R.mipmap.ic_call_bg);
        setBackgroundById(R.id.ll_model, R.mipmap.ic_incoming_model_bg);

        TextView tv_room = findView(R.id.tv_room);

        ImageView img_avatar = findView(R.id.img_avatar);

        TextView tv_custom_name = findView(R.id.tv_custom_name);

        TextView tv_custom_status = findView(R.id.tv_custom_status);

        LinearLayout ll_mute = findView(R.id.ll_mute);
        ll_mute.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        LinearLayout ll_speaker = findView(R.id.ll_speaker);
        ll_speaker.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        ImageView img_end_call = findView(R.id.img_end_call);
        img_end_call.setOnClickListener(new SingleClickListener() {
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
        setTextToView(tv_room, phone);

        call(phone);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        endCall();
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