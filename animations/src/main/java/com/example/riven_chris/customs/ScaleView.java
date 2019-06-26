package com.example.riven_chris.customs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by riven_chris on 16/8/8.
 */
public class ScaleView extends View {

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;

    private float radius1 = 0;
    private float radius2 = 0;
    private float radius3 = 0;

    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.parseColor("#998B49F6"));
        paint1.setStyle(Paint.Style.FILL);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.parseColor("#778B49F6"));
        paint2.setStyle(Paint.Style.FILL);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setColor(Color.parseColor("#558B49F6"));
        paint3.setStyle(Paint.Style.FILL);
    }

    public void setRadius(float radius) {
        radius1 = radius / 4f;
        radius2 = radius / 2f;
        radius3 = radius;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(0, 0, radius3, paint3);
        canvas.drawCircle(0, 0, radius2, paint2);
        canvas.drawCircle(0, 0, radius1, paint1);
    }
}
