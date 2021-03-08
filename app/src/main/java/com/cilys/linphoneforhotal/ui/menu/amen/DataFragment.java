package com.cilys.linphoneforhotal.ui.menu.amen;

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
import com.cilys.linphoneforhotal.ui.menu.DataBean;
import com.cilys.linphoneforhotal.ui.menu.food.FoodDataFg;
import com.cilys.linphoneforhotal.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends BaseFg {
    private String type = AmentiesAc.TYPE_pillow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fg_amen_data, container, false);

        type = getArguments().getString("type", AmentiesAc.TYPE_pillow);

        initUI(root);
        return root;
    }

    private void initUI(View rootView) {
        String ext = "";
        if (AmentiesAc.TYPE_in_room.equals(type)) {
            ext = "200";
        } else if (AmentiesAc.TYPE_laundries.equals(type)) {
            ext = "300";
        } else if (AmentiesAc.TYPE_towels.equals(type)) {
            ext = "400";
        } else if (AmentiesAc.TYPE_bath.equals(type)) {
            ext = "500";
        } else if (AmentiesAc.TYPE_drinks.equals(type)) {
            ext = "600";
        }  else if (AmentiesAc.TYPE_pillow.equals(type)) {
            ext = "100";
        }

        final int randomBound = 4;

        List<DataBean> datas = new ArrayList<>();
        DataBean b0 = new DataBean(getString(R.string.Dry_Cleaning));
        datas.add(b0);

        DataBean b1 = new DataBean(ext + "1", null, getString(R.string.Dressing_Gown_Silk),
                140.00f, 0);
        b1.setPicId(R.mipmap.icon_dress_test)
                .setStatus(getRandom());
        datas.add(b1);

        DataBean b2 = new DataBean(ext + "2", null, getString(R.string.Jacket),
                95.00f, 0);
        b2.setPicId(R.mipmap.icon_necktie_test)
                .setStatus(getRandom());
        datas.add(b2);

        DataBean b3 = new DataBean(ext + "3", null, getString(R.string.Necktie),
                45.00f, 0);
        b3.setPicId(R.mipmap.icon_necktie_2_test)
                .setStatus(getRandom());
        datas.add(b3);

        DataBean b4 = new DataBean(ext + "4", null, getString(R.string.Overcoat),
                187.00f, 0);
        b4.setPicId(R.mipmap.icon_dress_2_test)
                .setStatus(getRandom());
        datas.add(b4);

        DataBean b5 = new DataBean(ext + "5", null, getString(R.string.Pyjamas_Silk),
                114.00f, 0);
        b5.setPicId(R.mipmap.icon_dress_3_test)
                .setStatus(getRandom());
        datas.add(b5);

        DataBean b6 = new DataBean(getString(R.string.Laundry));
        datas.add(b6);

        DataBean b7 = new DataBean(ext + "6", null, getString(R.string.Pyjamas_Silk),
                114.00f, 0);
        b7.setPicId(R.mipmap.icon_dress_3_test)
                .setStatus(getRandom());
        datas.add(b7);

        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);

        DataAdapter adapter = new DataAdapter(datas);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
    }

    private String getRandom(){
        return FoodDataFg.getRandom();
    }
}
