package com.cilys.linphoneforhotal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cilys.linphoneforhotal.base.BaseLinphoneAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;


public class HomeAc extends BaseLinphoneAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_home;
    }

    @Override
    protected void initUI(){
        super.initUI();

        setBackgroundById(R.id.rl_head, R.mipmap.ic_home_head_bg);
        setBackgroundById(R.id.rl_head_weather, R.mipmap.ic_home_head_bg_weather);
        setBackgroundById(R.id.rl_home_promo, R.mipmap.ic_home_promo_bg_test);
        setBackgroundById(R.id.ll_bottom, R.mipmap.ic_home_bottom_bg);

        ImageView img_room_control = findView(R.id.img_room_control);

        ImageView img_tv_remote = findView(R.id.img_tv_remote);

        TextView tv_room_name = findView(R.id.tv_room_name);

        TextView tv_custom_name = findView(R.id.tv_custom_name);

        TextView tv_msg = findView(R.id.tv_msg);

        ImageView img_msg_notify = findView(R.id.img_msg_notify);

        TextView tv_location = findView(R.id.tv_location);

        TextView tv_time = findView(R.id.tv_time);

        TextView tv_weekday = findView(R.id.tv_weekday);

        ImageView img_weather = findView(R.id.img_weather);

        TextView tv_temp = findView(R.id.tv_temp);

        LinearLayout ll_donot_disturb = findView(R.id.ll_donot_disturb);

        LinearLayout ll_make_up_room = findView(R.id.ll_make_up_room);

        LinearLayout ll_service_menu = findView(R.id.ll_service_menu);

        ImageView img_phone = findView(R.id.img_phone);
        img_phone.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, CallNumberAc.class));
            }
        });

        ImageView img_service_call = findView(R.id.img_service_call);
        img_service_call.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, AccountAc.class));
            }
        });

        ImageView img_voice_mail = findView(R.id.img_voice_mail);
        img_voice_mail.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });
    }

    @Override
    protected void afterInit() {
        super.afterInit();
    }
}