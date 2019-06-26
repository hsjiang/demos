package com.example.riven_chris.overscrollertest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.riven_chris.overscrollertest.widget.ScrollableView;

/**
 * Created by riven_chris on 15/11/12.
 */
public class ScrollableActivity extends FragmentActivity {

    private ScrollableView scrollableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable);
        initView();
    }

    public void initView() {
        scrollableView = (ScrollableView) findViewById(R.id.scrollView);
        for (int i = 0; i < 50; i++) {//?计算高度
            View view = LayoutInflater.from(this).inflate(R.layout.layout_scrollable_item, null);
            scrollableView.addView(view);
        }
    }
}
