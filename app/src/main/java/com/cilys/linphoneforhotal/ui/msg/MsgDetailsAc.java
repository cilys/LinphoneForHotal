package com.cilys.linphoneforhotal.ui.msg;

import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public class MsgDetailsAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_msg_details;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.pic, R.mipmap.ic_food_details_test);


        findView(R.id.top_back).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
            }
        });
    }
}