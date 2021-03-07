package com.cilys.linphoneforhotal.ui.menu;

import com.cilys.linphoneforhotal.base.CommonTitleAc;
import com.cilys.linphoneforhotal.event.Event;

public abstract class ServiceParentAc extends CommonTitleAc {
    public final static int EVENT_CONFIRM_SELECT = 1051;

    @Override
    protected void onEvent(Event e) {
        super.onEvent(e);
        if (e.what == EVENT_CONFIRM_SELECT) {
            if (this instanceof ServiceMenuAc) {

            } else {
                finish();
            }
        }
    }
}
