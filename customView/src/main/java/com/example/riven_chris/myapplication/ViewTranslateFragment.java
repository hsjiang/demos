package com.example.riven_chris.myapplication;

import android.graphics.PointF;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by riven_chris on 15/11/3.
 */
public class ViewTranslateFragment extends Fragment {

    private ImageView iv;
    private TextView tv;
    private PointF pointF = new PointF();
    private float lastX, lastY;
    private float lastOffsetX, lastOffsetY;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_translate, null);
        initView(view);
        return view;
    }

    void initView(View view) {
        iv = (ImageView) view.findViewById(R.id.iv);

//        iv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        pointF.set(event.getX(), event.getY());
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return true;
//            }
//        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setTranslationX(iv.getX() + 40);
                iv.setTranslationY(iv.getY() + 40);
            }
        });

        tv = (TextView) view.findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("哦我飞机深V你偶尔玩女娲ienvwnvwinvwinvwoifjeoifjwijfowejiwe");
            }
        });
    }
}
