package com.example.riven_chris.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ColorFilterFragment extends Fragment {

    public static ColorFilterFragment newInstance() {
        ColorFilterFragment fragment = new ColorFilterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_matrix, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
