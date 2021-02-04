package com.cilys.linphoneforhotal;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import com.cilys.linphoneforhotal.base.BaseLinphoneAc;

import com.cilys.linphoneforhotal.call.PhoneAc;
import com.cilys.linphoneforhotal.view.SingleClickListener;


/**
 * 拨号界面
 */
public class CallNumberAc extends BaseLinphoneAc {
    private final int TIME_INVAL = 10;
    private TextView tv_show_number;

    @Override
    protected int getLayout() {
        return R.layout.ac_call_number;
    }

    @Override
    protected void initUI(){
        super.initUI();

        tv_show_number = findView(R.id.tv_show_number);

        findView(R.id.ll_number_1).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("1");
            }
        });
        findView(R.id.ll_number_2).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("2");
            }
        });
        findView(R.id.ll_number_3).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("3");
            }
        });
        findView(R.id.ll_number_4).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("4");
            }
        });
        findView(R.id.ll_number_5).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("5");
            }
        });
        findView(R.id.ll_number_6).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("6");
            }
        });
        findView(R.id.ll_number_7).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("7");
            }
        });
        findView(R.id.ll_number_8).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("8");
            }
        });
        findView(R.id.ll_number_9).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("9");
            }
        });
        findView(R.id.ll_number_0).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("0");
            }
        });

        findView(R.id.ll_number_star).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("*");
            }
        });

        findView(R.id.ll_number_shape).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                tv_show_number.append("#");
            }
        });

        findView(R.id.ll_del).setOnClickListener(new SingleClickListener(TIME_INVAL) {
            @Override
            public void onSingleClick(View v) {
                String s = tv_show_number.getText().toString();
                if (s != null && s.length() > 0) {
                    s = s.substring(0, s.length() - 1);
                    tv_show_number.setText(s);
                }
            }
        });
        findView(R.id.img_call).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                String phone = tv_show_number.getText().toString().trim();

                if (phone == null || phone.length() < 1) {
                    showToast("请输入号码");
                    return;
                }

                if (getLinphoneConfig() == null) {
                    showToast("请配置账户信息");
                    startActivity(new Intent(CallNumberAc.this, AccountAc.class));

                    return;
                }

                Intent i = new Intent(CallNumberAc.this, PhoneAc.class);
                i.putExtra(INTENT_CALL_NUMBER, phone);
                i.putExtra("SHOW_TYPE", PhoneAc.SHOW_TYPE_OUT);

                startActivity(i);
            }
        });

        findView(R.id.ll_voice_mail).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });
        findView(R.id.ll_service_call).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });
        findView(R.id.ll_sos).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
        });

        findView(R.id.ll_close).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        App.getInstance().setTypeLastActivity(App.TYPE_LAST_AC_DEFAULT);
    }

    @Override
    protected void screenProtrait() {
        super.screenProtrait();

        setBackgroundById(R.id.root, R.mipmap.ic_call_number_bg_dark);
    }

    @Override
    protected void screenLand() {
        super.screenLand();

        setBackgroundById(R.id.root, R.mipmap.ic_call_number_bg_dark_land);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (tv_show_number != null) {
            outState.putString("SHOW_CALL_NUMBER", tv_show_number.getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String show_call_number = savedInstanceState.getString("SHOW_CALL_NUMBER", "");
            setTextToView(tv_show_number, show_call_number);
        }
    }
}