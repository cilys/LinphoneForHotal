package com.cilys.linphoneforhotal.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseFg;
import com.cilys.linphoneforhotal.utils.ImageUtils;

public class PromFg extends BaseFg {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_home_prom, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(View v) {
        ImageView img = (ImageView)v.findViewById(R.id.img_pic);
        ImageUtils.load(this, R.mipmap.ic_promo_test, img);
    }
}
