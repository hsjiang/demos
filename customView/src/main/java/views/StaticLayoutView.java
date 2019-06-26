package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by riven_chris on 15/4/21.
 */
public class StaticLayoutView extends View {

    private TextPaint textPaint = null;
    private StaticLayout staticLayout = null;

    private String text = "This is used by widgets to control text layout. You should not need to use this class directly unless you are implementing your own widget or custom display object, or would be tempted to call Canvas.drawText() directly.";

    public StaticLayoutView(Context context) {
        this(context, null);
    }

    public StaticLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(60);
//        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setTextSkewX(-0.25f);
//        textPaint.setTextScaleX(1f);
//        textPaint.setTextAlign(Paint.Align.CENTER);
//        Typeface.create(Typeface.SERIF, Typeface.NORMAL);

//        Toast.makeText(getContext(), "init():" + Math.abs(textPaint.ascent() - textPaint.descent()), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        staticLayout = new StaticLayout(text, textPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1f, 0.0f, false);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}
