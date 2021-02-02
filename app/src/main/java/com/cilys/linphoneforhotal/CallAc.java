package com.cilys.linphoneforhotal;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cilys.linphoneforhotal.base.BaseLinphoneAc;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.event.EventImpl;
import com.cilys.linphoneforhotal.event.LinPhoneBean;
import com.cilys.linphoneforhotal.utils.LinphoneUtils;
import com.cilys.linphoneforhotal.utils.TimeUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import org.linphone.core.Call;

/**
 * 通话页面
 */
public class CallAc extends BaseLinphoneAc {
    private TextView tv_time;

    @Override
    protected int getLayout() {
        return R.layout.ac_call;
    }

    @Override
    protected void initUI(){
        super.initUI();

        setBackgroundById(R.id.root, R.mipmap.ic_call_bg);
        setBackgroundById(R.id.ll_model, R.mipmap.ic_incoming_model_bg);

        TextView tv_room = findView(R.id.tv_room);

        ImageView img_avatar = findView(R.id.img_avatar);

        TextView tv_custom_name = findView(R.id.tv_custom_name);
        setTextToView(tv_custom_name, LinphoneUtils.getDisplayName(getCurrentCall()));

        TextView tv_custom_status = findView(R.id.tv_custom_status);

        tv_time = findView(R.id.tv_time);

        LinearLayout ll_mute = findView(R.id.ll_mute);
        ll_mute.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        LinearLayout ll_hold = findView(R.id.ll_hold);
        ll_hold.setOnClickListener(new SingleClickListener() {
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
            }
        });
    }

    @Override
    protected void afterInit() {
        super.afterInit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        endCall();
    }

    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);
        if (e.what == EventImpl.CALL_STATE_CHANGED) {
            if (e.obj instanceof LinPhoneBean) {
                if (Call.State.Connected == ((LinPhoneBean) e.obj).getCallState()) {
                    timeCount = 0;
                }
            }
        } else if (e.what == EventImpl.SYSTEM_TIMER) {
            timeCount ++;

            setTextToView(tv_time, TimeUtils.fomcatTimeToSecond(timeCount));
        }
    }

    private long timeCount = -1;
}