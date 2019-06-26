package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.example.riven_chris.myapplication.R;

import Constant.Constant;

/**
 * Created by riven_chris on 15/4/29.
 */
public class CustomCanvasTransformView extends View {

    private Bitmap bitmap = null;
    private int x, y;

    public CustomCanvasTransformView(Context context) {
        super(context);
    }

    public CustomCanvasTransformView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.minv);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        x = w;
        y = h;

//        bitmap = Bitmap.createScaledBitmap(bitmap, x, y, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.scale(0.8F, 0.8F, x / 2, y / 2);
//        canvas.skew(-0.5f, 0f);
        Matrix matrix = new Matrix();
        matrix.setScale(0.8F, 0.35F);
        matrix.postTranslate(100, 100);
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
