package com.cilys.linphoneforhotal.ui.msg;

import android.content.Intent;
import android.graphics.RectF;
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
import com.cilys.linphoneforhotal.impl.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MsgFg extends BaseFg {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fg_msg, container, false);
        initUI(root);
        return root;
    }

    private void initUI(View v) {
        List<MsgBean> datas = new ArrayList<>();
        MsgBean b0 = new MsgBean(getString(R.string.New));
        datas.add(b0);

        MsgBean b1 = new MsgBean(getString(R.string.Service), getString(R.string.order_been_accepted),"11 min ago.", true);
        datas.add(b1);

        try{
            MsgBean b2 = b1.clone();
            b2.setTime("25 min ago.");

            datas.add(b2);
        }catch (Exception e) {
            e.printStackTrace();
        }

        try{
            MsgBean b3 = b0.clone();
            b3.setTitle(getString(R.string.all));

            datas.add(b3);
        }catch (Exception e) {
            e.printStackTrace();
        }

        try{
            MsgBean b4 = b1.clone();
            b4.setTime("2 h ago");

            datas.add(b4);
        }catch (Exception e) {
            e.printStackTrace();
        }

        try{
            MsgBean b5 = b1.clone();
            b5.setTime("2 h ago");

            datas.add(b5);
        }catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView rv = (RecyclerView)v.findViewById(R.id.rv);
        MsgAdapter adapter = new MsgAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), MsgDetailsAc.class));
            }
        });
    }
}
