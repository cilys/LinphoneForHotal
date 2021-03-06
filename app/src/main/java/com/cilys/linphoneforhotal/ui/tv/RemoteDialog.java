package com.cilys.linphoneforhotal.ui.tv;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.view.RemoteView;
import com.cilys.linphoneforhotal.view.SingleClickListener;

public class RemoteDialog {
    private final String TAG = getClass().getSimpleName();
    private Activity ac;
    private Dialog dialog;

    public RemoteDialog(Activity ac) {
        if (ac == null || ac.isFinishing()) {
            return;
        }
        this.ac = ac;

        dialog = new Dialog(ac, R.style.CommonDialogStyle);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = getScreenPix(ac, 1);
            params.height = (int) (0.5 * getScreenPix(ac, 2));
            window.setGravity(Gravity.BOTTOM);
            params.x = 0;
            params.y = 0;
            window.setAttributes(params);
        }
        View rootView = View.inflate(ac, R.layout.dialog_remote, null);

        View top_title = rootView.findViewById(R.id.top_title);
        if (top_title != null) {
            top_title.setOnClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    dismiss();
                }
            });
        }

        final View power_switch = rootView.findViewById(R.id.power_switch);
        power_switch.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_POWER);
            }
        });
        rootView.findViewById(R.id.channel_add).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_CHANNEL_ADD);
            }
        });
        rootView.findViewById(R.id.channel_reduce).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_CHANNEL_REDUCE);
            }
        });

        RemoteView remoteView = (RemoteView) rootView.findViewById(R.id.remoteView);
        remoteView.setOnClickListener(new RemoteView.OnClickListener() {
            @Override
            public void onClick(int type) {
                click(type);
            }
        });

        rootView.findViewById(R.id.vol_add).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_VOL_ADD);
            }
        });
        rootView.findViewById(R.id.vol_reduce).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_VOL_REDUCE);
            }
        });


        rootView.findViewById(R.id.action_mute).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_ACTION_MUTE);
            }
        });
        rootView.findViewById(R.id.action_home).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_ACTION_HOME);
            }
        });
        rootView.findViewById(R.id.action_return).setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                click(TYPE_ACTION_RETURN);
            }
        });

        dialog.setContentView(rootView);
    }

    public static int getScreenPix(Activity ac, int mode) {
        if (ac == null) {
            return 0;
        }
        DisplayMetrics dm = ac.getResources().getDisplayMetrics();
        if (mode == 2) {
            return dm.heightPixels;
        }
        return dm.widthPixels;
    }

    public void show() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                return;
            }
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public boolean isShowing(){
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    private void click(int type) {
        if (listener != null) {
            listener.onClick(type);
        }
    }

    public Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public final static int TYPE_POWER = 11;
    public final static int TYPE_CHANNEL_ADD = 12;
    public final static int TYPE_CHANNEL_REDUCE = 13;

    public final static int TYPE_VOL_ADD = 21;
    public final static int TYPE_VOL_REDUCE = 22;

    public final static int TYPE_ACTION_MUTE = 31;
    public final static int TYPE_ACTION_HOME = 32;
    public final static int TYPE_ACTION_RETURN = 33;

    public interface Listener {
        void onClick(int type);
    }
}