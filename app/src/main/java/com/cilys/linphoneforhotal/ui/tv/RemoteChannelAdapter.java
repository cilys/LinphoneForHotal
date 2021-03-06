package com.cilys.linphoneforhotal.ui.tv;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
    public void onBindViewHolder(@NonNull VH vh, int position) {
        vh.setImageResource(datas.get(position).getResourceId());
        final int i = position;
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
        vh.setSelected(datas.get(position).isSelected());
    }

    public void changeSelected(int position) {
        if (datas == null) {
            return;
        }
        if (datas.size() < 1) {
            return;
        }

        boolean selected = datas.get(position).isSelected();
        if (selected) {
            datas.get(position).setSelected(false);
        } else {
            for (ChannelBean b : datas) {
                b.setSelected(false);
            }
            datas.get(position).setSelected(true);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder {
        private ImageView img;
        private View rootView;
        private LinearLayout channel_parent;

        public VH(@NonNull View v) {
            super(v);
            rootView = v;
            img = (ImageView)v.findViewById(R.id.channel_icon);
            channel_parent = (LinearLayout)v.findViewById(R.id.channel_parent);
        }

        public void setSelected(boolean selected) {
            if (channel_parent != null) {
                channel_parent.setBackgroundResource(selected ? R.drawable.shape_round_bg_tv_remote_parent_selected : R.drawable.shape_round_bg_tv_remote_parent_normal);
            }
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