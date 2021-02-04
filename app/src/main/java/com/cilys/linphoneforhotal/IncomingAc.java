package com.cilys.linphoneforhotal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.base.BaseLinphoneAc;
import com.cilys.linphoneforhotal.event.Event;

import com.cilys.linphoneforhotal.utils.LinphoneUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import org.linphone.core.Call;
import org.linphone.core.CallParams;
import org.linphone.core.Core;

public class IncomingAc extends BaseLinphoneAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_incoming;
    }

    @Override
    protected void initUI(){
        super.initUI();

        setBackgroundById(R.id.root, R.mipmap.ic_call_bg);
        setBackgroundById(R.id.ll_model, R.mipmap.ic_incoming_model_bg);


        final Call call = getCurrentCall();

        TextView tv_room = findView(R.id.tv_room);
        if (call != null) {
            setTextToView(tv_room, LinphoneUtils.getAddressDisplayName(
                    call.getRemoteAddress()));
        }

        TextView tv_custom_name = findView(R.id.tv_custom_name);


        TextView tv_custom_status = findView(R.id.tv_custom_status);

        ImageView img_decline = findView(R.id.img_decline);
        img_decline.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                terminateCurrentCallOrConferenceOrAll();
            }
        });

        ImageView img_call_accept = findView(R.id.img_call_accept);
        img_call_accept.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (getLinphoneCore() != null && call != null) {
                    CallParams params = getLinphoneCore().createCallParams(call);
                    params.enableVideo(false);

                    App.getInstance().setTypeLastActivity(App.TYPE_LAST_AC_INCOMING);

                    call.acceptWithParams(params);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        endCall();
    }

    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);
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
        App.getInstance().setTypeLastActivity(App.TYPE_LAST_AC_DEFAULT);

        finish();
    }
}