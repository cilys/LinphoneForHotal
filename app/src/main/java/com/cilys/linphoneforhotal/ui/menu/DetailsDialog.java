package com.cilys.linphoneforhotal.ui.menu;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.dialog.BaseDialog;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsDialog extends BaseDialog {
    public final static String TYPE_REQUESTED = "Requested";
    public final static String TYPE_IN_PROGRESS = "in_progress";
    public final static String TYPE_DELIVERED = "Delivered";

    private DetailsDatasAdapter adapter;

    public DetailsDialog(Activity ac) {
        super(ac);
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog_service_menu_details;
    }

    @Override
    protected void initUI(View rootView) {
        super.initUI(rootView);

        final RadioButton rbt_req = (RadioButton)rootView.findViewById(R.id.rbt_req);
        setDrawableBottom(rbt_req, R.mipmap.icon_point_white);
        final RadioButton rbt_in_progress = (RadioButton)rootView.findViewById(R.id.rbt_in_progress);
        setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_trans);
        final RadioButton rbt_delivered = (RadioButton)rootView.findViewById(R.id.rbt_delivered);
        setDrawableBottom(rbt_delivered, R.mipmap.icon_point_trans);

        RadioGroup rg = (RadioGroup)rootView.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbt_in_progress){
                    if (adapter != null) {
                        adapter.setType(TYPE_IN_PROGRESS);
                        adapter.notifyDataSetChanged();
                    }

                    setDrawableBottom(rbt_req, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_white);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_trans);
                } else if (checkedId == R.id.rbt_delivered) {
                    if (adapter != null) {
                        adapter.setType(TYPE_DELIVERED);
                        adapter.notifyDataSetChanged();
                    }
                    setDrawableBottom(rbt_req, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_white);
                } else {
                    if (adapter != null) {
                        adapter.setType(TYPE_REQUESTED);
                        adapter.notifyDataSetChanged();
                    }
                    setDrawableBottom(rbt_req, R.mipmap.icon_point_white);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_trans);
                }
            }
        });

        ImageView right_arrow = (ImageView)rootView.findViewById(R.id.right_arrow);
        right_arrow.setImageResource(R.mipmap.icon_double_arrow_top_white);
        right_arrow.setRotation(180);
        rootView.findViewById(R.id.bottom_top_arrow).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                dismiss();
            }
        });

        TextView bottom_title = (TextView)rootView.findViewById(R.id.bottom_title);
        bottom_title.setText(getString(R.string.order_status));


        List<DetailsDataBean> datas = new ArrayList<>();
        DetailsDataBean b0 = new DetailsDataBean();
        b0.setName(getString(R.string.Arancini));
        b0.setPrice("$4.99");
        b0.setCount(2);
        b0.setPicId(R.mipmap.ic_service_menu_dialog_details_test_1);
        datas.add(b0);

        DetailsDataBean b1 = new DetailsDataBean();
        b1.setName(getString(R.string.Pizza_Marghcrita));
        b1.setPrice("$22.99");
        b1.setCount(1);
        b1.setPicId(R.mipmap.ic_service_menu_dialog_details_test_2);
        datas.add(b1);



        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);
        adapter = new DetailsDatasAdapter(datas);
        adapter.setType(TYPE_REQUESTED);
        rv.setLayoutManager(new LinearLayoutManager(ac));
        rv.setAdapter(adapter);
    }

    private void setDrawableBottom(RadioButton rbt, @DrawableRes int resId) {
        Drawable draw = ac.getResources().getDrawable(resId);
        draw.setBounds(0, 0,
                ac.getResources().getDimensionPixelSize(R.dimen.x10),
                ac.getResources().getDimensionPixelSize(R.dimen.x10));
        rbt.setCompoundDrawables(null, null, null, draw);
    }
}