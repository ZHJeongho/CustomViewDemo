package com.jeongho.newcustomview.view.zhima;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private int mMonthCount;
    private int mTextSize;

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
        mDottedLinePaint.setPathEffect(new DashPathEffect(new float[]{1, 2, 4, 8}, 1));
        mDottedLinePaint.setAntiAlias(true);
        mDottedLinePaint.setColor(Color.BLUE);
        mDottedLinePaint.setStyle(Paint.Style.STROKE);
        mDottedLinePaint.setStrokeWidth(10);

        mPolylinePaint = new Paint();
        mPolylinePaint.setStyle(Paint.Style.STROKE);
        mPolylinePaint.setAntiAlias(true);
        mPolylinePaint.setColor(Color.YELLOW);
        mPolylinePaint.setStrokeWidth(10);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.YELLOW);
        mTextPaint.setStrokeWidth(5);
        mTextPaint.setTextSize(mTextSize);
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
        mMonthCount = ta.getInteger(R.styleable.ZhiMaCoreCurve_month_count, 6) + 1;
        mTextSize = ta.getDimensionPixelSize(R.styleable.ZhiMaCoreCurve_text_size, 15);
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
        drawBottomLine(canvas);
        drawTopLine(canvas);
        drawMiddleLine(canvas);
        drawBottomMonthText(canvas);


    }

    private void drawBottomMonthText(Canvas canvas) {
        String text = "尼玛嗨";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();

        for (int i = 1; i < mMonthCount; i++){
            int tempX = i * getWidth() / mMonthCount;
            canvas.drawText(text, tempX - textWidth / 2 , (mMonthCount - 1) * getHeight() / mMonthCount + 30 + textHeight, mTextPaint);
        }

        text = "" + mMax;
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        textWidth = rect.width();
        textHeight = rect.height();
        canvas.drawText(text, getWidth() / mMonthCount - textWidth - 50, getHeight() / mMonthCount + textHeight / 2, mTextPaint);

        text = "" + mMin;
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        textWidth = rect.width();
        textHeight = rect.height();
        canvas.drawText(text, getWidth() / mMonthCount - textWidth - 50, getHeight() / 2 + textHeight / 2, mTextPaint);
    }

    private void drawMiddleLine(Canvas canvas) {
        canvas.drawLine(getWidth() / mMonthCount, getHeight() / 2, getWidth() - getWidth() / (mMonthCount * 3), getHeight() / 2, mDottedLinePaint);
    }

    private void drawTopLine(Canvas canvas) {
        canvas.drawLine(getWidth() / mMonthCount, getHeight() / mMonthCount, getWidth() - getWidth() / (mMonthCount * 3), getHeight() / mMonthCount, mDottedLinePaint);
    }

    private void drawBottomLine(Canvas canvas) {
        //画底部横线
        int bottomLineY = (mMonthCount - 1) * getHeight() / mMonthCount;
        canvas.drawLine(getWidth() / (mMonthCount * 3),
                bottomLineY,
                getWidth() - getWidth() / (mMonthCount * 3),
                bottomLineY,
                mDottedLinePaint);

        //画竖线
        for (int i = 1; i < mMonthCount; i++){
            int tempX = i * getWidth() / mMonthCount;
            canvas.drawLine(tempX,
                    bottomLineY,
                    tempX,
                    bottomLineY + 20,
                    mDottedLinePaint);
        }
    }


}
