package videoplayer.infocus.com.reyclerviewswapitem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by riven_chris on 16/7/10.
 */
public class SwapLayout extends LinearLayout {

    private OverScroller scroller;
    private VelocityTracker tracker;
    private int touchSlop;
    private int maxVelocity;
    private int minVelocity;
    private int overScrollDis;
    private int overFlingDis;

    public SwapLayout(Context context) {
        this(context, null);
    }

    public SwapLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
