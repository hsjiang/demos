package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.riven_chris.myapplication.R;

/**
 * Created by riven_chris on 15/4/2.
 */
public class CustomLightingColorView extends View {

    private Paint paint = null;
    private Bitmap bitmap = null;
    private Context context = null;

    public CustomLightingColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
        initRes();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        paint.setColorFilter(new LightingColorFilter(0xffffffff, 0x00770000));
                        break;
                    case MotionEvent.ACTION_UP:
                        paint.setColorFilter(null);
                        break;
                }
                invalidate();

                return true;
            }
        });
    }

    public CustomLightingColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomLightingColorView(Context context) {
        super(context);
    }

    public void initPaint() {
        if (paint == null)
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new LightingColorFilter(0xffffffff, 0x00000000));
    }

    public void initRes() {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.btn);
//        bitmap.setHeight(200);
//        bitmap.setWidth(120);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}
