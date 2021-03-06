package com.cilys.linphoneforhotal.ui.food;

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
import com.cilys.linphoneforhotal.impl.ItemClickListener;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class FoodDataFg extends BaseFg {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_food_data, container, false);
        initUI(v);
        return v;
    }

    private void initUI(View rootView) {
        List<DataBean> datas = new ArrayList<>();
        datas.add(new DataBean(getString(R.string.Appetzer)));
        datas.add(new DataBean(null, getString(R.string.Bruschetta_Pomodoro_e_Aglio), "$4.99", 0).setPicId(R.mipmap.ic_food_details_test));
        datas.add(new DataBean(null, getString(R.string.Sapori_di_Sicilia), "$4.99", 2).setPicId(R.mipmap.ic_food_details_test));

        datas.add(new DataBean(getString(R.string.Main_dish)));
        datas.add(new DataBean(null, getString(R.string.Pizza_Margherita), "$12.99", 1).setPicId(R.mipmap.ic_food_details_test));
        datas.add(new DataBean(null, getString(R.string.Pizza_Peperoncino), "$17.99", 0).setPicId(R.mipmap.ic_food_details_test));

        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);
        DatasAdapter adapter = new DatasAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), MealAc.class));
            }
        });

    }
}
