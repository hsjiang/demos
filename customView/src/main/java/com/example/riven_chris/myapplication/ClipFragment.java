package com.example.riven_chris.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import views.CustomClipView;

/**
 * Created by riven_chris on 15/4/28.
 */
public class ClipFragment extends Fragment {
    private CustomClipView clipView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clip_view_layout, null);

        clipView = (CustomClipView) view.findViewById(R.id.clip_view);
        clipView.start();

        return view;
    }
}
