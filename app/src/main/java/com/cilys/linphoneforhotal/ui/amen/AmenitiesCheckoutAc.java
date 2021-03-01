package com.cilys.linphoneforhotal.ui.amen;

import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.MyListView;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class AmenitiesCheckoutAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_amenities_checkout;
    }

    @Override
    protected void initUI() {
        super.initUI();


        MyListView lv = findView(R.id.lv_goods);
        List<DataBean> datas = new ArrayList<>();
        DataBean b2 = new DataBean(null, "Jacket",
                "$95.00", 2);
        b2.setPicId(R.mipmap.icon_necktie);
        datas.add(b2);

        DataBean b3 = new DataBean(null, "Necktie",
                "$45.00", 1);
        b3.setPicId(R.mipmap.icon_necktie_2);
        datas.add(b3);

        CheckoutDataAdapter adapter = new CheckoutDataAdapter(datas);
        lv.setAdapter(adapter);
    }

    @Override
    protected String getCommonTitle() {
        return getString(R.string.Checkout);
    }
}