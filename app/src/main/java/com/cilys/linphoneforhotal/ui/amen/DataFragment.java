package com.cilys.linphoneforhotal.ui.amen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseFg;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends BaseFg {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fg_amen_data, container, false);
        initUI(root);
        return root;
    }

    private void initUI(View rootView) {
        List<DataBean> datas = new ArrayList<>();
        DataBean b0 = new DataBean("Dry Cleaning");
        datas.add(b0);

        DataBean b1 = new DataBean(null, "Dressing Gown (Silk)",
                "$140.00", 0);
        b1.setPicId(R.mipmap.icon_dress);
        datas.add(b1);

        DataBean b2 = new DataBean(null, "Jacket",
                "$95.00", 2);
        b2.setPicId(R.mipmap.icon_necktie);
        datas.add(b2);

        DataBean b3 = new DataBean(null, "Necktie",
                "$45.00", 1);
        b3.setPicId(R.mipmap.icon_necktie_2);
        datas.add(b3);

        DataBean b4 = new DataBean(null, "Overcoat",
                "$187.00", 0);
        b4.setPicId(R.mipmap.icon_dress_2);
        datas.add(b4);

        DataBean b5 = new DataBean(null, "Pyjamas (Silk)",
                "$114.00", 0);
        b5.setPicId(R.mipmap.icon_dress_3);
        datas.add(b5);

        DataBean b6 = new DataBean("Laundry");
        datas.add(b6);

        datas.add(b5);

        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);

        DataAdapter adapter = new DataAdapter(datas);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        rootView.findViewById(R.id.next).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(getActivity(), AmenitiesCheckoutAc.class));
            }
        });
    }
}
