package com.cilys.linphoneforhotal.ui.amen;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class AmentiesAc extends CommonTitleAc {
    private TextView pillow, in_room, laundries, towels, bath, drinks;
    @Override
    protected int getLayout() {
        return R.layout.ac_amenties;
    }

    @Override
    protected void initUI() {
        super.initUI();
        final ViewPager vp = findView(R.id.vp);

        pillow = findView(R.id.pillow);
        pillow.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(0);
                vp.setCurrentItem(0);
            }
        });
        in_room = findView(R.id.in_room);
        in_room.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(1);
                vp.setCurrentItem(1);
            }
        });

        laundries = findView(R.id.laundries);
        laundries.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(2);
                vp.setCurrentItem(2);
            }
        });

        towels = findView(R.id.towels);
        towels.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(3);
                vp.setCurrentItem(3);
            }
        });

        bath = findView(R.id.bath);
        bath.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(4);
                vp.setCurrentItem(4);
            }
        });

        drinks = findView(R.id.drinks);
        drinks.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(5);
                vp.setCurrentItem(5);
            }
        });

        setSelect(0);

        List<DataFragment> fgs = new ArrayList<>();
        DataFragment f0 = new DataFragment();
        Bundle b0 = new Bundle();
        b0.putString("type", "pillow");
        f0.setArguments(b0);
        fgs.add(f0);

        DataFragment f1 = new DataFragment();
        Bundle b1 = new Bundle();
        b1.putString("type", "in_room");
        f1.setArguments(b1);
        fgs.add(f1);

        DataFragment f2 = new DataFragment();
        Bundle b2 = new Bundle();
        b2.putString("type", "laundries");
        f2.setArguments(b2);
        fgs.add(f2);

        DataFragment f3 = new DataFragment();
        Bundle b3 = new Bundle();
        b3.putString("type", "towels");
        f3.setArguments(b3);
        fgs.add(f3);

        DataFragment f4 = new DataFragment();
        Bundle b4 = new Bundle();
        b4.putString("type", "bath");
        f0.setArguments(b4);
        fgs.add(f4);

        DataFragment f5 = new DataFragment();
        Bundle b5 = new Bundle();
        b5.putString("type", "drinks");
        f5.setArguments(b5);
        fgs.add(f5);


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
    }

    private void setSelect(int i) {
        setDrawableBottom(pillow, R.mipmap.icon_point_trans);
        setDrawableBottom(in_room, R.mipmap.icon_point_trans);
        setDrawableBottom(laundries, R.mipmap.icon_point_trans);
        setDrawableBottom(towels, R.mipmap.icon_point_trans);
        setDrawableBottom(bath, R.mipmap.icon_point_trans);
        setDrawableBottom(drinks, R.mipmap.icon_point_trans);

        if (i == 1) {
            setDrawableBottom(in_room, R.mipmap.icon_point_red);
        } else if (i == 2) {
            setDrawableBottom(laundries, R.mipmap.icon_point_red);
        } else if (i == 3) {
            setDrawableBottom(towels, R.mipmap.icon_point_red);
        } else if (i == 4) {
            setDrawableBottom(bath, R.mipmap.icon_point_red);
        } else if (i == 5) {
            setDrawableBottom(drinks, R.mipmap.icon_point_red);
        } else {
            setDrawableBottom(pillow, R.mipmap.icon_point_red);
        }
    }
}
