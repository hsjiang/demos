package com.example.riven_chris.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import views.LifeCycleView;

/**
 * Created by riven_chris on 15/5/3.
 */
public class LifeCycleFragment extends Fragment implements View.OnClickListener {

    private LifeCycleView lifeCycleView = null;
    private TextView tvSet = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lifecycle_view_layout, null);
        lifeCycleView = (LifeCycleView) view.findViewById(R.id.lifecycle_view);
        tvSet = (TextView) view.findViewById(R.id.tv_set);
        tvSet.setOnClickListener(this);

        Log.d("LifeCycleView", "onCreateView");
        return view;
    }

    @Override
    public void onClick(View v) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.minv);
        lifeCycleView.setBitmap(bitmap);
    }
}
