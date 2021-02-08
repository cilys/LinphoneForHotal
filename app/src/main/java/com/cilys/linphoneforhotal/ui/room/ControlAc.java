package com.cilys.linphoneforhotal.ui.room;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;

public class ControlAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_control;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.test, R.mipmap.ic_room_control_test);
    }

    @Override
    protected String getCommonTitle() {
        return "Room Control";
    }
}
