package com.cilys.linphoneforhotal.ui.food;


import android.view.View;
import android.widget.ImageView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public class MealAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_meal;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.root, R.mipmap.ic_meal_details_bg);
        setBackgroundById(R.id.model_layout, R.mipmap.ic_meal_model_bg);

        ImageView pic = findView(R.id.pic);
        ImageUtils.load(this, R.mipmap.ic_meal_details_test, pic);

        findView(R.id.close).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
            }
        });
    }
}
