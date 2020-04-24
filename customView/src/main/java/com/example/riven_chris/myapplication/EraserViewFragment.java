package com.example.riven_chris.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * Created by riven_chris on 15/4/3.
 */
public class EraserViewFragment extends Fragment {

    public static EraserViewFragment newInstance() {
        EraserViewFragment fragment = new EraserViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eraser_view_fragment, null);
        return view;
    }
}
