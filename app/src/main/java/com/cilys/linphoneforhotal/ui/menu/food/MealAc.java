package com.cilys.linphoneforhotal.ui.menu.food;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.event.EventBus;
import com.cilys.linphoneforhotal.ui.menu.ServiceParentAc;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;
import com.cilys.linphoneforhotal.ui.menu.DataBean;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MealAc extends ServiceParentAc {
    private int num = 1;

    @Override
    protected int getLayout() {
        return R.layout.ac_meal;
    }

    @Override
    protected void initUI() {
        super.initUI();

        final DataBean dataBean = (DataBean) getIntent().getSerializableExtra("foodDetails");
        if (dataBean == null) {
            finish();
            return;
        }

        TextView name = findView(R.id.name);
        setTextToView(name, dataBean.getName());

        TextView info = findView(R.id.info);

        final float singlePrice = dataBean.getPrice();
        TextView price = findView(R.id.price);
        setTextToView(price, fomcatMoney(singlePrice));

        final TextView total_price = findView(R.id.total_price);
        final TextView count = findView(R.id.count);
        num = dataBean.getCount();
        setTextToView(count, String.valueOf(num));
        setTextToView(total_price, calTotal(singlePrice, num));

        EditText remark = findView(R.id.remark);

        View add_car = findView(R.id.add_car);
        if (add_car != null) {
            add_car.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    if (dataBean == null) {
                        finish();
                        return;
                    }


                    if (addOptionNum > 0) {
                        for (int i = 0; i < addOptionNum; i++) {
                            Event e = new Event();
                            e.what = FoodAc.EVENT_SELECTED_FOOD_ADD;
                            e.obj = dataBean;

                            EventBus.getInstance().postEvent(e);
                        }
                    } else if (addOptionNum < 0) {
                        int addNum = Math.abs(addOptionNum);

                        for (int i = 0; i < addNum; i++) {
                            Event e = new Event();
                            e.what = FoodAc.EVENT_SELECTED_FOOD_RESUCE;
                            e.obj = dataBean;

                            EventBus.getInstance().postEvent(e);
                        }
                    }


                    Event e = new Event();
                    e.what = FoodDataFg.EVENT_FOOD_ADD_TO_CAR;
                    e.obj = dataBean;

                    EventBus.getInstance().postEvent(e);

                    finish();
                }
            });
        }
        final View add = findView(R.id.add);
        final View reduce = findView(R.id.reduce);
        if (add != null) {
            add.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    num ++;

                    addOptionNum ++;

                    dataBean.setCount(num);

                    reduce.setVisibility(View.VISIBLE);
                    count.setVisibility(View.VISIBLE);

                    setTextToView(count, String.valueOf(num));
                    setTextToView(total_price, calTotal(singlePrice, num));
                }
            });
        }
        if (reduce != null) {
            reduce.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    num--;

                    addOptionNum --;

                    if (num < 1) {
                        num = 0;

                        reduce.setVisibility(View.INVISIBLE);
                        count.setVisibility(View.INVISIBLE);

                        dataBean.setCount(num);
                        setTextToView(count, String.valueOf(num));
                        setTextToView(total_price, calTotal(singlePrice, num));
                    } else {
                        dataBean.setCount(num);

                        setTextToView(count, String.valueOf(num));
                        setTextToView(total_price, calTotal(singlePrice, num));
                    }
                }
            });
        }
        if (num <= 0) {
            if (reduce != null) {
                reduce.setVisibility(View.INVISIBLE);
            }
            if (count != null) {
                count.setVisibility(View.INVISIBLE);
            }
        }

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

    private int addOptionNum = 0;

    private String calTotal(float singlePrice, int num) {
        if (singlePrice == 0 || num == 0) {
            return fomcatMoney(0.00f);
        }
        BigDecimal b1 = new BigDecimal(singlePrice);
        BigDecimal b2 = new BigDecimal(num);
        BigDecimal res = b1.multiply(b2);
        res = res.setScale(2, RoundingMode.HALF_UP);

        return fomcatMoney(res.floatValue());
    }
}
