package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.riven_chris.myapplication.R;

/**
 * Created by riven_chris on 15/4/2.
 */
public class CustomPorterDuffView extends View {

    private Paint paint = null;
    private Bitmap src = null;
    private Context context = null;
    private PorterDuff.Mode mode = PorterDuff.Mode.CLEAR;
    private Paint circlePaint = null;

    public CustomPorterDuffView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
        initRes();
    }

    public CustomPorterDuffView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomPorterDuffView(Context context) {
        super(context);
    }

    public void initPaint() {
        if (paint == null)
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (circlePaint == null) {
            circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint.setColor(Color.parseColor("#00ffff"));
            circlePaint.setStyle(Paint.Style.FILL);
        }
    }

    public void initRes() {
        src = BitmapFactory.decodeResource(context.getResources(), R.mipmap.four);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int sc = canvas.saveLayer(0, 0, 600, 450, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(300, 225, 225, circlePaint);
//        canvas.drawColor(0xcc1c093e);
        paint.setXfermode(new PorterDuffXfermode(mode));
        canvas.drawBitmap(src, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(sc);
    }

    public void setPorterDuffMode(PorterDuff.Mode mode) {
        this.mode = mode;
        paint.setXfermode(null);
        invalidate();
    }
}
