package com.cilys.linphoneforhotal.ui.menu;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;

public class ServiceMenuDetails extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_service_menu_details;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.test, R.mipmap.ic_service_menu_detatils_test);
    }

    @Override
    protected String getCommonTitle() {
        return "Service Menu";
    }
}