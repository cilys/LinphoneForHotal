package com.cilys.linphoneforhotal.tv;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.adapter.RvItemClickListener;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class RemoteChannelAdapter extends RecyclerView.Adapter<RemoteChannelAdapter.VH> {
    private List<ChannelBean> datas;

    public RemoteChannelAdapter(List<ChannelBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_tv_remote_channel, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, final int i) {
        vh.setImageResource(datas.get(i).getResourceId());
        if (vh.rootView != null) {
            vh.rootView.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder {
        private ImageView img;
        private View rootView;

        public VH(@NonNull View v) {
            super(v);
            rootView = v;
            img = (ImageView)v.findViewById(R.id.channel_icon);
        }

        public void setImageResource(@DrawableRes int resourceId) {
            if (img != null) {
                img.setImageResource(resourceId);
            }
        }
    }

    private RvItemClickListener listener;

    public void setListener(RvItemClickListener listener) {
        this.listener = listener;
    }
}
