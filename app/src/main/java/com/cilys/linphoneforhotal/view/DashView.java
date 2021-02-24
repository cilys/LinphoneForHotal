package com.cilys.linphoneforhotal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cilys.linphoneforhotal.R;

/**
 * 仪表盘
 * 温度、湿度
 */
public class DashView extends View {
    private final float arc_width;  //外层圆环宽度
    private final int arc_dark_color,   //圆环深色
            arc_light_color,    //圆环浅色
            arc_point_center_color,     //指示点中间的颜色
            arc_point_ring_color,       //指示点四周的颜色
            arc_point_out_ring_color    //指示点外围的颜色
                    ;
    private final float arc_point_radius;//指示点的半径
    private final float arc_point_ring_interval = 0.2f; //指示点外环多余的倍数

    private final float interval;       //外层圆环和内圆之间的距离
    private final float center_circle_radius;   //内圆半径
    private final int center_circle_color;    //内圆颜色

    private final int backgroundColor;          //背景颜色

    private final float default_width, default_height;  //View默认宽高

    private final float center_text_size;

    public DashView(Context context) {
        this(context, null);
    }

    public DashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final int default_size = 20;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.dashview);

        arc_width = ta.getDimensionPixelSize(R.styleable.dashview_arcWidth, default_size);
        arc_point_radius = 1.0f * arc_width;

        interval = 1.4f * arc_width;

        center_circle_radius = 6.4f * arc_width;

        default_width = arc_width * 2.0f + interval * 2.0f + center_circle_radius * 2.0f;
        default_height = default_width;

        arc_dark_color = ta.getColor(R.styleable.dashview_arcDarkColor, context.getResources().getColor(R.color.color_main_text_color));
        arc_light_color = ta.getColor(R.styleable.dashview_arcLightColor, context.getResources().getColor(R.color.color_eee));
        arc_point_center_color = ta.getColor(R.styleable.dashview_arcPointCenterColor, context.getResources().getColor(R.color.color_ea5b5b));
        arc_point_ring_color = ta.getColor(R.styleable.dashview_arcPointRingColor, context.getResources().getColor(R.color.white));
        arc_point_out_ring_color = ta.getColor(R.styleable.dashview_arcPointRingColor, context.getResources().getColor(R.color.color_f9d0d0));

        center_circle_color = ta.getColor(R.styleable.dashview_centerCircleColor, context.getResources().getColor(R.color.color_f0f0f0));

        backgroundColor = ta.getColor(R.styleable.dashview_dashviewBackgroundColor, context.getResources().getColor(R.color.white));

        center_text_size = ta.getDimensionPixelSize(R.styleable.dashview_dashviewCenterTextSize, (int)(arc_width * 4));

        ta.recycle();
    }

    private Paint paint;
    private void resetPaint(){
        if (paint == null) {
            paint = new Paint();
        }
        paint.reset();
    }

    private float cx, cy;
    private int width, height;  //view的宽、高

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //宽的大小和测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //高的大小和测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 当模式是AT_MOST（即wrap_content）时设置默认值
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            width = (int)default_width;
            height = (int)default_height;

            // 宽 / 高任意一个模式为AT_MOST（即wrap_content）时，都设置默认值
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = (int)default_width;
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            width = widthSize;
            height = (int)default_height;
        } else {
            width = widthSize;
            height = heightSize;
        }

        width = (int)(width + arc_point_ring_interval * arc_point_radius + arc_point_radius + 10);
        height = (int)(height + arc_point_ring_interval * arc_point_radius + arc_point_radius + 10);

        cx = width / 2;
        cy = height / 2;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        resetView(canvas);
        drawCenterCircle(canvas);
        drawArc(canvas);
        drawCenterText(canvas);
    }

    private void resetView(Canvas canvas){
        resetPaint();

        paint.setColor(backgroundColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(0, 0, width, height, paint);
    }

    private void drawCenterCircle(Canvas canvas) {
        resetPaint();

        paint.setColor(center_circle_color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(cx, cy, center_circle_radius, paint);
    }

    private final int START_ANGLE = 135;
    private int sweepAngle = 0;
    private void drawArc(Canvas canvas) {
        resetPaint();

        paint.setColor(arc_light_color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(arc_width);
        paint.setStrokeCap(Paint.Cap.ROUND);

        float dis = arc_width;
        RectF f = new RectF(0 + dis / 2 + (arc_point_ring_interval * arc_point_radius + arc_point_radius + 10),
                0 + dis / 2 + (arc_point_ring_interval * arc_point_radius + arc_point_radius + 10),
                width - (arc_point_ring_interval * arc_point_radius + arc_point_radius + 10) - dis / 2,
                height - (arc_point_ring_interval * arc_point_radius + arc_point_radius + 10) - dis / 2);

        canvas.drawArc(f, 135, 270, false, paint);

        if (sweepAngle <= 0){
            return;
        }

        resetPaint();
        paint.setColor(arc_dark_color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(arc_width);
        paint.setStrokeCap(Paint.Cap.ROUND);



        canvas.drawArc(f, START_ANGLE, sweepAngle, false, paint);



        //角度（圆上的角度，即与右半边的夹角）
        int angle = START_ANGLE + sweepAngle;
        float radius = (f.right - f.left) / 2;
        float x = (float) (cx - radius * Math.sin(Math.toRadians(angle - 90)));
        float y = (float)(cy + radius * Math.cos(Math.toRadians(angle - 90)));

        resetPaint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(arc_point_out_ring_color);
        canvas.drawCircle(x, y , arc_point_radius + arc_point_ring_interval * arc_point_radius + 1, paint);

        resetPaint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(arc_point_ring_color);
        canvas.drawCircle(x, y, arc_point_radius + arc_point_ring_interval * arc_point_radius, paint);


        resetPaint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(arc_point_center_color);

        canvas.drawCircle(x, y, arc_point_radius, paint);
    }

    public void setCenterText(String text) {
        this.centerText = text;
    }

    private int lowScale = 0;
    private int highScale = 50;

    public void setLowScale(int lowScale) {
        this.lowScale = lowScale;
    }

    public void setHighScale(int highScale) {
        this.highScale = highScale;
    }

    private final int TOTAL_ANGLE = 360 - 90;

    public void setCurrentScale(float currentScale) {
        if (currentScale < lowScale) {
            currentScale = lowScale;
        }
        if (currentScale > highScale) {
            currentScale = highScale;
        }

        sweepAngle = (int)(TOTAL_ANGLE * currentScale / (highScale - lowScale));
    }

    private String centerText = "21℃";
    private void drawCenterText(Canvas canvas) {
        resetPaint();

        paint.setTextSize(center_text_size);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(arc_dark_color);
        paint.setAntiAlias(true);

        if (centerText == null || centerText.length() < 1) {
            return;
        }

        Rect r = new Rect();
        paint.getTextBounds(centerText, 0, centerText.length(), r);
        int w = r.left + r.right;
        int h = r.height();

        canvas.drawText(centerText, cx - w / 2, cy + h / 2, paint);
    }
}