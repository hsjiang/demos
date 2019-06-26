package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private Region mRegionA, mRegionB;// 区域A和区域B对象
    private Paint mPaint;// 绘制边框的Paint
    private Region.Op[] ops = new Region.Op[6];
    private Region.Op op = Region.Op.DIFFERENCE;
    private int i = 1;

    public CustomClipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (Build.VERSION.SDK_INT > 11)
            setLayerType(LAYER_TYPE_SOFTWARE, null);

        // 实例化画笔并设置属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

        // 实例化区域A和区域B
        mRegionA = new Region(100, 100, 300, 300);
        mRegionB = new Region(200, 200, 400, 400);

        ops[0] = Region.Op.DIFFERENCE;
        ops[1] = Region.Op.INTERSECT;
        ops[2] = Region.Op.UNION;
        ops[3] = Region.Op.XOR;
        ops[4] = Region.Op.REVERSE_DIFFERENCE;
        ops[5] = Region.Op.REPLACE;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 填充颜色
        canvas.drawColor(Color.BLUE);

        canvas.save();

        // 裁剪区域A
        canvas.clipRegion(mRegionA);

        if (op == null) {
            Toast.makeText(getContext(), "op is null", Toast.LENGTH_SHORT).show();
        } else {
            // 再通过组合方式裁剪区域B
            canvas.clipRegion(mRegionB, op);
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
