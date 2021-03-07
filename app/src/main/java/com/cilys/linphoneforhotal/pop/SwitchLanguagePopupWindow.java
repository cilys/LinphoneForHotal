package com.cilys.linphoneforhotal.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.cilys.linphoneforhotal.R;
import com.cilys.linphoneforhotal.view.SingleClickListener;

import java.util.Locale;

public class SwitchLanguagePopupWindow {
    public final static String LANGUAGE_ENGLISH = "en";
    public final static String LANGUAGE_CHINESE = "zh_CN";
    public final static String LANGUAGE_JAPAN = "ja_JP";
    public final static String LANGUAGE_TH = "th_TH";

    public static void show(Context cx, View parentView, final ItemListener listener){
        View contentView= LayoutInflater.from(cx).inflate(R.layout.pop_switch_language, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                (int)cx.getResources().getDimension(R.dimen.pop_layout_width),
                (int)cx.getResources().getDimension(R.dimen.y300), true);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);

        View language_english = contentView.findViewById(R.id.language_english);
        language_english.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (listener != null) {
                    listener.onItemClick(Locale.UK);
                }
                popupWindow.dismiss();
            }
        });
        View language_chinese = contentView.findViewById(R.id.language_chinese);
        language_chinese.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (listener != null) {
                    listener.onItemClick(Locale.SIMPLIFIED_CHINESE);
                }
                popupWindow.dismiss();
            }
        });
        View language_janpanese = contentView.findViewById(R.id.language_jpan);
        language_janpanese.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (listener != null) {
                    listener.onItemClick(Locale.JAPAN);
                }
                popupWindow.dismiss();
            }
        });
        View language_th = contentView.findViewById(R.id.language_th);
        language_th.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (listener != null) {
                    listener.onItemClick(new Locale("th", "TH"));
                }
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(parentView, 0, 0);
    }

    public interface ItemListener {
        void onItemClick(Locale language);
    }
}