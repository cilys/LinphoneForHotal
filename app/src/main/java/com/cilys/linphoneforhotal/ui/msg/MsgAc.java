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

        final ViewPager vp = findView(R.id.vp);

        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbt_unread){
                    setDrawableBottom(rbt_all, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_unread, R.mipmap.icon_point_red);
                    setDrawableBottom(rbt_read, R.mipmap.icon_point_trans);
                } else if (checkedId == R.id.rbt_read) {
                    setDrawableBottom(rbt_all, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_unread, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_read, R.mipmap.icon_point_red);
                } else {
                    setDrawableBottom(rbt_all, R.mipmap.icon_point_red);
                    setDrawableBottom(rbt_unread, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_read, R.mipmap.icon_point_trans);
                }
            }
        });


        List<MsgFg> fgs = new ArrayList<>();
        MsgFg all = new MsgFg();
        Bundle b0 = new Bundle();
        b0.putString("type", "all");
        all.setArguments(b0);
        fgs.add(all);

        MsgFg unread = new MsgFg();
        Bundle b1 = new Bundle();
        b1.putString("type", "unread");
        unread.setArguments(b1);
        fgs.add(unread);

        MsgFg read = new MsgFg();
        Bundle b2 = new Bundle();
        b2.putString("type", "read");
        read.setArguments(b2);
        fgs.add(read);

        MsgAdapter adapter = new MsgAdapter(getSupportFragmentManager(), fgs);
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