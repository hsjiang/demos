package com.example.riven_chris.overscrollertest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by riven_chris on 15/11/11.
 */
public class ScrollableView extends LinearLayout {

    private OverScroller scroller;
    private VelocityTracker tracker;

    private int touchSlop;
    private int maxVelocity;
    private int minVelocity;
    private int overScrollDis;
    private int overFlingDis;

    private float lastY;

    public ScrollableView(Context context) {
        super(context);
    }

    public ScrollableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new OverScroller(context);
        maxVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
        minVelocity = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        overScrollDis = ViewConfiguration.get(getContext()).getScaledOverscrollDistance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(widthMeasureSpec, MeasureSpec.AT_MOST);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);
        float y = event.getY();

        Log.d("ScrollableView", "getScrollY: " + getScrollY());

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                initVelocityTrackerIsNotExists();
                tracker.clear();
                tracker.addMovement(event);
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = lastY - y;
                if (Math.abs(dy) > touchSlop) {
                    scrollBy(0, (int) dy);
                }
                tracker.addMovement(event);
                lastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                recycleTracker();
                break;
            case MotionEvent.ACTION_UP:
                recycleTracker();
                break;
        }
        return true;
    }

    public void initVelocityTrackerIsNotExists() {
        if (tracker == null)
            tracker = VelocityTracker.obtain();
    }

    public void recycleTracker() {
        if (tracker != null) {
            tracker.recycle();
            tracker = null;
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }


}
