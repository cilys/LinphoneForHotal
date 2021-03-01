package com.cilys.linphoneforhotal.ui.tv;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.adapter.RvItemClickListener;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class TvRemoteAc extends CommonTitleAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_tv_remote;
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
        rv.setLayoutManager(new GridLayoutManager(this, getScreenModel() == Configuration.ORIENTATION_LANDSCAPE ? 5 : 3));
        adapter.setListener(new RvItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.changeSelected(position);

                showDialog();
            }
        });

        findView(R.id.bottom_top_arrow).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                showDialog();
            }
        });
    }

    @Override
    protected String getCommonTitle() {
        return getString(R.string.tv_remote);
    }

    private RemoteDialog remoteDialog;
    private void showDialog(){
        if (remoteDialog == null) {
            remoteDialog = new RemoteDialog(this);
            remoteDialog.setListener(new RemoteDialog.Listener() {
                @Override
                public void onClick(int type) {

                }
            });
        }
        remoteDialog.show();
    }
    private boolean isShowRemoteDialog(){
        if (remoteDialog == null) {
            return false;
        }
        return remoteDialog.isShowing();
    }

    private final String IS_SHOW_DIALOG = "IS_SHOW_DIALOG";
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_SHOW_DIALOG, isShowRemoteDialog());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean showDialog = false;
        if (savedInstanceState == null) {

        }
        showDialog = savedInstanceState.getBoolean(IS_SHOW_DIALOG, false);

        if (showDialog) {
            showDialog();
        }
    }
}