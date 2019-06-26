package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by riven_chris on 15/4/28.
 */
public class CustomPathView extends View {

    private Paint paint = null;
    private Path path = null;
    private Paint textPaint = null;

    public CustomPathView(Context context) {
        this(context, null);
    }

    public CustomPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (paint == null)
            paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(5);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextSize(20);


        // 实例化路径
        path = new Path();
//
//        // 移动点至[100,100]
//        path.moveTo(100, 100);
//
//        // 连接路径到点
//        path.lineTo(200, 200);

        RectF oval = new RectF(100, 100, 300, 400);
        path.addOval(oval, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);

        canvas.drawTextOnPath("1112223334445555666777888", path, 0, 0, textPaint);
    }
}
