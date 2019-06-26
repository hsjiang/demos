package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.riven_chris.myapplication.R;

import Constant.Constant;

/**
 * Created by riven_chris on 15/4/25.
 */
public class CustomShaderView extends View {
    private Paint paint = null;
    private Bitmap bitmap = null;
    private float left, top, right, bottom;// 矩形坐上右下坐标
    private static final int RECT_SIZE = 400;// 矩形尺寸的一半
    private Paint fillPaint = null;
    private float eventX, eventY;


    public CustomShaderView(Context context) {
        this(context, null);
    }

    public CustomShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        left = Constant.screenW / 2 - RECT_SIZE;
        top = Constant.screenH / 2 - RECT_SIZE;
        right = Constant.screenW / 2 + RECT_SIZE;
        bottom = Constant.screenH / 2 + RECT_SIZE;

        if (paint == null)
            paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        if (fillPaint == null)
            fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);


        if (bitmap == null)
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.four);

        fillPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                eventX = event.getX();
                eventY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                eventX = event.getX();
                eventY = event.getY();
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect(0, 0, Constant.screenW, Constant.screenH, fillPaint);
        canvas.drawColor(Color.DKGRAY);
        canvas.drawCircle(eventX, eventY, 100, fillPaint);
        canvas.drawCircle(eventX, eventY, 100, paint);
    }

}
