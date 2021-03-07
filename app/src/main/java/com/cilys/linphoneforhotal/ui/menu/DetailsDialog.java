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
//    public final static String TYPE_REQUESTED = "Requested";
//    public final static String TYPE_IN_PROGRESS = "in_progress";
//    public final static String TYPE_DELIVERED = "Delivered";
    public final static String TYPE_REQUESTED = "0";
    public final static String TYPE_IN_PROGRESS = "1";
    public final static String TYPE_DELIVERED = "2";

    private DetailsDatasAdapter adapter;
    private List<DataBean> datas;
    private String currentType = TYPE_REQUESTED;

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
                    currentType = TYPE_IN_PROGRESS;

                    anayDatas();

                    setDrawableBottom(rbt_req, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_white);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_trans);
                } else if (checkedId == R.id.rbt_delivered) {
                    currentType = TYPE_DELIVERED;

                    anayDatas();

                    setDrawableBottom(rbt_req, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_white);
                } else {
                    currentType = TYPE_REQUESTED;

                    anayDatas();

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

        datas = new ArrayList<>();
        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);
        adapter = new DetailsDatasAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(ac));
        rv.setAdapter(adapter);
    }

    private List<DataBean> datas_cache;
    public void setDatas(List<DataBean> datas) {
        if (datas == null || datas.size() < 1) {
            return;
        }
        if (this.datas_cache == null) {
            this.datas_cache = new ArrayList<>();
        }
        this.datas_cache.clear();
        this.datas_cache.addAll(datas);

        anayDatas();
    }
    private void anayDatas(){
        if (this.datas == null) {
            this.datas = new ArrayList<>();
        }
        this.datas.clear();

        for (DataBean b : datas_cache) {
            if (currentType.equals(b.getStatus())) {
                this.datas.add(b);
            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void setDrawableBottom(RadioButton rbt, @DrawableRes int resId) {
        Drawable draw = ac.getResources().getDrawable(resId);
        draw.setBounds(0, 0,
                ac.getResources().getDimensionPixelSize(R.dimen.x10),
                ac.getResources().getDimensionPixelSize(R.dimen.x10));
        rbt.setCompoundDrawables(null, null, null, draw);
    }
}