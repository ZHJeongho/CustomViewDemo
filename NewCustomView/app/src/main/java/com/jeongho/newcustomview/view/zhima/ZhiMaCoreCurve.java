package com.jeongho.newcustomview.view.zhima;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.jeongho.newcustomview.R;

/**
 * Created by Jeongho on 2016/12/26.
 */

public class ZhiMaCoreCurve extends View {

    private Paint mPolylinePaint;
    private Paint mTextPaint;
    private Paint mDottedLinePaint;
    private int mMax;
    private int mMin;
    private int[] mNumber;

    public ZhiMaCoreCurve(Context context) {
        this(context, null);
    }

    public ZhiMaCoreCurve(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZhiMaCoreCurve(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mDottedLinePaint = new Paint();
        mDottedLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 5, 5, 5}, 1));
        mDottedLinePaint.setAntiAlias(true);
        mDottedLinePaint.setColor(Color.BLUE);
        mDottedLinePaint.setStyle(Paint.Style.FILL);
        mDottedLinePaint.setStrokeWidth(2);

        mPolylinePaint = new Paint();
        mPolylinePaint.setStyle(Paint.Style.STROKE);
        mPolylinePaint.setAntiAlias(true);
        mPolylinePaint.setColor(Color.YELLOW);
        mPolylinePaint.setStrokeWidth(10);
    }

    /**
     * init config
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ZhiMaCoreCurve);
        mMax = ta.getInteger(R.styleable.ZhiMaCoreCurve_max, 700);
        mMin = ta.getInteger(R.styleable.ZhiMaCoreCurve_min, 650);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //根据MATCH_PARENT、WRAP_CONTENT分情况

        //setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        int width = getWidth();
        int height = getHeight();

        if (widthMode == MeasureSpec.EXACTLY){
            width = measureWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = measureHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画图
        Path path = new Path();
        path.moveTo(0, getHeight() / 2);
        path.lineTo(getWidth(), getHeight() / 2);

        path.moveTo(0, 0);
        path.lineTo(400, 400);
        //canvas.drawPath(path, mDottedLinePaint);
        //canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPolylinePaint);


        canvas.drawLine(0, 0, 200, 200, mPolylinePaint);
    }


}
