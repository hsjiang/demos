package com.example.riven_chris.myapplication;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import views.CustomPorterDuffView;

public class PorterDuffModeFragment extends Fragment implements View.OnClickListener {

    private CustomPorterDuffView customPorterDuffView = null;
    private TextView tvClear = null;
    private TextView tvSrc = null;
    private TextView tvDst = null;
    private TextView tvSrcOver = null;
    private TextView tvDstOver = null;
    private TextView tvSrcIn = null;
    private TextView tvDstIn = null;
    private TextView tvSrcOut = null;
    private TextView tvDstOut = null;
    private TextView tvSrcTop = null;
    private TextView tvDstTop = null;
    private TextView tvXor = null;
    private TextView tvDarken = null;
    private TextView tvLighten = null;
    private TextView tvMultiply = null;
    private TextView tvScreen = null;

    public static PorterDuffModeFragment newInstance() {
        PorterDuffModeFragment fragment = new PorterDuffModeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_porter_duff_mode, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        customPorterDuffView = (CustomPorterDuffView) view.findViewById(R.id.custom_porter_duff_view);
        tvClear = (TextView) view.findViewById(R.id.clear);
        tvSrc = (TextView) view.findViewById(R.id.src);
        tvDst = (TextView) view.findViewById(R.id.dst);
        tvSrcOver = (TextView) view.findViewById(R.id.src_over);
        tvDstOver = (TextView) view.findViewById(R.id.dst_over);
        tvSrcIn = (TextView) view.findViewById(R.id.src_in);
        tvDstIn = (TextView) view.findViewById(R.id.dst_in);
        tvSrcOut = (TextView) view.findViewById(R.id.src_out);
        tvDstOut = (TextView) view.findViewById(R.id.dst_out);
        tvSrcTop = (TextView) view.findViewById(R.id.src_top);
        tvDstTop = (TextView) view.findViewById(R.id.dst_top);
        tvXor = (TextView) view.findViewById(R.id.xor);
        tvDarken = (TextView) view.findViewById(R.id.darken);
        tvLighten = (TextView) view.findViewById(R.id.lighten);
        tvMultiply = (TextView) view.findViewById(R.id.multiply);
        tvScreen = (TextView) view.findViewById(R.id.screen);


        tvClear.setOnClickListener(this);
        tvSrc.setOnClickListener(this);
        tvDst.setOnClickListener(this);
        tvSrcOver.setOnClickListener(this);
        tvDstOver.setOnClickListener(this);
        tvSrcIn.setOnClickListener(this);
        tvDstIn.setOnClickListener(this);
        tvSrcOut.setOnClickListener(this);
        tvDstOut.setOnClickListener(this);
        tvSrcTop.setOnClickListener(this);
        tvDstTop.setOnClickListener(this);
        tvXor.setOnClickListener(this);
        tvDarken.setOnClickListener(this);
        tvLighten.setOnClickListener(this);
        tvMultiply.setOnClickListener(this);
        tvScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                setPorterDuffMode(PorterDuff.Mode.CLEAR);
                break;
            case R.id.src:
                setPorterDuffMode(PorterDuff.Mode.SRC);
                break;
            case R.id.dst:
                setPorterDuffMode(PorterDuff.Mode.DST);
                break;
            case R.id.src_over:
                setPorterDuffMode(PorterDuff.Mode.SRC_OVER);
                break;
            case R.id.dst_over:
                setPorterDuffMode(PorterDuff.Mode.DST_OVER);
                break;
            case R.id.src_in:
                setPorterDuffMode(PorterDuff.Mode.SRC_IN);
                break;
            case R.id.dst_in:
                setPorterDuffMode(PorterDuff.Mode.DST_IN);
                break;
            case R.id.src_out:
                setPorterDuffMode(PorterDuff.Mode.SRC_OUT);
                break;
            case R.id.dst_out:
                setPorterDuffMode(PorterDuff.Mode.DST_OUT);
                break;
            case R.id.src_top:
                setPorterDuffMode(PorterDuff.Mode.SRC_ATOP);
                break;
            case R.id.dst_top:
                setPorterDuffMode(PorterDuff.Mode.DST_ATOP);
                break;
            case R.id.xor:
                setPorterDuffMode(PorterDuff.Mode.XOR);
                break;
            case R.id.darken:
                setPorterDuffMode(PorterDuff.Mode.DARKEN);
                break;
            case R.id.lighten:
                setPorterDuffMode(PorterDuff.Mode.LIGHTEN);
                break;
            case R.id.multiply:
                setPorterDuffMode(PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.screen:
                setPorterDuffMode(PorterDuff.Mode.SCREEN);
                break;
        }
    }

    public void setPorterDuffMode(PorterDuff.Mode mode) {
        customPorterDuffView.setPorterDuffMode(mode);
    }
}
