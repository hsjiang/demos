package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.riven_chris.myapplication.R;

import Constant.Constant;

/**
 * Created by riven_chris on 15/4/22.
 */
public class CustomMaskFilterView extends View {
    private Paint paint = null;
    private Bitmap bitmap, shadowBitmap;
    private float x, y;

    public CustomMaskFilterView(Context context) {
        this(context, null);
    }

    public CustomMaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB)
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        if (paint == null)
            paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#ff6600"));
//        paint.setShadowLayer(5, 20, 20, Color.DKGRAY);

        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.four);
//        shadowBitmap = bitmap.extractAlpha();
        shadowBitmap = bitmap;
        x = Constant.screenW / 2 - bitmap.getWidth() / 2;
        y = Constant.screenH / 2 - bitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.GRAY);
//        canvas.drawCircle(Constant.screenW / 2, Constant.screenH / 2, 400, paint);
        canvas.drawBitmap(shadowBitmap, x, y, paint);
        canvas.drawBitmap(bitmap, x, y, null);

        //for setShadowLayer
//        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void setBlurMaskFilter() {
        paint.setMaskFilter(new BlurMaskFilter(40, BlurMaskFilter.Blur.OUTER));
        invalidate();
    }

    public void setEmbossMaskFilter() {
//        paint.setMaskFilter(new BlurMaskFilter(20, EmbossMaskFilter.));
        invalidate();
    }
}
