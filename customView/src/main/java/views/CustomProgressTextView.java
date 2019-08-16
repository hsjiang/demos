package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by riven_chris on 2017/8/29.
 */

public class CustomProgressTextView extends View {

    private Paint mTextPaint = null;

    public CustomProgressTextView(Context context) {
        this(context, null);
    }

    public CustomProgressTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
