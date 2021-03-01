package com.cilys.linphoneforhotal.ui.room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.DashView;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public class ControlAc extends CommonTitleAc {
    private boolean curtainsStatus = false,
            masterLightStatus = false,
            bathroomLightStatus = false,
            lightsStatus = false;

    @Override
    protected int getLayout() {
        return R.layout.ac_control;
    }

    private TextView curtains_open, curtains_close;
    private TextView master_light_open, master_light_close;
    private TextView bathroom_light_open, bathroom_light_close;
    private TextView lights_open, lights_close;

    @Override
    protected void initUI() {
        super.initUI();

        DashView temp = findView(R.id.temp);
        temp.setLowScale(0);
        temp.setHighScale(30);
        temp.setCurrentScale(21);
        temp.setCenterText("21℃");
        temp.invalidate();

        DashView fan_speed = findView(R.id.fan_speed);
        fan_speed.setLowScale(0);
        fan_speed.setHighScale(100);
        fan_speed.setCurrentScale(22);
        fan_speed.setCenterText("22%");
        fan_speed.invalidate();

        View curtains = findView(R.id.curtains);
        curtains_open = findView(R.id.curtains_open);
        curtains_close = findView(R.id.curtains_close);
        curtains.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                curtainsStatus = !curtainsStatus;
                setTextViewSelected(curtains_open, curtainsStatus);
                setTextViewSelected(curtains_close, !curtainsStatus);
            }
        });

        View master_light = findView(R.id.master_light);
        master_light_open = findView(R.id.master_light_open);
        master_light_close = findView(R.id.master_light_close);
        master_light.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                masterLightStatus = !masterLightStatus;

                setTextViewSelected(master_light_open, masterLightStatus);
                setTextViewSelected(master_light_close, !masterLightStatus);

            }
        });

        View bathroom_light = findView(R.id.bathroom_light);
        bathroom_light_open = findView(R.id.bathroom_light_open);
        bathroom_light_close = findView(R.id.bathroom_light_close);
        bathroom_light.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                bathroomLightStatus = !bathroomLightStatus;

                setTextViewSelected(bathroom_light_open, bathroomLightStatus);
                setTextViewSelected(bathroom_light_close, !bathroomLightStatus);

            }
        });

        View lights = findView(R.id.lights);
        lights_open = findView(R.id.lights_open);
        lights_close = findView(R.id.lights_close);
        lights.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                lightsStatus = !lightsStatus;
                setTextViewSelected(lights_open, lightsStatus);
                setTextViewSelected(lights_close, !lightsStatus);
            }
        });
    }

    private void setTextViewSelected(TextView tv, boolean selected){
        if (tv == null) {
            return;
        }
        if (selected) {
            tv.setBackgroundResource(R.drawable.shape_round_bg_room_control_action_close);
            tv.setTextColor(getResources().getColor(R.color.white));
        } else {
            tv.setBackgroundResource(R.color.white);
            tv.setTextColor(getResources().getColor(R.color.color_main_text_color));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState == null) {
            return;
        }
        outState.putBoolean("curtainsStatus", curtainsStatus);
        outState.putBoolean("masterLightStatus", masterLightStatus);
        outState.putBoolean("bathroomLightStatus", bathroomLightStatus);
        outState.putBoolean("lightsStatus", lightsStatus);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }

        curtainsStatus = savedInstanceState.getBoolean("curtainsStatus");
        masterLightStatus = savedInstanceState.getBoolean("masterLightStatus");
        bathroomLightStatus = savedInstanceState.getBoolean("bathroomLightStatus");
        lightsStatus = savedInstanceState.getBoolean("lightsStatus");

        setTextViewSelected(curtains_open, curtainsStatus);
        setTextViewSelected(curtains_close, !curtainsStatus);

        setTextViewSelected(master_light_open, masterLightStatus);
        setTextViewSelected(master_light_close, !masterLightStatus);

        setTextViewSelected(bathroom_light_open, bathroomLightStatus);
        setTextViewSelected(bathroom_light_close, !bathroomLightStatus);

        setTextViewSelected(lights_open, lightsStatus);
        setTextViewSelected(lights_close, !lightsStatus);
    }

    @Override
    protected String getCommonTitle() {
        return "Room Control";
    }
}
