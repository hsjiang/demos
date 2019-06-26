package com.example.riven_chris.pulltorefreshviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PTRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptr);
        startActivity(new Intent(PTRActivity.this, StickyHeadActivity.class));
    }
}
