package com.cilys.linphoneforhotal.tv;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.adapter.RvItemClickListener;
import com.cilys.linphoneforhotal.base.BaseAc;

import java.util.ArrayList;
import java.util.List;

public class TvAc extends BaseAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_tv;
    }

    @Override
    protected void initUI() {
        super.initUI();

        List<ChannelBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i % 5 == 0) {
                datas.add(new ChannelBean(R.mipmap.icon_tv_cnn));
            } else if (i % 5 == 1) {
                datas.add(new ChannelBean(R.mipmap.icon_tv_discovery));
            } else if (i % 5 == 2) {
                datas.add(new ChannelBean(R.mipmap.icon_tv_fox));
            } else if (i % 5 == 3) {
                datas.add(new ChannelBean(R.mipmap.icon_tv_hbo));
            } else if (i % 5 == 4) {
                datas.add(new ChannelBean(R.mipmap.icon_tv_nbc));
            }
        }

        final RemoteChannelAdapter adapter = new RemoteChannelAdapter(datas);

        RecyclerView rv = findView(R.id.rv);
        rv.setAdapter(adapter);
        adapter.setListener(new RvItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.changeSelected(position);
            }
        });
    }
}