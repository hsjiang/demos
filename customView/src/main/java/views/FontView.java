package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by riven_chris on 15/4/20.
 */
public class FontView extends View {
    private String text = "Canvas.drawText()";
    private Paint paint = null;
    private Paint linePaint = null; //中心线画笔

    private int x, y;//baseline绘制xy坐标

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
//        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setTextSize(80);
//        paint.setColor(Color.BLUE);
//        paint.setTypeface(Typeface.SERIF);

        paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(80);
        paint.setColor(Color.BLUE);
        paint.setTypeface(Typeface.SERIF);
        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSkewX(-55);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);
        linePaint.setColor(Color.RED);

//        Toast.makeText(context, paint.ascent() + " , " + paint.descent(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算Baseline绘制的起点X轴坐标
//        x = (int) (canvas.getWidth() - paint.measureText(text)) / 2;

        // 计算Baseline绘制的Y坐标
//        y = (int) (canvas.getHeight() - (paint.descent() + paint.ascent())) / 2;
        y = canvas.getHeight() / 2;
        Toast.makeText(getContext(), canvas.getHeight() + " , " + (paint.descent() + paint.ascent()) + "," + y, Toast.LENGTH_SHORT).show();

        canvas.drawText(text, canvas.getWidth() / 2, y, paint);

        // 为了便于理解我们在画布中心处绘制一条中线
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, linePaint);
    }
}
