package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by riven_chris on 15/4/1.
 */
public class CustomView extends View {

    private Paint paint = null;
    private int radius = 400;

    private ColorMatrix colorMatrix = new ColorMatrix(new float[]{
            0.2f, 0, 0, 0, 0,
            0, 0.2f, 0, 0, 0,
            0, 0, 0.2f, 0, 0,
            0, 0, 0, 0.5f, 0,
    });

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context) {
        super(context);
    }

    public void initPaint() {
        if (paint == null)
            paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#0066ff"));
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(720, 990, radius, paint);
    }

}
