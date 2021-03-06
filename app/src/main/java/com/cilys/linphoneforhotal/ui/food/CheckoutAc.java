package com.cilys.linphoneforhotal.ui.food;

import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class CheckoutAc extends CommonTitleAc {
    @Override
    protected int getLayout() {
        return R.layout.ac_food_checkout;
    }

    @Override
    protected void initUI() {
        super.initUI();

        MyListView lv = findView(R.id.lv_goods);
        List<DataBean> datas = new ArrayList<>();
        DataBean b2 = new DataBean("1", null, getString(R.string.Arancini),
                4.49f, 2);
        b2.setPicId(R.mipmap.ic_food_details_test);
        datas.add(b2);

        DataBean b3 = new DataBean("11", null, getString(R.string.Pizza_Margherta),
                12.99f, 1);
        b3.setPicId(R.mipmap.ic_food_details_test);
        datas.add(b3);

        CheckoutDataAdapter adapter = new CheckoutDataAdapter(datas);
        lv.setAdapter(adapter);

        ImageView recommend_extra = findView(R.id.recommend_extra);
        TextView recommend_extra_name = findView(R.id.recommend_extra_name);
        TextView recommend_extra_price = findView(R.id.recommend_extra_price);
//        recommend_extra_name.setText("");
//        recommend_extra_price.setText("");
        ImageUtils.load(this, R.mipmap.ic_food_checkout_recommend_test_1, recommend_extra);


        ImageView recommend_kit = findView(R.id.recommend_kit);
        TextView recommend_kit_name = findView(R.id.recommend_kit_name);
//        recommend_kit_name.setText("");
        TextView recommend_kit_price = findView(R.id.recommend_kit_price);
//        recommend_kit_price.setText("");
        ImageUtils.load(this, R.mipmap.ic_food_checkout_recommend_test_2, recommend_kit);

    }

    @Override
    protected String getCommonTitle() {
        return getString(R.string.Checkout);
    }


}
