package com.cilys.linphoneforhotal.ui.menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class DetailsDatasAdapter extends RecyclerView.Adapter<DetailsDatasAdapter.VH> {
    private List<DetailsDataBean> datas;

    public DetailsDatasAdapter(List<DetailsDataBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service_menu_details, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, final int i) {
        vh.setName(datas.get(i).getName());
        vh.setPrice(datas.get(i).getPrice());
        vh.setCount(datas.get(i).getCount());

        if (datas.get(i).getCount() < 1) {
            vh.reduce.setVisibility(View.INVISIBLE);
        } else {
            vh.reduce.setVisibility(View.VISIBLE);
        }

        vh.add.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                int count = datas.get(i).getCount();
                count ++;
                datas.get(i).setCount(count);

                notifyDataSetChanged();
            }
        });

        vh.reduce.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                int count = datas.get(i).getCount();
                if (count < 1) {
                    return;
                }
                count --;
                datas.get(i).setCount(count);

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder {
        private TextView name, price, count;
        private View add, reduce;

        public VH(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            count = (TextView) itemView.findViewById(R.id.count);
            count.setTextColor(itemView.getContext().getResources().getColor(R.color.white));

            add =  itemView.findViewById(R.id.add);
            reduce =  itemView.findViewById(R.id.reduce);
        }

        private void setName(String name) {
            if (name != null) {
                if (this.name != null) {
                    this.name.setText(name);
                }
            }
        }

        private void setPrice(String price) {
            if (price != null) {
                if (this.price != null) {
                    this.price.setText(price);
                }
            }
        }

        private void setCount(int count) {
            if (count < 0) {
                count = 0;
            }
            if (this.count != null) {
                this.count.setText(String.valueOf(count));

                this.count.setVisibility(count <= 0 ? View.GONE : View.VISIBLE);
            }
        }
    }
}
