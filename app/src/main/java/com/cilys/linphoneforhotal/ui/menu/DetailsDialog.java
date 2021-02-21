package com.cilys.linphoneforhotal.ui.menu;

import android.app.Activity;
import android.view.View;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.base.dialog.BaseDialog;

public class DetailsDialog extends BaseDialog {
    public DetailsDialog(Activity ac) {
        super(ac);
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog_service_menu_details;
    }

    @Override
    protected void initUI(View rootView) {
        super.initUI(rootView);

    }
}
