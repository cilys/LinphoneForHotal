package com.cilys.linphoneforhotal.ui.food;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cilys.linphoneforhotal.base.BaseFg;

import java.util.List;

public class FgAdapter extends FragmentPagerAdapter {
    private List<FoodDataFg> fgs;

    public FgAdapter(FragmentManager fm, List<FoodDataFg> fgs) {
        super(fm);
        this.fgs = fgs;
    }



    @Override
    public FoodDataFg getItem(int i) {
        return fgs.get(i);
    }

    @Override
    public int getCount() {
        return fgs == null ? 0 : fgs.size();
    }
}
