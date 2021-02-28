package com.cilys.linphoneforhotal.event;

public interface EventImpl {
    int CLOSE_APP = 1;
    int SYSTEM_TIMER = 2;   //系统计时器
    int CALL_STATE_CHANGED = 11;        //呼叫状态改变
    int REGISTION_STATE_CHANGED = 21;   //注册状态改变

    int TO_HOME = 31;       //回到Home页

    void onTrigger(Event event);
}
