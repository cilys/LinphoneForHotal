package com.cilys.linphoneforhotal.ui.menu;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.dialog.BaseDialog;

public class DetailsDialog extends BaseDialog {
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
                    setDrawableBottom(rbt_req, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_white);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_trans);
                } else if (checkedId == R.id.rbt_delivered) {
                    setDrawableBottom(rbt_req, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_white);
                } else {
                    setDrawableBottom(rbt_req, R.mipmap.icon_point_white);
                    setDrawableBottom(rbt_in_progress, R.mipmap.icon_point_trans);
                    setDrawableBottom(rbt_delivered, R.mipmap.icon_point_trans);
                }
            }
        });

        ImageView right_arrow = (ImageView)rootView.findViewById(R.id.right_arrow);
        right_arrow.setImageResource(R.mipmap.icon_double_arrow_top_white);
        right_arrow.setRotation(180);

        TextView bottom_title = (TextView)rootView.findViewById(R.id.bottom_title);
        bottom_title.setText(ac.getResources().getString(R.string.order_status));
    }

    private void setDrawableBottom(RadioButton rbt, @DrawableRes int resId) {
        Drawable draw = ac.getResources().getDrawable(resId);
        draw.setBounds(0, 0,
                ac.getResources().getDimensionPixelSize(R.dimen.x10),
                ac.getResources().getDimensionPixelSize(R.dimen.x10));
        rbt.setCompoundDrawables(null, null, null, draw);
    }
}