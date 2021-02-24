package com.cilys.linphoneforhotal.ui.amen;

import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;

public class AmentiesAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_amenties;
    }

    @Override
    protected void initUI() {
        super.initUI();

        TextView pillow = findView(R.id.pillow);
        TextView in_room = findView(R.id.in_room);
        TextView laundries = findView(R.id.laundries);
        TextView towels = findView(R.id.towels);
        TextView bath = findView(R.id.bath);
        TextView drinks = findView(R.id.drinks);
    }


}
