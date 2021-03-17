package com.cilys.linphoneforhotal.event;

public interface EventImpl {
    int CLOSE_APP = 1;
    int SYSTEM_TIMER = 2;   //系统计时器
    int TO_HOME = 11;       //回到Home页

    int CALL_STATE_CHANGED = 121;        //呼叫状态改变
    int REGISTION_STATE_CHANGED = 131;   //注册状态改变
    int RECEIVE_NOTIFY = 141;               //收到了通知消息

    void onTrigger(Event event);
}
