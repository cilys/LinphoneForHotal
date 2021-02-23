package com.cilys.linphoneforhotal.ui.msg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseFg;

public class MsgFg extends BaseFg {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fg_msg, container, false);
        initUI(root);
        return root;
    }

    private void initUI(View v) {

    }
}
