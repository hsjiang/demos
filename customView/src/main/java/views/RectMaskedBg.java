package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by riven_chris on 15/11/4.
 */
public class RectMaskedBg extends ImageView {

    private Bitmap bitmap = null;
    private Paint paint = null;
    private Matrix matrix;
    private int width, height;
    private BitmapShader bitmapShader;
    private RectF rectF;
    private int radios = 20;
    private Paint maskPaint = null;
    private int color;

    public RectMaskedBg(Context context) {
        this(context, null);
    }

    public RectMaskedBg(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectMaskedBg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init() {
        //        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.user_icon);
        if (bitmap != null) {
            int bw = bitmap.getWidth();
            int bh = bitmap.getHeight();
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            matrix = new Matrix();
            int scale;
            if (bw <= bh) {
                scale = width / bw * 2;
            } else {
                scale = height / bh * 2;
            }
            matrix.setScale(scale, scale);
            int bx = (bw * scale - width) / 2;
            int by = (bh * scale - height) / 2;
            matrix.postTranslate(-bx, -by);
            rectF = new RectF(0, 0, width, height);
            bitmapShader.setLocalMatrix(matrix);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShader(bitmapShader);
        }
        if (maskPaint == null) {
            maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
//        maskPaint.setMaskFilter(new BlurMaskFilter(6, BlurMaskFilter.Blur.INNER));
        maskPaint.setColor(color);
        maskPaint.setAlpha(160);


        invalidate();
    }

    public void initResource(Bitmap bitmap, int color, int width, int height) {
        this.bitmap = bitmap;
        this.color = color;
        this.width = width;
        this.height = height;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width == 0 || height == 0)
            return;
        if (bitmap != null) {
            canvas.drawRoundRect(rectF, radios, radios, paint);
        }
        canvas.drawRoundRect(rectF, radios, radios, maskPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
