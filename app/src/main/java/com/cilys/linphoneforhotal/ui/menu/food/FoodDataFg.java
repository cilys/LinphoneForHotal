package com.cilys.linphoneforhotal.ui.menu.food;

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
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.impl.ItemClickListener;
import com.cilys.linphoneforhotal.ui.menu.DataBean;
import com.cilys.linphoneforhotal.ui.menu.DetailsDialog;
import com.cilys.linphoneforhotal.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class FoodDataFg extends BaseFg {
    private List<DataBean> datas;
    private DatasAdapter adapter;

    private String type;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_food_data, container, false);

        type = getArguments().getString("type", FoodAc.TYPE_dessert);

        initUI(v);
        return v;
    }

    private void initUI(View rootView) {
        String ext = "";
        if (FoodAc.TYPE_inter.equals(type)) {
            ext = "2";
        } else if (FoodAc.TYPE_italian.equals(type)) {
            ext = "3";
        } else if (FoodAc.TYPE_kids.equals(type)) {
            ext = "4";
        } else if (FoodAc.TYPE_middle.equals(type)) {
            ext = "5";
        } else {
            ext = "1";
        }

        final int randomBound = 4;

        datas = new ArrayList<>();
        datas.add(new DataBean(getString(R.string.Appetzer)));
        datas.add(new DataBean(ext + "1", null, getString(R.string.Bruschetta_Pomodoro_e_Aglio), 4.99f, 0)
                .setPicId(R.mipmap.ic_food_details_test)
                .setStatus(getRandom()));
        datas.add(new DataBean(ext + "2", null, getString(R.string.Sapori_di_Sicilia), 4.99f, 0)
                .setPicId(R.mipmap.ic_food_details_test)
                .setStatus(getRandom()));

        datas.add(new DataBean(getString(R.string.Main_dish)));
        datas.add(new DataBean(ext + "3", null, getString(R.string.Pizza_Margherita), 12.99f, 0)
                .setPicId(R.mipmap.ic_food_details_test)
                .setStatus(getRandom()));
        datas.add(new DataBean(ext + "4", null, getString(R.string.Pizza_Peperoncino), 17.99f, 0)
                .setPicId(R.mipmap.ic_food_details_test)
                .setStatus(getRandom()));

        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);
        adapter = new DatasAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("foodDetails", datas.get(position));
                Intent i = new Intent(getActivity(), MealAc.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    public static String getRandom(){
        int random = RandomUtils.getRandom(9);
        if (random % 3 == 2) {
            return DetailsDialog.TYPE_DELIVERED;
        }

        if (random % 3 == 1) {
            return DetailsDialog.TYPE_IN_PROGRESS;
        }

        return DetailsDialog.TYPE_REQUESTED;
    }

    public final static int EVENT_FOOD_ADD_TO_CAR = 1011;

    @Override
    public void onTrigger(Event event) {
        super.onTrigger(event);
        if (event.what == EVENT_FOOD_ADD_TO_CAR) {
            DataBean b = (DataBean) event.obj;
            if (b == null) {
                return;
            }

            if (datas != null) {
                for (int i = 0; i < datas.size(); i++)
                    if (datas.get(i).getId() != null) {
                        if (datas.get(i).getId().equals(b.getId())) {
                            datas.get(i).setCount(b.getCount());

                            if (adapter != null) {
                                adapter.notifyItemChanged(i);
                            }

                            break;
                        }
                    }
            }
        }
    }
}