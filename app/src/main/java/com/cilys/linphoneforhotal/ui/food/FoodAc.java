package com.cilys.linphoneforhotal.ui.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodAc extends CommonTitleAc {
    public final static String TYPE_dessert = "TYPE_dessert";
    public final static String TYPE_inter = "TYPE_inter";
    public final static String TYPE_italian = "TYPE_italian";
    public final static String TYPE_kids = "TYPE_kids";
    public final static String TYPE_middle = "TYPE_middle";

    private TextView dessert, inter, italian, kids, middle;

    @Override
    protected int getLayout() {
        return R.layout.ac_food;
    }

    @Override
    protected void initUI() {
        super.initUI();

        final ViewPager vp = findView(R.id.vp);

        dessert = findView(R.id.dessert);
        dessert.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(0);
                vp.setCurrentItem(0);
            }
        });

        inter = findView(R.id.inter);
        inter.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(1);
                vp.setCurrentItem(1);
            }
        });

        italian = findView(R.id.italian);
        italian.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(2);
                vp.setCurrentItem(2);
            }
        });

        kids = findView(R.id.kids);
        kids.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(3);
                vp.setCurrentItem(3);
            }
        });

        middle = findView(R.id.middle);
        middle.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(4);
                vp.setCurrentItem(4);
            }
        });

        setSelect(0);

        List<FoodDataFg> fgs = new ArrayList<>();
        FoodDataFg f0 = new FoodDataFg();
        Bundle b0 = new Bundle();
        b0.putString("type", TYPE_dessert);
        f0.setArguments(b0);
        fgs.add(f0);

        FoodDataFg f1 = new FoodDataFg();
        Bundle b1 = new Bundle();
        b1.putString("type", TYPE_inter);
        f1.setArguments(b1);
        fgs.add(f1);

        FoodDataFg f2 = new FoodDataFg();
        Bundle b2 = new Bundle();
        b2.putString("type", TYPE_italian);
        f2.setArguments(b2);
        fgs.add(f2);

        FoodDataFg f3 = new FoodDataFg();
        Bundle b3 = new Bundle();
        b3.putString("type", TYPE_kids);
        f3.setArguments(b3);
        fgs.add(f3);

        FoodDataFg f4 = new FoodDataFg();
        Bundle b4 = new Bundle();
        b4.putString("type", TYPE_middle);
        f4.setArguments(b4);
        fgs.add(f4);

        FgAdapter adapter = new FgAdapter(getSupportFragmentManager(), fgs);
        vp.setAdapter(adapter);

        //TODO 未做缓存数据恢复功能，先取巧解决数据缓存问题
        vp.setOffscreenPageLimit(fgs.size());

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setSelect(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        items_count = findView(R.id.bottom_title);
        total_price = findView(R.id.total_price);

        findViewById(R.id.next).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (selected_count_item <= 0) {
                    showToast(getString(R.string.please_select_food));
                    return;
                }
                startActivity(new Intent(FoodAc.this, CheckoutAc.class));
            }
        });
    }

    private TextView items_count;
    private TextView total_price;

    private void setSelect(int i) {
        setDrawableBottom(dessert, R.mipmap.icon_point_trans);
        setDrawableBottom(inter, R.mipmap.icon_point_trans);
        setDrawableBottom(italian, R.mipmap.icon_point_trans);
        setDrawableBottom(kids, R.mipmap.icon_point_trans);
        setDrawableBottom(middle, R.mipmap.icon_point_trans);

        if (i == 1) {
            setDrawableBottom(inter, R.mipmap.icon_point_red);
        } else if (i == 2) {
            setDrawableBottom(italian, R.mipmap.icon_point_red);
        } else if (i == 3) {
            setDrawableBottom(kids, R.mipmap.icon_point_red);
        } else if (i == 4) {
            setDrawableBottom(middle, R.mipmap.icon_point_red);
        } else {
            setDrawableBottom(dessert, R.mipmap.icon_point_red);
        }
    }

    @Override
    protected String getCommonTitle() {
        return getString(R.string.food_order);
    }

    //已选中的菜品
    private Map<String, DataBean> map_selected_food;


    public final static int EVENT_SELECTED_FOOD_ADD = 1001;
    public final static int EVENT_SELECTED_FOOD_RESUCE = 1002;
    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);

        if (e.what == EVENT_SELECTED_FOOD_ADD) {
            if (map_selected_food == null) {
                map_selected_food = new HashMap<>();
            }
            DataBean bean = (DataBean)e.obj;
            if (bean == null) {
                return;
            }
            String id = bean.getId();

            map_selected_food.put(id, bean);

            selected_count_item ++;
            items_count.setText(selected_count_item + " " + getString(R.string.Items));

            BigDecimal d = new BigDecimal(selected_total_price);
            BigDecimal d2 = new BigDecimal(bean.getPrice());
            BigDecimal res = d.add(d2);
            res = res.setScale(2, RoundingMode.HALF_UP);

            selected_total_price = res.floatValue();

            total_price.setText(getString(R.string.money_unit) + selected_total_price);
        } else if (e.what == EVENT_SELECTED_FOOD_RESUCE) {
            if (map_selected_food == null) {
                map_selected_food = new HashMap<>();
            }
            DataBean bean = (DataBean)e.obj;
            if (bean == null) {
                return;
            }
            String id = bean.getId();

            int count = bean.getCount();
            if (count <= 0) {
                map_selected_food.remove(id);
            }else {
                map_selected_food.put(id, bean);
            }

            selected_count_item --;
            items_count.setText(selected_count_item + " " + getString(R.string.Items));

            BigDecimal d = new BigDecimal(selected_total_price);
            BigDecimal d2 = new BigDecimal(bean.getPrice());
            BigDecimal res = d.subtract(d2);
            res = res.setScale(2, RoundingMode.HALF_UP);

            selected_total_price = res.floatValue();

            total_price.setText(getString(R.string.money_unit) + selected_total_price);
        }
    }

    private int selected_count_item = 0;
    private float selected_total_price = 0.00f;
}