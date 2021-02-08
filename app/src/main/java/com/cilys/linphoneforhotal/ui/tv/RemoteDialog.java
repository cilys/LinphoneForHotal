package com.cilys.linphoneforhotal.ui.tv;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.utils.ImageUtils;

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
            params.height = (int)(0.5 * getScreenPix(ac, 2));
            window.setGravity(Gravity.BOTTOM);
            params.x = 0;
            params.y = 0;
            window.setAttributes(params);
        }
        View rootView = View.inflate(ac, R.layout.dialog_remote, null);
        ImageUtils.load(ac, R.mipmap.ic_remote_test, (rootView.findViewById(R.id.root)));

        dialog.setContentView(rootView);
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

    public void show(){
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dismiss(){
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
