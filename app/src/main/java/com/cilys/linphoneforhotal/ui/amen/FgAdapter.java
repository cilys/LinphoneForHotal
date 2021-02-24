package com.cilys.linphoneforhotal.ui.amen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FgAdapter extends FragmentPagerAdapter {
    private List<DataFragment> datas;
    public FgAdapter(FragmentManager fm, List<DataFragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int i) {
        return datas.get(i);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }
}
