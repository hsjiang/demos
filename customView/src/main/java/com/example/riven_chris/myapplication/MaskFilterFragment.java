package com.example.riven_chris.myapplication;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import views.CustomMaskFilterView;

/**
 * Created by riven_chris on 15/4/22.
 */
public class MaskFilterFragment extends Fragment implements View.OnClickListener {

    private TextView tvBlurMask = null;
    private TextView tvEmbossMask = null;
    private CustomMaskFilterView maskFilterView = null;

    public static MaskFilterFragment newInstance() {
        MaskFilterFragment fragment = new MaskFilterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mask_filter_view, null);
        init(view);
        return view;
    }

    private void init(View view) {
        maskFilterView = (CustomMaskFilterView) view.findViewById(R.id.mask_filter_view);
        tvBlurMask = (TextView) view.findViewById(R.id.tv_blur_mask_filter);
        tvEmbossMask = (TextView) view.findViewById(R.id.tv_emboss_mask_filter);

        tvBlurMask.setOnClickListener(this);
        tvEmbossMask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_blur_mask_filter:
                maskFilterView.setBlurMaskFilter();
                break;
            case R.id.tv_emboss_mask_filter:
                maskFilterView.setEmbossMaskFilter();
                break;
        }
    }
}
