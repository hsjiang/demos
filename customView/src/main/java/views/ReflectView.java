package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.riven_chris.myapplication.R;

/**
 * Created by riven_chris on 15/10/24.
 */
public class ReflectView extends View {

    private Bitmap srcBitmap;
    private Bitmap refBitmap;
    private Paint paint;
    private PorterDuffXfermode mode;
    private int x, y;

    public ReflectView(Context context) {
        this(context, null);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRes();
    }

    private void initRes() {
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image);

        Matrix matrix = new Matrix();
        matrix.setScale(1f, -1f);
//        matrix.preScale(1f, -1f);

        refBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);

        paint = new Paint();
        paint.setShader(new LinearGradient(x, y + srcBitmap.getHeight(), x, y + srcBitmap.getHeight() + srcBitmap.getHeight() / 4, 0x60000000, 0x00000000, Shader.TileMode.CLAMP));
        mode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        paint.setXfermode(mode);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        int i = canvas.saveLayer(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight() * 2, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(refBitmap, 0, srcBitmap.getHeight(), null);
        canvas.drawRect(0, srcBitmap.getHeight(), srcBitmap.getWidth(), srcBitmap.getHeight() + srcBitmap.getHeight(), paint);
        canvas.restoreToCount(i);
    }
}
