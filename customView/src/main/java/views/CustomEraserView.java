package views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Debug;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riven_chris.myapplication.R;

/**
 * Created by riven_chris on 15/4/3.
 */
public class CustomEraserView extends View {
    private static final int MIN_MOVE_DIS = 5;

    private Paint paint = null;
    //    private Bitmap bgBitmap = null;
    private Bitmap fgBitmap = null;
    private Context context = null;
    private int screenW = 0;
    private int screenH = 0;
    private Path path = null;
    private float preX = 0;
    private float preY = 0;
    private Canvas mCanvas = null;

    public CustomEraserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initRes();
        initPaint();
//        Choreographer
    }

    public CustomEraserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomEraserView(Context context) {
        super(context);
    }

    public void initPaint() {

        if (path == null)
            path = new Path();

        if (paint == null)
            paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        // 设置画笔透明度为0是关键！我们要让绘制的路径是透明的，然后让该路径与前景的底色混合“抠”出绘制路径
        paint.setARGB(0, 0, 0, 0);

        // 设置混合模式为DST_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        // 设置画笔风格为描边
        paint.setStyle(Paint.Style.STROKE);

        // 设置路径结合处样式
        paint.setStrokeJoin(Paint.Join.ROUND);

        // 设置笔触类型
        paint.setStrokeCap(Paint.Cap.ROUND);

        // 设置描边宽度
        paint.setStrokeWidth(40);

        // 生成前景图Bitmap
//        fgBitmap = Bitmap.createBitmap(screenW, screenH, Bitmap.Config.ARGB_8888);
        fgBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.image).copy(Bitmap.Config.ARGB_8888, true);
        fgBitmap.createScaledBitmap(fgBitmap, screenW, screenH, true);

        // 将其注入画布
        mCanvas = new Canvas(fgBitmap);
        // 绘制画布背景为中性灰
//        mCanvas.drawColor(0xFF808080);

//        bgBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.image);
//        bgBitmap.createScaledBitmap(bgBitmap, screenW, screenH, true);
    }

    public void initRes() {
        screenW = ((Activity) context).getResources().getDisplayMetrics().widthPixels;
        screenH = ((Activity) context).getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawBitmap(bgBitmap, 0, 0, null);
//        Debug.startMethodTracing("Eraser.trace");
        canvas.drawBitmap(fgBitmap, 0, 0, null);

        /*
         * 这里要注意canvas和mCanvas是两个不同的画布对象
         * 当我们在屏幕上移动手指绘制路径时会把路径通过mCanvas绘制到fgBitmap上
         * 每当我们手指移动一次均会将路径mPath作为目标图像绘制到mCanvas上，而在上面我们先在mCanvas上绘制了中性灰色
         * 两者会因为DST_IN模式的计算只显示中性灰，但是因为mPath的透明，计算生成的混合图像也会是透明的
         * 所以我们会得到“橡皮擦”的效果
         */
        mCanvas.drawPath(path, paint);
//        Debug.stopMethodTracing();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
         * 获取当前事件位置坐标
         */
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:// 手指接触屏幕重置路径
                path.reset();
                path.moveTo(x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:// 手指移动时连接路径
                float dx = Math.abs(x - preX);
                float dy = Math.abs(y - preY);
                if (dx >= MIN_MOVE_DIS || dy >= MIN_MOVE_DIS) {
                    path.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
                    preX = x;
                    preY = y;
                }
                break;
        }
        // 重绘视图
        invalidate();
        return true;
    }

}
