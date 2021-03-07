package com.cilys.linphoneforhotal.ui.amen;


import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.utils.MoneyUtils;
import com.cilys.linphoneforhotal.view.MyListView;

import java.util.ArrayList;

public class AmenitiesCheckoutAc extends CommonTitleAc {
    public final static int EVENT_CHOOSE_AMEN = 1041;

    private TextView sub_total, vat_total, service_fee, tips, total;

    private ArrayList<DataBean> datas_selected;

    @Override
    protected int getLayout() {
        return R.layout.ac_amenities_checkout;
    }

    @Override
    protected void initUI() {
        super.initUI();

        datas_selected = (ArrayList<DataBean>)getIntent().getSerializableExtra("selected_amen");
        if (datas_selected == null || datas_selected.size() < 1){
            finish();
            return;
        }

        MyListView lv = findView(R.id.lv_goods);


        CheckoutDataAdapter adapter = new CheckoutDataAdapter(datas_selected);
        lv.setAdapter(adapter);

        sub_total = findView(R.id.sub_total);

        vat_total = findView(R.id.vat_total);

        service_fee = findView(R.id.service_fee);

        tips = findView(R.id.tips);

        total = findView(R.id.total);

        cal();
    }

    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);
        if (e.what == EVENT_CHOOSE_AMEN) {
            cal();
        }
    }

    private void cal(){
        if (datas_selected == null || datas_selected.size() < 1) {
            return;
        }

        float[] selectedFoods = new float[datas_selected.size()];
        for (int i = 0; i < selectedFoods.length; i++) {
            selectedFoods[i] = MoneyUtils.mul(datas_selected.get(i).getPrice(), datas_selected.get(i).getCount());
        }
        float selected_total = MoneyUtils.add(selectedFoods);
        setTextToView(sub_total, fomcatMoney(selected_total));

        float selected_vat = MoneyUtils.mul(selected_total, 0.07f);
        setTextToView(vat_total, fomcatMoney(selected_vat));

        float selected_service = MoneyUtils.mul(selected_total, 0.10f);
        setTextToView(service_fee, fomcatMoney(selected_service));

        float selected_tip = 0.00f;
        setTextToView(tips, fomcatMoney(selected_tip));

        setTextToView(total,
                fomcatMoney(MoneyUtils.add(selected_total, selected_vat, selected_service, selected_tip)));
    }

    @Override
    protected String getCommonTitle() {
        return getString(R.string.Checkout);
    }
}