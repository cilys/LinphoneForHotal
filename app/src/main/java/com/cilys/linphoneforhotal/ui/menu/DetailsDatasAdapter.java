package com.cilys.linphoneforhotal.ui.menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.utils.MoneyUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class DetailsDatasAdapter extends RecyclerView.Adapter<DetailsDatasAdapter.VH> {
    private List<DataBean> datas;
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public DetailsDatasAdapter(List<DataBean> datas) {
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
        vh.setPic(datas.get(i).getPicId());



        if (datas.get(i).getCount() < 1) {
            vh.reduce.setVisibility(View.INVISIBLE);
        } else {
            vh.reduce.setVisibility(View.VISIBLE);
        }

        vh.setData(datas.get(i), type);

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
        private ImageView img;

        public VH(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);

            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            count = (TextView) itemView.findViewById(R.id.count);
            count.setTextColor(itemView.getContext().getResources().getColor(R.color.white));

            add =  itemView.findViewById(R.id.add);
            reduce =  itemView.findViewById(R.id.reduce);
        }

        private void setData(DataBean bean, String type) {
            if (bean != null) {
                if (DetailsDialog.TYPE_IN_PROGRESS.equals(type) || DetailsDialog.TYPE_DELIVERED.equals(type)) {
                    if (add != null) {
                        add.setVisibility(View.INVISIBLE);
                    }
                    if (reduce != null) {
                        reduce.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (add != null) {
                        add.setVisibility(View.VISIBLE);
                    }
                    if (reduce != null) {
                        reduce.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

        private void setPic(int picId) {
            if (img != null) {
                ImageUtils.load(img.getContext(), picId, img);
            }
        }

        private void setName(String name) {
            if (name != null) {
                if (this.name != null) {
                    this.name.setText(name);
                }
            }
        }

        private void setPrice(float price) {
            if (this.price != null) {
                this.price.setText(this.price.getContext().getResources().getString(R.string.money_unit)
                            + MoneyUtils.fomcatMoney(price));
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
