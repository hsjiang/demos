package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import Constant.Constant;

/**
 * Created by riven_chris on 15/4/29.
 */
public class CustomCanvasLayerView extends View {

    private Paint paint = null;
    private Paint paint2 = null;
    private Paint paint3 = null;
    private Paint paint4 = null;
    private float x, y;

    public CustomCanvasLayerView(Context context) {
        this(context, null);
    }

    public CustomCanvasLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.GREEN);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.BLUE);

        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint4.setStyle(Paint.Style.FILL);
        paint4.setColor(Color.YELLOW);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        x = w / 2;
        y = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(x - 200, y - 200, x + 200, y + 200, paint);


        int i1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(x - 150, y - 150, x + 150, y + 150);
        canvas.drawColor(Color.GREEN);
//        canvas.restoreToCount(i1);

        int i2 = canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate(5);
        canvas.drawRect(x - 100, y - 100, x + 100, y + 100, paint3);
        canvas.restoreToCount(i2);
        canvas.restoreToCount(i1);

        canvas.drawRect(x - 300, y - 300, x + 300, y + 300, paint4);

    }
}
