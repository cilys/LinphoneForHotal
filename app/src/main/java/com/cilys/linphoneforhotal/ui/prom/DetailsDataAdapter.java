package com.cilys.linphoneforhotal.ui.prom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.utils.ImageUtils;

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
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.setImg(null);
        vh.test();
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder {
        LinearLayout test;
        ImageView img_pic;

        public VH(@NonNull View itemView) {
            super(itemView);

            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);

            test = (LinearLayout) itemView.findViewById(R.id.test);
        }

        private void setImg(String url) {
            if (img_pic != null) {
                ImageUtils.load(img_pic.getContext(), R.mipmap.ic_promo_test, img_pic);
            }
        }

        private void test(){
            if (test != null) {
                ImageUtils.load(test.getContext(), R.mipmap.ic_prom_details_test, test);
            }
        }
    }
}