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
        View top_close = findView(R.id.top_close);
        if (top_close != null) {
            top_close.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    finish();
                }
            });
        }

        View top_home = findView(R.id.top_home);
        if (top_home != null) {
            top_home.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {

                }
            });
        }
    }

    protected String getCommonTitle(){
        return "";
    }
}
