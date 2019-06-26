package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.riven_chris.myapplication.R;

/**
 * Created by riven_chris on 15/4/2.
 */
public class CustomColorMatrixView extends View {

    private Paint paint = null;
    private Bitmap bitmap = null;
    private Context context = null;

    private ColorMatrix colorMatrix = new ColorMatrix(new float[]{
            1.5f, 0, 0, 0, 0,
            0, 1.5f, 0, 0, 0,
            0, 0, 1.5f, 0, 0,
            0, 0, 0, 1, 0,
    });

    public CustomColorMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
        initRes();
    }

    public CustomColorMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomColorMatrixView(Context context) {
        super(context);
    }

    public void initPaint() {
        if (paint == null)
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public void initRes() {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.four);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

}
