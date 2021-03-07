package com.cilys.linphoneforhotal.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.event.EventBus;
import com.cilys.linphoneforhotal.event.EventImpl;
import com.cilys.linphoneforhotal.utils.MoneyUtils;
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
                    EventBus.getInstance().postEvent(EventImpl.TO_HOME);
                }
            });
        }

        View top_search = findView(R.id.top_search);
        if (top_search != null) {
            top_search.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {

                }
            });
        }
    }

    protected String getCommonTitle(){
        return "";
    }

    protected void setDrawableBottom(TextView rbt, @DrawableRes int resId) {
        Drawable draw = getResources().getDrawable(resId);
        draw.setBounds(0, 0,
                getResources().getDimensionPixelSize(R.dimen.x10),
                getResources().getDimensionPixelSize(R.dimen.x10));
        rbt.setCompoundDrawables(null, null, null, draw);
    }

    protected String fomcatMoney(float money) {
        return getString(R.string.money_unit) + MoneyUtils.fomcatMoney(money);
    }
}