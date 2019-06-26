package com.example.riven_chris.viewpagermultiview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.Map;

public class ViewPagerMultiActivity extends AppCompatActivity {

    private ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_multi);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(7);
        viewPager.setPageMargin(50);
        viewPager.setAdapter(new CyclePagerAdapter(this));

//        Field field = null;
//        try {
//            field = ViewPager.class.getDeclaredField("mScroller");
//            field.setAccessible(true);
//            field.set(viewPager, this);
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
