package com.cilys.linphoneforhotal.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cilys.linphoneforhotal.R;

public abstract class BaseDialog {
    private final String TAG = getClass().getSimpleName();
    protected Activity ac;
    private Dialog dialog;

    public BaseDialog(Activity ac) {
        if (ac == null || ac.isFinishing()) {
            return;
        }
        this.ac = ac;

        dialog = new Dialog(ac, R.style.CommonDialogStyle);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = getScreenPix(ac, 1);
            params.height = (int)(heightScale() * getScreenPix(ac, 2));
            window.setGravity(Gravity.BOTTOM);
            params.x = 0;
            params.y = 0;
            window.setAttributes(params);
        }
        View rootView = View.inflate(ac, layoutId(), null);
        initUI(rootView);
        dialog.setContentView(rootView);
    }

    protected float heightScale(){
        return 0.5f;
    }

    protected void initUI(View rootView) {

    }

    protected abstract  @LayoutRes int layoutId();

    protected int getScreenPix(Activity ac, int mode){
        if (ac == null) {
            return 0;
        }
        DisplayMetrics dm = ac.getResources().getDisplayMetrics();
        if (mode == 2) {
            return dm.heightPixels;
        }
        return dm.widthPixels;
    }

    public void show(){
        if (dialog != null) {
            if (dialog.isShowing()) {
                return;
            }
            dialog.show();
        }
    }

    public void dismiss(){
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
