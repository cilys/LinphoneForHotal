package com.cilys.linphoneforhotal.ui.home;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.ViewGroup;

import com.cilys.linphoneforhotal.base.BaseFg;

import java.util.List;

public class VpHomeAdapter extends FragmentPagerAdapter {
    private List<? extends BaseFg> fgs;
    public VpHomeAdapter(FragmentManager fm, List<? extends BaseFg> fgs) {
        super(fm);
        this.fgs = fgs;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public Fragment getItem(int i) {
        return (fgs == null || fgs.size() < 1) ? null : fgs.get(i);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position = position% fgs.size();

        return super.instantiateItem(container, position);
    }
}