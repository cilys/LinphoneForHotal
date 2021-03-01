package com.cilys.linphoneforhotal.ui.msg;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;

import java.util.ArrayList;
import java.util.List;

public class MsgAc extends CommonTitleAc {
    private ViewPager vp;

    @Override
    protected int getLayout() {
        return R.layout.ac_msg;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setBackgroundById(R.id.root, R.mipmap.ic_call_bg);

        final RadioButton rbt_all = (RadioButton)findViewById(R.id.rbt_all);
        setDrawableBottom(rbt_all, R.mipmap.icon_point_red);
        final RadioButton rbt_unread = (RadioButton)findViewById(R.id.rbt_unread);
        setDrawableBottom(rbt_unread, R.mipmap.icon_point_trans);
        final RadioButton rbt_read = (RadioButton)findViewById(R.id.rbt_read);
        setDrawableBottom(rbt_read, R.mipmap.icon_point_trans);

        vp = findView(R.id.vp);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 1) {
                    rbt_unread.setChecked(true);
                } else if (i == 2) {
                    rbt_read.setChecked(true);
                } else {
                    rbt_all.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbt_unread){
                    setDrawableBottom(rbt_all, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_unread, R.mipmap.icon_point_red);
                    setDrawableBottom(rbt_read, R.mipmap.icon_point_trans);
                    vp.setCurrentItem(1);
                } else if (checkedId == R.id.rbt_read) {
                    setDrawableBottom(rbt_all, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_unread, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_read, R.mipmap.icon_point_red);
                    vp.setCurrentItem(2);
                } else {
                    setDrawableBottom(rbt_all, R.mipmap.icon_point_red);
                    setDrawableBottom(rbt_unread, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_read, R.mipmap.icon_point_trans);
                    vp.setCurrentItem(0);
                }
            }
        });


        List<MsgFg> fgs = new ArrayList<>();
        MsgFg all = new MsgFg();
        Bundle b0 = new Bundle();
        b0.putString("type", getString(R.string.all));
        all.setArguments(b0);
        fgs.add(all);

        MsgFg unread = new MsgFg();
        Bundle b1 = new Bundle();
        b1.putString("type", getString(R.string.unread));
        unread.setArguments(b1);
        fgs.add(unread);

        MsgFg read = new MsgFg();
        Bundle b2 = new Bundle();
        b2.putString("type", getString(R.string.read));
        read.setArguments(b2);
        fgs.add(read);

        MsgFgAdapter adapter = new MsgFgAdapter(getSupportFragmentManager(), fgs);
        vp.setAdapter(adapter);
    }

    private void setDrawableBottom(RadioButton rbt, @DrawableRes int resId) {
        Drawable draw = getResources().getDrawable(resId);
        draw.setBounds(0, 0,
                getResources().getDimensionPixelSize(R.dimen.x10),
                getResources().getDimensionPixelSize(R.dimen.x10));
        rbt.setCompoundDrawables(null, null, null, draw);
    }
}