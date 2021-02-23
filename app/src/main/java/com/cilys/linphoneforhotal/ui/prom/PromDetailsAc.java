package com.cilys.linphoneforhotal.ui.prom;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;

import java.util.ArrayList;
import java.util.List;

public class PromDetailsAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_prom_details;
    }

    @Override
    protected void initUI() {
        super.initUI();

        List<DetailsDataBean> datas = new ArrayList<>();
        DetailsDataBean b = new DetailsDataBean();
        datas.add(b);
        datas.add(b);

        RecyclerView rv = findView(R.id.rv);
        DetailsDataAdapter adapter = new DetailsDataAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    protected String getCommonTitle() {
        return "Promotions";
    }
}