package com.cilys.linphoneforhotal.ui.msg;

import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public class MsgAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_msg;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.root, R.mipmap.ic_call_bg);
        setBackgroundById(R.id.ll_msg,  R.mipmap.ic_msg_modal_test);



    }
}