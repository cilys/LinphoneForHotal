package com.cilys.linphoneforhotal.ui.menu;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.adapter.RvItemClickListener;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
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
        setTextToView(bottom_title, "Order status");

        findView(R.id.bottom_top_arrow).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                showDetailsDialog();
            }
        });

        List<MenuBean> datas = new ArrayList<>();
        datas.add(new MenuBean(R.mipmap.icon_amen, "Amenities"));
        datas.add(new MenuBean(R.mipmap.icon_clean_room, "Clean Room"));
        datas.add(new MenuBean(R.mipmap.icon_concierge, "Concierge"));
        datas.add(new MenuBean(R.mipmap.icon_maintenance, "Maintenance"));
        datas.add(new MenuBean(R.mipmap.icon_food, "Food"));
        datas.add(new MenuBean(R.mipmap.icon_wakeup, "Wakeup Call"));
        datas.add(new MenuBean(R.mipmap.icon_amenities, "Amenities"));
        datas.add(new MenuBean(R.mipmap.icon_late_checkout, "Late Checkout"));
        datas.add(new MenuBean(R.mipmap.icon_dnd, "DND"));




        RecyclerView rv = findView(R.id.rv);
        ServiceMenuAdapter adapter = new ServiceMenuAdapter(datas);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(adapter);
        adapter.setListener(new RvItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showDetailsDialog();
            }
        });
    }



    @Override
    protected String getCommonTitle() {
        return "Service Menu";
    }

    private void showDetailsDialog(){
        DetailsDialog dialog = new DetailsDialog(this);
        dialog.show();
    }
}
