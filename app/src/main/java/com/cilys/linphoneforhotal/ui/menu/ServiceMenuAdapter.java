package com.cilys.linphoneforhotal.ui.menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.adapter.RvItemClickListener;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.List;

public class ServiceMenuAdapter extends RecyclerView.Adapter<ServiceMenuAdapter.VH> {
  private List<MenuBean> datas;

  public ServiceMenuAdapter(List<MenuBean> datas){
    this.datas = datas;
  }

  @NonNull
  @Override
  public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service_menu, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull VH vh, int i) {
    vh.setIcon(datas.get(i).getResourceId());
    vh.setName(datas.get(i).getName());
    if (listener != null) {
      if (vh.rootView != null) {
        final int position = i;
        vh.rootView.setOnClickListener(new SingleClickListener() {
          @Override
          public void onSingleClick(View v) {
            listener.onItemClick(v, position);
          }
        });
      }
    }
  }

  @Override
  public int getItemCount() {
    return datas == null ? 0 : datas.size();
  }

  protected static class VH extends RecyclerView.ViewHolder {
    ImageView menu_icon;
    TextView menu_name;
    View rootView;
    public VH(@NonNull View itemView) {
      super(itemView);
      rootView = itemView;
      menu_icon = (ImageView)itemView.findViewById(R.id.menu_icon);
      menu_name = (TextView)itemView.findViewById(R.id.menu_name);
    }

    public void setIcon(int resourceId) {
      if (menu_icon != null) {
        menu_icon.setImageResource(resourceId);
      }
    }

    public void setName(String name) {
      if (menu_name != null) {
        menu_name.setText(name == null ? "" : name);
      }
    }
  }

  private RvItemClickListener listener;

  public void setListener(RvItemClickListener listener) {
    this.listener = listener;
  }
}
