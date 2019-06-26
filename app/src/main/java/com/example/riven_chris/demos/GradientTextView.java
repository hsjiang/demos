package com.example.riven_chris.demos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;

/**
 * Created by riven_chris on 2017/8/25.
 */
public class GradientTextView extends View {

    private Paint mPaint;
    private Paint mBgPaint;
    private float mTextHeight;
    private float mTextWidth;
    private PorterDuffXfermode xformode;
    private Bitmap srcBitmap = null;
    private Canvas bitmapCanvas = null;
    private RectF rectF = null;
    private float offset;

    private String mText = null;
    private boolean mDuringCountDown = false;

    @ColorInt
    private int srcColor = Color.WHITE;

    @ColorInt
    private int destColor = Color.BLUE;

    public GradientTextView(Context ctx) {
        this(ctx, null);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setPadding(16, 20, 16, 20);
        setBackgroundColor(srcColor);

        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        rectF = new RectF(0, 0, offset, 0);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(destColor);
        mPaint.setTextSize(40.0f);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setXfermode(null);
        mPaint.setTextAlign(Paint.Align.LEFT);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(destColor);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mTextHeight = fontMetrics.descent - fontMetrics.ascent;
    }

    public void onTime(float percent, String text, boolean duringCountDown) {
        mText = text;
        mTextWidth = mPaint.measureText(mText);
        offset = getMeasuredWidth() * percent;
        mDuringCountDown = duringCountDown;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthWithPadding = (int) (mTextWidth + getPaddingLeft() + getPaddingRight());

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = Math.min(widthWithPadding, widthSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                width = widthWithPadding;
                break;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightWithPadding = (int) (mTextHeight + getPaddingTop() + getPaddingBottom());

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = Math.min(heightWithPadding, heightSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                height = heightWithPadding;
                break;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            srcBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmapCanvas = new Canvas(srcBitmap);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight <= 0 || getMeasuredWidth() <= 0 || TextUtils.isEmpty(mText))
            return;

        if (mDuringCountDown)
            canvas.drawRect(0, 0, offset, measuredHeight, mBgPaint);

        canvas.save();
        bitmapCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        bitmapCanvas.drawText(mText, getPaddingLeft(), mTextHeight + getPaddingTop() - mPaint.descent(), mPaint);
        if (mDuringCountDown) {
            mPaint.setXfermode(xformode);
            mPaint.setColor(Color.WHITE);
            rectF.bottom = measuredHeight;
            rectF.right = offset;
            bitmapCanvas.drawRect(rectF, mPaint);
            mPaint.setColor(destColor);
            mPaint.setXfermode(null);
        }

        canvas.drawBitmap(srcBitmap, 0, 0, null);
        canvas.restore();
    }
}
