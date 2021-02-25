package com.cilys.linphoneforhotal.ui.amen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.impl.ItemClickListener;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataBean> datas;

    public DataAdapter(List<DataBean> datas) {
        this.datas = datas;
    }

    private final int TYPE_TITLE = 0;
    private final int TYPE_VALUE = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_TITLE) {
            return new VH_Title(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_msg_title, viewGroup, false));
        } else {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_amen_data_value, viewGroup, false));
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

            ((VH) vh).add.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    int count = datas.get(i).getCount();
                    count ++;
                    datas.get(i).setCount(count);

                    notifyDataSetChanged();
                }
            });

            ((VH) vh).reduce.setOnClickListener(new SingleClickListener() {
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
    }

    @Override
    public int getItemViewType(int position) {
        String title = datas.get(position).getTitle();
        if (title == null) {
            return TYPE_VALUE;
        } else {
            return TYPE_TITLE;
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
        private View rootView;
        private ImageView img;
        private TextView name, price, count;
        private View reduce, add;

        public VH(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;

            img = (ImageView)itemView.findViewById(R.id.img);

            name = (TextView)itemView.findViewById(R.id.name);
            price = (TextView)itemView.findViewById(R.id.price);
            count = (TextView)itemView.findViewById(R.id.count);

            reduce = (View)itemView.findViewById(R.id.reduce);
            add = (View)itemView.findViewById(R.id.add);
        }

        private void setData(DataBean bean) {
            if (img != null) {
//                ImageUtils.load(img.getContext(), bean.getPicId(), img);
                img.setBackgroundResource(bean.getPicId());
            }

            int count = bean.getCount();
            if (count < 0) {
                count = 0;
            }

            this.count.setVisibility(count < 1 ? View.GONE : View.VISIBLE);
            this.reduce.setVisibility(count < 1 ? View.GONE : View.VISIBLE);

            if (this.count != null) {
                this.count.setText(String.valueOf(count));
            }
            if (this.name != null) {
                this.name.setText(bean.getName());
            }
            if (this.price != null) {
                this.price.setText(bean.getPrice());
            }
        }
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
