package com.cilys.linphoneforhotal.ui.home;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;

public class PromDetailsAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_prom_details;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.test, R.mipmap.ic_promotion_details_test);
    }

    @Override
    protected String getCommonTitle() {
        return "Promotion details";
    }
}
