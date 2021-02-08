package com.cilys.linphoneforhotal.ui.amen;

import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseAc;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public class AmentiesAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_amenties;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.test, R.mipmap.ic_amenities_test);

        findView(R.id.top_close).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
            }
        });
    }
}
