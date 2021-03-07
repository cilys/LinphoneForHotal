package com.cilys.linphoneforhotal.ui.menu.amen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.event.Event;
import com.cilys.linphoneforhotal.utils.MoneyUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmentiesAc extends CommonTitleAc {
    public final static String TYPE_pillow = "TYPE_pillow";
    public final static String TYPE_in_room = "TYPE_in_room";
    public final static String TYPE_laundries = "TYPE_laundries";
    public final static String TYPE_towels = "TYPE_towels";
    public final static String TYPE_bath = "TYPE_bath";
    public final static String TYPE_drinks = "TYPE_drinks";

    private TextView pillow, in_room, laundries, towels, bath, drinks;
    @Override
    protected int getLayout() {
        return R.layout.ac_amenties;
    }

    @Override
    protected void initUI() {
        super.initUI();
        final ViewPager vp = findView(R.id.vp);

        pillow = findView(R.id.pillow);
        pillow.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(0);
                vp.setCurrentItem(0);
            }
        });
        in_room = findView(R.id.in_room);
        in_room.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(1);
                vp.setCurrentItem(1);
            }
        });

        laundries = findView(R.id.laundries);
        laundries.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(2);
                vp.setCurrentItem(2);
            }
        });

        towels = findView(R.id.towels);
        towels.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(3);
                vp.setCurrentItem(3);
            }
        });

        bath = findView(R.id.bath);
        bath.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(4);
                vp.setCurrentItem(4);
            }
        });

        drinks = findView(R.id.drinks);
        drinks.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                setSelect(5);
                vp.setCurrentItem(5);
            }
        });

        setSelect(0);

        List<DataFragment> fgs = new ArrayList<>();
        DataFragment f0 = new DataFragment();
        Bundle b0 = new Bundle();
        b0.putString("type", TYPE_pillow);
        f0.setArguments(b0);
        fgs.add(f0);

        DataFragment f1 = new DataFragment();
        Bundle b1 = new Bundle();
        b1.putString("type", TYPE_in_room);
        f1.setArguments(b1);
        fgs.add(f1);

        DataFragment f2 = new DataFragment();
        Bundle b2 = new Bundle();
        b2.putString("type", TYPE_laundries);
        f2.setArguments(b2);
        fgs.add(f2);

        DataFragment f3 = new DataFragment();
        Bundle b3 = new Bundle();
        b3.putString("type", TYPE_towels);
        f3.setArguments(b3);
        fgs.add(f3);

        DataFragment f4 = new DataFragment();
        Bundle b4 = new Bundle();
        b4.putString("type", TYPE_bath);
        f4.setArguments(b4);
        fgs.add(f4);

        DataFragment f5 = new DataFragment();
        Bundle b5 = new Bundle();
        b5.putString("type", TYPE_drinks);
        f5.setArguments(b5);
        fgs.add(f5);


        FgAdapter adapter = new FgAdapter(getSupportFragmentManager(), fgs);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(fgs.size());
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setSelect(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        findView(R.id.next).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (map_selected == null || map_selected.size() < 1) {
                    showToast(getString(R.string.please_select_amen));
                    return;
                }
                ArrayList<DataBean> datas = new ArrayList<>();
                for (DataBean b : map_selected.values()) {
                    datas.add(b);
                }
                Intent i = new Intent(AmentiesAc.this, AmenitiesCheckoutAc.class);
                i.putExtra("selected_amen", datas);
                startActivity(i);
            }
        });
        bottom_title = findView(R.id.bottom_title);
        setTextToView(bottom_title, getString(R.string.Items_0));

        total_price = findView(R.id.total_price);
        setTextToView(total_price, fomcatMoney(0.00f));
    }

    private TextView bottom_title, total_price;

    private void setSelect(int i) {
        setDrawableBottom(pillow, R.mipmap.icon_point_trans);
        setDrawableBottom(in_room, R.mipmap.icon_point_trans);
        setDrawableBottom(laundries, R.mipmap.icon_point_trans);
        setDrawableBottom(towels, R.mipmap.icon_point_trans);
        setDrawableBottom(bath, R.mipmap.icon_point_trans);
        setDrawableBottom(drinks, R.mipmap.icon_point_trans);

        if (i == 1) {
            setDrawableBottom(in_room, R.mipmap.icon_point_red);
        } else if (i == 2) {
            setDrawableBottom(laundries, R.mipmap.icon_point_red);
        } else if (i == 3) {
            setDrawableBottom(towels, R.mipmap.icon_point_red);
        } else if (i == 4) {
            setDrawableBottom(bath, R.mipmap.icon_point_red);
        } else if (i == 5) {
            setDrawableBottom(drinks, R.mipmap.icon_point_red);
        } else {
            setDrawableBottom(pillow, R.mipmap.icon_point_red);
        }
    }

    @Override
    protected String getCommonTitle() {
        return getString(R.string.Amenties);
    }

    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);
        if (e.what == EVENT_AMEN_CHANGE) {
            DataBean bean = (DataBean)e.obj;
            if (bean == null) {
                return;
            }

            String id = bean.getId();

            if (map_selected == null) {
                map_selected = new HashMap<>();
            }
            map_selected.put(id, bean);

            List<Float> prices = new ArrayList<>();
            int count = 0;
            for (DataBean b : map_selected.values()) {
                count += b.getCount();
                prices.add(MoneyUtils.mul(b.getPrice(), b.getCount()));
            }
            float[] ps = new float[prices.size()];
            for (int i = 0; i < ps.length; i++) {
                ps[i] = prices.get(i);
            }
            setTextToView(bottom_title, count + " " + getString(R.string.Items));
            setTextToView(total_price, fomcatMoney(MoneyUtils.add(ps)));
        }
    }

    public final static int EVENT_AMEN_CHANGE = 1031;
    private Map<String, DataBean> map_selected;
}
