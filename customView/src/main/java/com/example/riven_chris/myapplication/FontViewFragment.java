package com.example.riven_chris.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FontViewFragment extends Fragment {

    public static FontViewFragment newInstance() {
        FontViewFragment fragment = new FontViewFragment();
        return fragment;
    }

    public FontViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_font_view, container, false);
        return view;
    }

}
