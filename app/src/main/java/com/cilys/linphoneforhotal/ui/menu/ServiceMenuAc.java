package com.cilys.linphoneforhotal.ui.menu;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.adapter.RvItemClickListener;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.ui.amen.AmentiesAc;
import com.cilys.linphoneforhotal.ui.food.FoodAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceMenuAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_service_menu;
    }

    @Override
    protected void initUI() {
        super.initUI();

        TextView bottom_title = findView(R.id.bottom_title);
        setTextToView(bottom_title, getString(R.string.order_status));

        findView(R.id.bottom_top_arrow).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                showDetailsDialog();
            }
        });

        final List<MenuBean> datas = new ArrayList<>();
        datas.add(new MenuBean(R.mipmap.icon_amen, getString(R.string.Amenities)));
        datas.add(new MenuBean(R.mipmap.icon_clean_room, getString(R.string.Clean_Room)));
        datas.add(new MenuBean(R.mipmap.icon_concierge, getString(R.string.Concierge)));
        datas.add(new MenuBean(R.mipmap.icon_maintenance, getString(R.string.Maintenance)));
        datas.add(new MenuBean(R.mipmap.icon_food, getString(R.string.Food)));
        datas.add(new MenuBean(R.mipmap.icon_wakeup, getString(R.string.Wakeup_Call)));
        datas.add(new MenuBean(R.mipmap.icon_amenities, getString(R.string.Amenities)));
        datas.add(new MenuBean(R.mipmap.icon_late_checkout, getString(R.string.Late_Checkout)));
        datas.add(new MenuBean(R.mipmap.icon_dnd, getString(R.string.DND)));




        RecyclerView rv = findView(R.id.rv);
        ServiceMenuAdapter adapter = new ServiceMenuAdapter(datas);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(adapter);
        adapter.setListener(new RvItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (getString(R.string.Amenities).equals(datas.get(position).getName())) {
                    startActivity(new Intent(ServiceMenuAc.this, AmentiesAc.class));
                } else if (getString(R.string.Food).equals(datas.get(position).getName())) {
                    startActivity(new Intent(ServiceMenuAc.this, FoodAc.class));
                }
            }
        });
    }



    @Override
    protected String getCommonTitle() {
        return getString(R.string.Service_Menu);
    }

    private void showDetailsDialog(){
        DetailsDialog dialog = new DetailsDialog(this);
        dialog.show();
    }
}
