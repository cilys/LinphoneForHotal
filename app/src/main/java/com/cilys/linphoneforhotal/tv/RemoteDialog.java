package com.cilys.linphoneforhotal.tv;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.cilys.linphoneforhotal.R;

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
            params.height = (int)(0.75f) * getScreenPix(ac, 2);
            params.x = 0;
            params.y = 0;
            window.setAttributes(params);
        }
        dialog.setContentView(R.layout.dialog_remote);
    }

    private int getScreenPix(Activity ac, int mode){
        if (ac == null) {
            return 0;
        }
        DisplayMetrics dm = ac.getResources().getDisplayMetrics();
        if (mode == 2) {
            return dm.heightPixels;
        }
        return dm.widthPixels;
    }



}
