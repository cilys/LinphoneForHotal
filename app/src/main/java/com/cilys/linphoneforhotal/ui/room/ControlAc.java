package com.cilys.linphoneforhotal.ui.room;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.DashView;

public class ControlAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_control;
    }

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
    }

    @Override
    protected String getCommonTitle() {
        return "Room Control";
    }
}
