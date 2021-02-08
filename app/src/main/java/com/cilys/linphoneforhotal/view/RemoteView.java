package com.cilys.linphoneforhotal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cilys.linphoneforhotal.R;

public class RemoteView extends View {
    private final int WIDTH, HEIGHT;
    private int background, foreground,
            topArrowColor, rightArrowColor,
            bottomArrowColor, leftArrowColor,
            textColor, textSize, rectColor;

    private Paint mPaint;

    public RemoteView(Context context) {
        this(context, null);
    }

    public RemoteView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.remoteview);

        WIDTH = ta.getInt(R.styleable.remoteview_view_width, 100);
        HEIGHT = ta.getInt(R.styleable.remoteview_view_height, 100);

        background = ta.getColor(R.styleable.remoteview_backgroundColor, Color.TRANSPARENT);
        foreground = ta.getColor(R.styleable.remoteview_foregroundColor, context.getResources().getColor(R.color.color_303040));

        topArrowColor = ta.getColor(R.styleable.remoteview_topArrowColor, context.getResources().getColor(R.color.white));
        rightArrowColor = ta.getColor(R.styleable.remoteview_rightArrowColor, context.getResources().getColor(R.color.white));
        bottomArrowColor = ta.getColor(R.styleable.remoteview_bottomArrowColor, context.getResources().getColor(R.color.white));
        leftArrowColor = ta.getColor(R.styleable.remoteview_leftArrowColor, context.getResources().getColor(R.color.white));

        textColor = ta.getColor(R.styleable.remoteview_centerTextColor, context.getResources().getColor(R.color.white));

        rectColor = ta.getColor(R.styleable.remoteview_rectColor, context.getResources().getColor(R.color.white));

        textSize = ta.getDimensionPixelSize(R.styleable.remoteview_centerTextSize, 25);

        ta.recycle();

        mPaint = new Paint();
    }


}
