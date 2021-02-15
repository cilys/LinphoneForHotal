package com.cilys.linphoneforhotal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cilys.linphoneforhotal.R;

public class RemoteView extends View {
    private final int WIDTH, HEIGHT;
    private int background, foreground,
            topArrowColor, rightArrowColor,
            bottomArrowColor, leftArrowColor,
            textColor, textSize, rectColor;
    private int touchForegroundColor, touchArrowColor;

    private int width, height;  //view的宽、高
    private float radius;         //圆的半径
    private float cx, cy;       //圆心坐标


    private final int AREA_DEFAULT = -1;
    private final int AREA_TOP = 3;
    private final int AREA_LEFT = 2;
    private final int AREA_BOTTOM = 1;
    private final int AREA_RIGHT = 0;


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

        WIDTH = ta.getDimensionPixelSize(R.styleable.remoteview_view_width, 100);
        HEIGHT = ta.getDimensionPixelSize(R.styleable.remoteview_view_height, 100);

        background = ta.getColor(R.styleable.remoteview_backgroundColor, Color.TRANSPARENT);
        foreground = ta.getColor(R.styleable.remoteview_foregroundColor, context.getResources().getColor(R.color.color_303040));
        touchForegroundColor = ta.getColor(R.styleable.remoteview_touchForegroundColor, context.getResources().getColor(R.color.color_303040));


        topArrowColor = ta.getColor(R.styleable.remoteview_topArrowColor, context.getResources().getColor(R.color.white));
        rightArrowColor = ta.getColor(R.styleable.remoteview_rightArrowColor, context.getResources().getColor(R.color.white));
        bottomArrowColor = ta.getColor(R.styleable.remoteview_bottomArrowColor, context.getResources().getColor(R.color.white));
        leftArrowColor = ta.getColor(R.styleable.remoteview_leftArrowColor, context.getResources().getColor(R.color.white));

        touchArrowColor = ta.getColor(R.styleable.remoteview_touchArrowColor, context.getResources().getColor(R.color.white));


        textColor = ta.getColor(R.styleable.remoteview_centerTextColor, context.getResources().getColor(R.color.white));

        rectColor = ta.getColor(R.styleable.remoteview_rectColor, context.getResources().getColor(R.color.white));

        textSize = ta.getDimensionPixelSize(R.styleable.remoteview_centerTextSize, 25);

        ta.recycle();

        resetPaint();
    }

    private void resetPaint(){
        if (mPaint == null) {
            mPaint = new Paint();
        }
        mPaint.reset();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //宽的大小和测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //高的大小和测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //设置wrap_content的默认宽高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看

        int mWidth = WIDTH;
        int mHeight = HEIGHT;

        // 当模式是AT_MOST（即wrap_content）时设置默认值
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            width = mWidth;
            height = mHeight;

            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个模式为AT_MOST（即wrap_content）时，都设置默认值
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = mWidth;
            height = heightSize;

            setMeasuredDimension(mWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            width = widthSize;
            height = mHeight;

            setMeasuredDimension(widthSize, mHeight);
        } else {
            width = widthSize;
            height = heightSize;

            setMeasuredDimension(widthSize, heightSize);
        }

        cx = width / 2;
        cy = height / 2;
        radius = Math.min(cx, cy);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawCircle(canvas);

        drawArrow(canvas);

//        drawCenter(canvas);
        drawCenterRect(canvas);
        drawCenterText(canvas);
    }

    private void drawBackground(Canvas canvas){
        resetPaint();
        mPaint.setColor(background);
        mPaint.setStyle(Paint.Style.FILL);


        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    private int touchArea = AREA_DEFAULT;//按压的区域

    private void drawCircle(Canvas canvas){
        resetPaint();
        mPaint.setColor(foreground);
        mPaint.setStyle(Paint.Style.FILL);

//        canvas.drawCircle(x, y, radius, mPaint);

        RectF rectF = new RectF(cx - radius, cy - radius, cx + radius, cy + radius);
        float start = -45;

        for (int i = 0; i < 4; i++) {
            if (i == touchArea) {
                resetPaint();
                mPaint.setColor(touchForegroundColor);
                mPaint.setStyle(Paint.Style.FILL);
            } else {
                resetPaint();
                mPaint.setColor(foreground);
                mPaint.setStyle(Paint.Style.FILL);
            }
            canvas.drawArc(rectF, start, 90, true, mPaint);
            start += 90;
        }
    }

    private void drawArrow(Canvas canvas) {
        final float arrowLineSize = Math.max(radius / 10 , 10);

        final float marginCircle = radius / 5;


        resetPaint();
        if (touchArea == AREA_TOP){
            mPaint.setColor(touchArrowColor);
        } else {
            mPaint.setColor(topArrowColor);
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        //上箭头顶点x坐标、y坐标
        float topPointY = cy - radius + marginCircle;
        float topPointX = cx;
        canvas.drawLine(topPointX - arrowLineSize, topPointY + arrowLineSize, topPointX, topPointY, mPaint);
        canvas.drawLine(topPointX, topPointY, topPointX + arrowLineSize, topPointY + arrowLineSize, mPaint);

        resetPaint();
        if (touchArea == AREA_RIGHT){
            mPaint.setColor(touchArrowColor);
        } else {
            mPaint.setColor(rightArrowColor);
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        //右箭头
        float rightPointX = cx + radius - marginCircle;
        float rightPointY = cy;
        canvas.drawLine(rightPointX - arrowLineSize, rightPointY - arrowLineSize, rightPointX, rightPointY, mPaint);
        canvas.drawLine(rightPointX - arrowLineSize, rightPointY + arrowLineSize, rightPointX, rightPointY, mPaint);

        resetPaint();
        if (touchArea == AREA_BOTTOM){
            mPaint.setColor(touchArrowColor);
        } else {
            mPaint.setColor(bottomArrowColor);
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        //下箭头
        float bottomPointX = cx;
        float bottomPointY = cy + radius - marginCircle;
        canvas.drawLine(bottomPointX - arrowLineSize, bottomPointY - arrowLineSize, bottomPointX, bottomPointY, mPaint);
        canvas.drawLine(bottomPointX + arrowLineSize, bottomPointY - arrowLineSize, bottomPointX, bottomPointY, mPaint);

        resetPaint();
        if (touchArea == AREA_LEFT){
            mPaint.setColor(touchArrowColor);
        } else {
            mPaint.setColor(leftArrowColor);
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        //左箭头
        float leftPointX = cx - radius + marginCircle;
        float leftPointY = cy;
        canvas.drawLine(leftPointX, leftPointY, leftPointX + arrowLineSize, leftPointY - arrowLineSize, mPaint);
        canvas.drawLine(leftPointX, leftPointY, leftPointX + arrowLineSize, leftPointY + arrowLineSize, mPaint);
    }

    private final int STROKE_WIDTH = 2;

    private void drawCenter(Canvas canvas){
        resetPaint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setColor(rectColor);
        mPaint.setAntiAlias(true);

        final float disX = 50;
        final float disY = disX;
        final float radiusX = 20;
        final float radiusY = radiusX;

        //上
        canvas.drawLine(cx - disX + radiusX, cy - disY, cx + disX - radiusX, cy - disY, mPaint);
        //右
        canvas.drawLine(cx + disX, cy - disY + radiusY, cx + disX, cy + disY - radiusY, mPaint);
        //下
        canvas.drawLine(cx - disX + radiusX, cy + disY, cx + disX - radiusX, cy + disY, mPaint);
        //左
        canvas.drawLine(cx - disX, cy - disY + radiusY, cx - disX, cy + disY - radiusY, mPaint);

        resetPaint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setColor(rectColor);
        mPaint.setAntiAlias(true);
        //左上角
//        RectF lt = new RectF(cx - disX, cy - disY, cx -disX + radiusX, cy - disY + radiusY);
//        canvas.drawArc(lt, 0, 90, false, mPaint);
        Path lt = new Path();
        lt.moveTo(cx - disX, cy - disY + radiusY);
        lt.quadTo(cx - disX, cy -disY, cx - disX + radiusX, cy - disY);
        canvas.drawPath(lt, mPaint);
        //右上角
        Path rt = new Path();
        rt.moveTo(cx + disX, cy - disY + radiusY);
        rt.quadTo(cx + disX, cy -disY, cx + disX - radiusX, cy - disY);
        canvas.drawPath(rt, mPaint);

        //左下角
        Path lb = new Path();
        lb.moveTo(cx - disX, cy + disY - radiusY);
        lb.quadTo(cx - disX, cy + disY, cx - disX + radiusX, cy + disY);
        canvas.drawPath(lb, mPaint);

        //右下角
        Path rb = new Path();
        rb.moveTo(cx + disX, cy + disY - radiusY);
        rb.quadTo(cx + disX, cy + disY, cx + disX - radiusX, cy + disY);
        canvas.drawPath(rb, mPaint);
    }

    private final float CENTER_RECT_DIS_X = 50;
    private final float CENTER_RECT_DIS_Y = CENTER_RECT_DIS_X;

    private void drawCenterRect(Canvas canvas) {
        resetPaint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setColor(rectColor);
        mPaint.setAntiAlias(true);

        final float disX = CENTER_RECT_DIS_X;
        final float disY = CENTER_RECT_DIS_Y;
        final float radiusX = 20;
        final float radiusY = radiusX;


        RectF r = new RectF(cx - disX, cy - disY, cx + disX, cy + disY);
        canvas.drawRoundRect(r, radiusX, radiusY, mPaint);

        final float SIZE = 2;
        RectF c = new RectF(cx - disX + SIZE, cy - disY + SIZE, cx + disX - SIZE, cy + disY - SIZE);
        resetPaint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setColor(foreground);
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(c, radiusX, radiusY, mPaint);
    }

    private String centerText = "OK";

    private void drawCenterText(Canvas canvas){
        resetPaint();
        mPaint.setTextSize(textSize);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(textColor);
        mPaint.setAntiAlias(true);

        if (centerText == null || centerText.length() < 1) {
            return;
        }

        Rect r = new Rect();
        mPaint.getTextBounds(centerText, 0, centerText.length(), r);
        int w = r.left + r.right;
        int h = r.height();

        canvas.drawText(centerText, cx - w / 2, cy + h / 2, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //判断按压点在哪个区域内
            float x = event.getX();
            float y = event.getY();

            //如果在圆外，则不在任何一个区域
            if ((x - cx) > 5 || (y - cy) > 5) {

            } else if (pointInRect(x, y, cx - CENTER_RECT_DIS_X, cy - CENTER_RECT_DIS_Y,
                    cx + CENTER_RECT_DIS_X, cy + CENTER_RECT_DIS_Y)){
                //如果按压点在圆在中间的"Ok"按钮范围内，也不在任何一个区域内

            } else {
                //如果按压点在"ok"按钮范围外，在圆的范围内，则判断在哪个区域

                //圆心坐标：（x, y）
                //半径：r
                //角度：a
                //则圆上任一点的坐标（x1, y1）
                //x1 = x + r * cos(a)
                //y1 = y + r * sin(a)

                //-45度线的坐标
                double x1 = cx + radius * Math.cos(315);
                double y1 = cy + radius * Math.sin(315);


                //45度线的坐标
                double x2 = cx + radius * Math.cos(45);
                double y2 = cy + radius * Math.sin(45);

                //135度线的坐标
                double x3 = cx + radius * Math.cos(135);
                double y3 = cy + radius * Math.sin(135);

                //225度线的坐标
                double x4 = cx + radius * Math.cos(225);
                double y4 = cy + radius * Math.sin(225);


            }


        } else {
            touchArea = AREA_DEFAULT;
        }
        return super.onTouchEvent(event);
    }

    private boolean pointInRect(float x, float y, float left, float top, float right, float bottom){
        if (x - left < 0) {
            return false;
        }
        if (x - right > 0) {
            return false;
        }

        if (y - top < 0) {
            return false;
        }

        if (y - top > 0){
            return false;
        }

        return true;
    }
}
