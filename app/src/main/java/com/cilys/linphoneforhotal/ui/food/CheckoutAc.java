package com.cilys.linphoneforhotal.ui.food;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class CheckoutAc extends CommonTitleAc {
    @Override
    protected int getLayout() {
        return R.layout.ac_amenities_checkout;
    }

    @Override
    protected void initUI() {
        super.initUI();

        MyListView lv = findView(R.id.lv_goods);
        List<DataBean> datas = new ArrayList<>();
        DataBean b2 = new DataBean(null, "Arancini(3 picces)",
                "$4.49", 2);
        b2.setPicId(R.mipmap.ic_food_details_test);
        datas.add(b2);

        DataBean b3 = new DataBean(null, "Pizza Margherta",
                "$12.99", 1);
        b3.setPicId(R.mipmap.ic_food_details_test);
        datas.add(b3);

        CheckoutDataAdapter adapter = new CheckoutDataAdapter(datas);
        lv.setAdapter(adapter);
    }

    @Override
    protected String getCommonTitle() {
        return "Checkout";
    }


}
