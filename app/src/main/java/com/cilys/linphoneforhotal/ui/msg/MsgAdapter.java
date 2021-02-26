package com.cilys.linphoneforhotal.ui.msg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.impl.ItemClickListener;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MsgBean> datas;

    public MsgAdapter(List<MsgBean> datas) {
        this.datas = datas;
    }

    private final int TYPE_TITLE = 0;
    private final int TYPE_VALUE = 1;

    @Override
    public int getItemViewType(int position) {
        String title = datas.get(position).getTitle();
        if (title == null) {
            return TYPE_VALUE;
        } else {
            return TYPE_TITLE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_TITLE) {
            return new VH_Title(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_title, viewGroup, false));
        } else {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_msg_value, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, final int i) {
        if (vh instanceof VH_Title) {
            ((VH_Title) vh).setTitle(datas.get(i).getTitle());
        } else if (vh instanceof VH) {
            ((VH) vh).setData(datas.get(i));

            ((VH) vh).rootView.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected static class VH_Title extends RecyclerView.ViewHolder {
        private TextView title;

        public VH_Title(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }

        private void setTitle(String str) {
            if (str != null) {
                if (title != null) {
                    title.setText(str);
                }
            }
        }
    }

    protected static class VH extends RecyclerView.ViewHolder {
        private ImageView red_point;
        private TextView name, time, info;
        private View rootView;

        public VH(@NonNull View itemView) {
            super(itemView);

            rootView = itemView;

            red_point = (ImageView) itemView.findViewById(R.id.red_point);

            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            info = (TextView) itemView.findViewById(R.id.info);
        }

        private void setData(MsgBean bean) {
            name.setText(bean.getName());
            time.setText(bean.getTime());
            info.setText(bean.getInfo());

            red_point.setVisibility(bean.isNewMsg() ? View.VISIBLE : View.VISIBLE);
        }
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
