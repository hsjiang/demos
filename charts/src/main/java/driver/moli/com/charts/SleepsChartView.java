package driver.moli.com.charts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by riven_chris on 2017/4/1.
 */

public class SleepsChartView extends View {
    private static final String TAG = SleepsChartView.class.getSimpleName();

    private Paint grayPaint = null;
    private Paint purplePaint = null;
    private Paint scalePaint = null;

    private int strokeWidth = 50;

    private int width;
    private int height;
    private float pivotX;
    private float pivotY;
    private int radius;
    private double radian = 3.14 / 180;
    private int scaleOffset = 10;
    private int scaleLength = 10;
    private int scaleWidth = 5;
    private int scaleRotateDegree = 30;
    RectF rectF;

    private Bitmap moonBitmap;
    private Bitmap sunBitmap;
    private float moonX;
    private float moonY;
    private float sunX;
    private float sunY;

    private float startAngle;
    private float sweepAngle;

    public SleepsChartView(Context context) {
        this(context, null);
    }

    public SleepsChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SleepsChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAngle(float startAngle, float sweepAngle) {
        if (startAngle == this.startAngle && sweepAngle == this.sweepAngle) {
            return;
        }
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        invalidate();
    }

    void init() {
        grayPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        grayPaint.setStyle(Paint.Style.STROKE);
        grayPaint.setColor(Color.parseColor("#00FF00"));
        grayPaint.setStrokeWidth(strokeWidth);

        scalePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        scalePaint.setStyle(Paint.Style.FILL);
        scalePaint.setColor(Color.parseColor("#00FF00"));
        scalePaint.setStrokeCap(Paint.Cap.ROUND);
        scalePaint.setStrokeWidth(scaleWidth);
    }


    private void initDefaults() {
        pivotX = (width) / 2;
        pivotY = (height) / 2;
        Log.d(TAG, "x: " + pivotX + " ,y: " + pivotY);

        radius = (width - strokeWidth) / 2;
        Log.d(TAG, "radius: " + radius);

        int offsetStroke = strokeWidth / 2;
        rectF = new RectF(offsetStroke, offsetStroke, width - offsetStroke, height - offsetStroke);
    }

    private void figureOut() {
        purplePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        purplePaint.setStyle(Paint.Style.STROKE);
        purplePaint.setColor(Color.parseColor("#6730A8"));
        purplePaint.setStrokeWidth(strokeWidth);
        purplePaint.setStrokeCap(Paint.Cap.ROUND);
        moonBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.home_moon_icon);
        sunBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.home_sun_icon);

        double mAngle = startAngle * radian;
        moonX = (float) (pivotX + radius * Math.cos(mAngle));
        moonY = (float) (pivotY + radius * Math.sin(mAngle));
        Log.d(TAG, "moonX: " + moonX + " ,moonY: " + moonY);

        double sAngle = (sweepAngle + startAngle) * radian;
        sunX = (float) (pivotX + radius * Math.cos(sAngle));
        sunY = (float) (pivotY + radius * Math.sin(sAngle));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getMeasuredWidth() <= 0 || getMeasuredHeight() <= 0)
            return;
        Log.d(TAG, "onDraw: ");
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        Log.d(TAG, "width: " + getMeasuredWidth() + " ,height: " + getMeasuredHeight());
        initDefaults();
        canvas.drawArc(rectF, 0, 360, false, grayPaint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        for (int i = 0; i < 12; i++) {
            int offset = strokeWidth + scaleOffset;
            canvas.drawLine(width / 2, offset, width / 2, offset + scaleLength, scalePaint);
            canvas.rotate(scaleRotateDegree, pivotX, pivotY);
        }
        canvas.restore();

        if (startAngle == 0.0f && sweepAngle == 0.0f)
            return;
        figureOut();
        canvas.drawArc(rectF, startAngle, sweepAngle, false, purplePaint);

        float mX = moonX - (moonBitmap.getWidth() / 2);
        float mY = (moonY - moonBitmap.getHeight() / 2);
        canvas.drawBitmap(moonBitmap, mX, mY, null);

        float sX = sunX - sunBitmap.getWidth() / 2;
        float sY = sunY - sunBitmap.getHeight() / 2;
        canvas.drawBitmap(sunBitmap, sX, sY, null);
    }
}
