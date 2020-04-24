package com.example.riven_chris.myapplication;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


public class ColorFilterFragment extends Fragment {

    public static ColorFilterFragment newInstance() {
        ColorFilterFragment fragment = new ColorFilterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_matrix, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ImageView bubu = view.findViewById(R.id.iv_bubu);
        bubu.setColorFilter(Color.parseColor("#80313131"), PorterDuff.Mode.SRC_ATOP);
    }
}
