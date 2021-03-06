package com.cilys.linphoneforhotal.ui.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class FoodAc extends CommonTitleAc {
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
        b0.putString("type", "dessert");
        f0.setArguments(b0);
        fgs.add(f0);

        FoodDataFg f1 = new FoodDataFg();
        Bundle b1 = new Bundle();
        b1.putString("type", "inter");
        f1.setArguments(b1);
        fgs.add(f1);

        FoodDataFg f2 = new FoodDataFg();
        Bundle b2 = new Bundle();
        b2.putString("type", "italian");
        f2.setArguments(b2);
        fgs.add(f2);

        FoodDataFg f3 = new FoodDataFg();
        Bundle b3 = new Bundle();
        b3.putString("type", "kids");
        f3.setArguments(b3);
        fgs.add(f3);

        FoodDataFg f4 = new FoodDataFg();
        Bundle b4 = new Bundle();
        b4.putString("type", "middle");
        f0.setArguments(b4);
        fgs.add(f4);

        FgAdapter adapter = new FgAdapter(getSupportFragmentManager(), fgs);
        vp.setAdapter(adapter);
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

        findViewById(R.id.next).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(FoodAc.this, CheckoutAc.class));
            }
        });
    }

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
}
