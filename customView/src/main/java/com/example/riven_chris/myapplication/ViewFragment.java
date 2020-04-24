package com.example.riven_chris.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import views.RectMaskedBg;

/**
 * Created by riven_chris on 15/10/24.
 */
public class ViewFragment extends Fragment {

    //    private int layout = R.layout.fragment_view_layout;
    private int layout = R.layout.fragment_rect_mask_bg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout, null);
        initRectMaskedView(view);
        return view;
    }

    private void initRectMaskedView(View view) {
        RectMaskedBg bg = (RectMaskedBg) view.findViewById(R.id.mask_bg);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.user_icon);
        bg.initResource(bitmap, Color.parseColor("#03a9f4"), 600, 400);
    }

    public void img() {
        ImageView iv = new ImageView(getActivity());
        Drawable drawable;
        TextView tv;
    }
}
