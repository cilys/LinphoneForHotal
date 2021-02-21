package com.cilys.linphoneforhotal.ui.food;

import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseAc;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public class FoodAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_food;
    }

    @Override
    protected void initUI() {
        super.initUI();

        findView(R.id.top_close).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected String getCommonTitle() {
        return getString(R.string.food_order);
    }
}
