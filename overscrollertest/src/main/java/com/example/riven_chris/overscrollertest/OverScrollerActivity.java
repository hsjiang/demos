package com.example.riven_chris.overscrollertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class OverScrollerActivity extends AppCompatActivity {

    private RelativeLayout rl;
    private LinearLayout ll;
    private TextView tv;
    private TextView tvInvalidate;
    private TextView tvText;
    private TextView tvStartActivity;


    private float translationY;
    private int touchSlop;
    private int maxVelocity;
    private int minVelocity;
    private int overScrollDis;
    private int overFlingDis;

    private float lastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_scroller);
        maxVelocity = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();
        minVelocity = ViewConfiguration.get(this).getScaledTouchSlop();
        touchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        overScrollDis = ViewConfiguration.get(this).getScaledOverscrollDistance();
        overFlingDis = ViewConfiguration.get(this).getScaledOverflingDistance();
        Log.d("TAG", "TouchSlop: " + touchSlop);
        Log.d("TAG", "maxVelocity: " + maxVelocity);
        Log.d("TAG", "minVelocity" + minVelocity);
        Log.d("TAG", "overScrollDis" + overScrollDis);
        Log.d("TAG", "overFlingDis" + overFlingDis);

        ScrollView scroller;
        ListView listView;

        rl = (RelativeLayout) findViewById(R.id.rl);
        ll = (LinearLayout) findViewById(R.id.ll);
        tv = (TextView) findViewById(R.id.tv);
        tvText = (TextView) findViewById(R.id.center);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translationY += 60;
//                ll.setTranslationY(translationY);
//                ll.offsetTopAndBottom(60);
//                ll.offsetLeftAndRight(60);
//                ll.scrollTo(0, 160);
//                ll.scrollTo(0, (int) translationY);
                ll.scrollBy(0, -60);
            }
        });
        tvInvalidate = (TextView) findViewById(R.id.tv_invalidate);
        tvInvalidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OverScrollerActivity.this, "invalidate", Toast.LENGTH_SHORT).show();
                tvText.setText("" + translationY);
                ll.invalidate();
            }
        });

        tvStartActivity = (TextView) findViewById(R.id.tv_start);
        tvStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OverScrollerActivity.this, ScrollableActivity.class));
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - lastY;
                if (Math.abs(dy) > touchSlop) {
//                    rl.setTranslationY(y);
                    lastY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.AXIS_HSCROLL:
                break;
        }

//        int i = event.getHistorySize();
//        Log.d("TAG", "" + i)
//        int i = event.getHistoricalY(0);
        return super.onTouchEvent(event);
    }
}
