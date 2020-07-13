package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by riven_chris on 15/4/28.
 */
public class CustomClipView extends View {
    private Rect mRectA, mRectB;// 区域A和区域B对象
    private Paint mPaint;// 绘制边框的Paint
    private Region.Op[] ops = new Region.Op[2];
    private Region.Op op = Region.Op.DIFFERENCE;
    private int i = 1;

    public CustomClipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayerType(LAYER_TYPE_SOFTWARE, null);

        // 实例化画笔并设置属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

        // 实例化区域A和区域B
        mRectA = new Rect(100, 100, 300, 300);
        mRectB = new Rect(200, 200, 400, 400);

        ops[0] = Region.Op.DIFFERENCE;
        ops[1] = Region.Op.INTERSECT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 填充颜色
        canvas.drawColor(Color.BLUE);

        canvas.save();

        // 裁剪区域A
        canvas.clipRect(mRectA);

        if (op == null) {
            Toast.makeText(getContext(), "op is null", Toast.LENGTH_SHORT).show();
        } else {
            canvas.clipRect(mRectB, op);
        }

        // 填充颜色
        canvas.drawColor(Color.RED);

        canvas.restore();

        // 绘制框框帮助我们观察
        canvas.drawRect(100, 100, 300, 300, mPaint);
        canvas.drawRect(200, 200, 400, 400, mPaint);

    }

    public void start() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                op = ops[i];
                i = i >= ops.length - 1 ? 0 : ++i;
                postInvalidate();
            }
        }, 600, 600);
    }
}
