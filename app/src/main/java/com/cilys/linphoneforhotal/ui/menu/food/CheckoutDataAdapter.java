package com.cilys.linphoneforhotal.ui.menu.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.event.EventBus;
import com.cilys.linphoneforhotal.utils.MoneyUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class CheckoutDataAdapter extends BaseAdapter {
    private List<DataBean> datas;

    public CheckoutDataAdapter(List<DataBean> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public DataBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        VH vh;

        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amen_data_value, parent, false);
            vh = new VH();
            vh.img = (ImageView)v.findViewById(R.id.img);

            vh.name = (TextView)v.findViewById(R.id.name);
            vh.price = (TextView)v.findViewById(R.id.price);
            vh.count = (TextView)v.findViewById(R.id.count);

            vh.reduce = v.findViewById(R.id.reduce);
            vh.add = v.findViewById(R.id.add);

            v.setTag(vh);
        } else {
            vh = (VH)v.getTag();
        }
        vh.add.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                int count = datas.get(position).getCount();
                count ++;
                datas.get(position).setCount(count);

                EventBus.getInstance().postEvent(CheckoutAc.EVENT_CHOOSE_FOOD);

                notifyDataSetChanged();
            }
        });

        vh.reduce.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                int count = datas.get(position).getCount();
                if (count < 1) {
                    return;
                }
                count --;
                datas.get(position).setCount(count);

                EventBus.getInstance().postEvent(CheckoutAc.EVENT_CHOOSE_FOOD);

                notifyDataSetChanged();
            }
        });

        vh.img.setBackgroundResource(datas.get(position).getPicId());
        int count = datas.get(position).getCount();
        if (count < 0) {
            count = 0;
        }

        vh.count.setVisibility(count < 1 ? View.GONE : View.VISIBLE);
        vh.reduce.setVisibility(count < 1 ? View.GONE : View.VISIBLE);

        if (vh.count != null) {
            vh.count.setText(String.valueOf(count));
        }
        if (vh.name != null) {
            vh.name.setText(datas.get(position).getName());
        }
        if (vh.price != null) {
            vh.price.setText(vh.price.getContext().getString(R.string.money_unit)
                    + MoneyUtils.fomcatMoney(datas.get(position).getPrice()));
        }

        return v;
    }

    private static class VH {
        private View rootView;
        private ImageView img;
        private TextView name, price, count;
        private View reduce, add;
    }
}
