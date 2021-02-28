package com.cilys.linphoneforhotal.ui.prom;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.impl.ItemClickListener;

import java.util.ArrayList;
import java.util.Calendar;
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

        adapter.setItemClickListener(new ItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(View view, int position) {
                if (view.getId() == R.id.startDay || view.getId() == R.id.endDay) {
                    showDatePicker ();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePicker (){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, null, year, month, day);
        dialog.show();
    }

    @Override
    protected String getCommonTitle() {
        return "Promotions";
    }
}