package com.cilys.linphoneforhotal.ui.checkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseAc;
import com.cilys.linphoneforhotal.base.CommonTitleAc;

public class CheckoutAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_checkout;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.test, R.mipmap.ic_checkout_test);
    }
}