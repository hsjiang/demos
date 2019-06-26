package com.example.riven_chris.pulltorefreshviews;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import widget.StickyNavLayout;

/**
 * Created by riven_chris on 15/11/6.
 */
public class StickyHeadActivity extends FragmentActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private LinearLayout mTopMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header);
        initView();
    }

    public void initView() {

        StickyNavLayout stickyNavLayout = (StickyNavLayout) findViewById(R.id.sticky_layout);
        mTopMenu = (LinearLayout) findViewById(R.id.ll_top_menu);
        toggleTopMenu(false, 0);
        mTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StickyHeadActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        stickyNavLayout.setScrollObserver(new StickyNavLayout.ScrollObserver() {
            @Override
            public void onToggleTopMenu(boolean show) {
                toggleTopMenu(show, 600);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                return TabFragment.newInstance("" + position);
            }
        };

        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
    }

    public void toggleTopMenu(boolean show, int duration) {
        if (show) {
            mTopMenu.setEnabled(true);
            ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mTopMenu, "alpha",
                    0, 1).setDuration(duration);
            alphaAnim.setInterpolator(new LinearInterpolator());
            alphaAnim.start();
        } else {
            mTopMenu.setEnabled(false);
            ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mTopMenu, "alpha",
                    1, 0).setDuration(duration);
            alphaAnim.start();

        }
    }
}
