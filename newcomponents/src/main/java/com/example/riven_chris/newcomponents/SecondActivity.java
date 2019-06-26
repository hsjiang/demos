package com.example.riven_chris.newcomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

/**
 * Created by riven_chris on 2016/1/27.
 */
public class SecondActivity extends BaseActivity {
    private FloatingActionButton fab = null;
    CollapsingToolbarLayout cLayout;
    AppBarLayout appBarLayout;
    CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
        cLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        cLayout.setTitle("哈哈哈哈");
        cLayout.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("SecondActivity", "verticalOffset: " + verticalOffset);
            }
        });
    }
}
