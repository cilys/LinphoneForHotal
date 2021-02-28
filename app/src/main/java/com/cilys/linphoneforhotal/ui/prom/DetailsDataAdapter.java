package com.cilys.linphoneforhotal.ui.prom;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.impl.ItemClickListener;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class DetailsDataAdapter extends RecyclerView.Adapter<DetailsDataAdapter.VH> {
    private List<DetailsDataBean> datas;

    public DetailsDataAdapter(List<DetailsDataBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_prom_details, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, final int i) {
        vh.setImg(null);
        vh.startDay.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, i);
                }
            }
        });
        vh.endDay.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder {
        ImageView img_pic;
        private TextView oldPrice;
        private TextView startDay, endDay;

        public VH(@NonNull View itemView) {
            super(itemView);

            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);

            startDay = (TextView) itemView.findViewById(R.id.startDay);
            endDay = (TextView) itemView.findViewById(R.id.endDay);

            oldPrice = (TextView) itemView.findViewById(R.id.oldPrice);
            oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }

        private void setImg(String url) {
            if (img_pic != null) {
                ImageUtils.load(img_pic.getContext(), R.mipmap.ic_promo_test, img_pic);
            }
        }
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}