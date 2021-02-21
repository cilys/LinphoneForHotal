package com.cilys.linphoneforhotal.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cilys.linphoneforhotal.AccountAc;
import com.cilys.linphoneforhotal.BuildConfig;
import com.cilys.linphoneforhotal.ui.call.CallNumberAc;
import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.BaseLinphoneAc;
import com.cilys.linphoneforhotal.service.LinphoneService;
import com.cilys.linphoneforhotal.ui.menu.ServiceMenuAc;
import com.cilys.linphoneforhotal.ui.msg.MsgAc;
import com.cilys.linphoneforhotal.ui.prom.PromDetailsAc;
import com.cilys.linphoneforhotal.ui.room.ControlAc;
import com.cilys.linphoneforhotal.ui.tv.TvRemoteAc;
import com.cilys.linphoneforhotal.utils.ImageUtils;
import com.cilys.linphoneforhotal.utils.ToastUtils;
import com.cilys.linphoneforhotal.view.SingleClickListener;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.BaseBannerInfo;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;


public class HomeAc extends BaseLinphoneAc {

    @Override
    protected int getLayout() {
        return R.layout.ac_home;
    }

    @Override
    protected void initUI(){
        super.initUI();

        if (com.cilys.linphoneforhotal.BuildConfig.DEBUG) {
//            startActivity(new Intent(this, TAc.class));
//            finish();
//            return;
        }

        Configuration cf= this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = cf.orientation ; //获取屏幕方向
        setBackground(ori);

        LinearLayout ll_room_control = findView(R.id.ll_room_control);
        ll_room_control.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, ControlAc.class));
            }
        });

        LinearLayout ll_tv_remote = findView(R.id.ll_tv_remote);
        ll_tv_remote.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, TvRemoteAc.class));
            }
        });

        TextView tv_room_name = findView(R.id.tv_room_name);

        TextView tv_custom_name = findView(R.id.tv_custom_name);

        TextView tv_msg = findView(R.id.tv_msg);
        tv_msg.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, MsgAc.class));
            }
        });

        ImageView img_msg_notify = findView(R.id.img_msg_notify);

        TextView tv_location = findView(R.id.tv_location);

        TextView tv_time = findView(R.id.tv_time);

        TextView tv_weekday = findView(R.id.tv_weekday);

        ImageView img_weather = findView(R.id.img_weather);

        TextView tv_temp = findView(R.id.tv_temp);

        LinearLayout ll_donot_disturb = findView(R.id.ll_donot_disturb);

        LinearLayout ll_make_up_room = findView(R.id.ll_make_up_room);

        LinearLayout ll_service_menu = findView(R.id.ll_service_menu);
        ll_service_menu.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, ServiceMenuAc.class));
            }
        });

        LinearLayout ll_phone = findView(R.id.ll_phone);
        ll_phone.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, CallNumberAc.class));
            }
        });

        LinearLayout ll_service_call = findView(R.id.ll_service_call);
        ll_service_call.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(HomeAc.this, AccountAc.class));
            }
        });

        LinearLayout ll_voice_mail = findView(R.id.ll_voice_mail);
        ll_voice_mail.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        XBanner banner = findView(R.id.xBanner);
        banner.setPageTransformer(Transformer.Default);

        List<BaseBannerInfo> ls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ls.add(new BannerInfo());
        }
        banner.setBannerData(R.layout.fg_home_prom, ls);
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView img = (ImageView)view.findViewById(R.id.img_pic);
                ImageUtils.load(HomeAc.this, R.mipmap.ic_promo_test, img);
            }
        });
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                startActivity(new Intent(HomeAc.this, PromDetailsAc.class));
            }
        });
    }

    @Override
    protected void afterInit() {
        super.afterInit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (BuildConfig.DEBUG) {
//            return;
        }

        requestCameraPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 11) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestRecordPermission();
            } else {
                ToastUtils.show(this, "相机权限被拒绝，暂无法使用app");
            }
        } else if (requestCode == 12) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestSipPermission();
            } else {
                ToastUtils.show(this, "录音权限被拒绝，暂无法使用app");
            }
        } else if (requestCode == 13) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                start();
            } else {
                ToastUtils.show(this, "电话被拒绝，暂无法使用app");
            }
        }
    }

    private void requestCameraPermission(){
        String p1 = Manifest.permission.CAMERA;

        if (ContextCompat.checkSelfPermission(this, p1)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{p1}, 11);
        } else {
            //已授权
            requestRecordPermission();
        }
    }

    private void requestRecordPermission(){
        String p2 = Manifest.permission.RECORD_AUDIO;

        if (ContextCompat.checkSelfPermission(this, p2)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{p2}, 12);
        } else {
            //已授权
            requestSipPermission();
        }
    }

    private void requestSipPermission(){
        String p3 = Manifest.permission.USE_SIP;


        if (ContextCompat.checkSelfPermission(this, p3)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{p3}, 13);
        } else {
            //已授权
            start();
        }
    }

    private void start(){
        if (LinphoneService.isReady()) {
            onServiceReady();
        } else {
            startService(new Intent(this, LinphoneService.class));

            final Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!LinphoneService.isReady()) {
                        mHandler.postDelayed(this, 30);
                    } else {
                        onServiceReady();
                    }
                }
            }, 30);
        }
    }

    private void onServiceReady(){

    }

    private void setBackground(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setBackgroundById(R.id.ll_bottom, R.mipmap.ic_home_bottom_bg_land);
            setBackgroundById(R.id.rl_head, R.mipmap.ic_home_head_bg_land);
            setBackgroundById(R.id.rl_head_weather, R.mipmap.ic_home_head_bg_weather_land);
        } else {
            setBackgroundById(R.id.ll_bottom, R.mipmap.ic_home_bottom_bg);
            setBackgroundById(R.id.rl_head, R.mipmap.ic_home_head_bg);
            setBackgroundById(R.id.rl_head_weather, R.mipmap.ic_home_head_bg_weather);
        }
    }
}