package com.cilys.linphoneforhotal.base;

import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public abstract class CommonTitleAc extends BaseLinphoneAc {

    @Override
    protected void initUI() {
        super.initUI();

        setTextToView((TextView) getViewFromCache(R.id.tv_title), getCommonTitle());
        findView(R.id.top_close).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
            }
        });

        findView(R.id.top_home).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });
    }

    protected String getCommonTitle(){
        return "";
    }
}
