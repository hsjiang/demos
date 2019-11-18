package com.riven_chris.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.riven_chris.mvvm.sample.EventHandlingActivity;
import com.riven_chris.mvvm.sample.IncludeActivity;
import com.riven_chris.mvvm.sample.MediatorLiveDataActivity;
import com.riven_chris.mvvm.sample.SampleActivity;

public class MVVMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);

    }

    public void startSample(View view) {
        startActivity(new Intent(this, SampleActivity.class));
    }

    public void startEventHandling(View view) {
        startActivity(new Intent(this, EventHandlingActivity.class));
    }

    public void include(View view) {
        startActivity(new Intent(this, IncludeActivity.class));
    }

    public void mediatorLiveData(View view) {
        startActivity(new Intent(this, MediatorLiveDataActivity.class));
    }
}
