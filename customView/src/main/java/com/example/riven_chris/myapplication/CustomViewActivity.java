package com.example.riven_chris.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import Constant.Constant;


public class CustomViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initScreen();
        initView();
    }

    private void initView() {
        findView(R.id.tv).setOnClickListener(this);
        findView(R.id.custom_view).setOnClickListener(this);
        findView(R.id.tv_color_filter).setOnClickListener(this);
        findView(R.id.tv_porter_duff).setOnClickListener(this);
        findView(R.id.tv_eraser_view).setOnClickListener(this);
        findView(R.id.tv_font_view).setOnClickListener(this);
        findView(R.id.tv_mask_filter_view).setOnClickListener(this);
        findView(R.id.tv_path_effect_view).setOnClickListener(this);
        findView(R.id.tv_ecg_view).setOnClickListener(this);
        findView(R.id.tv_shader_view).setOnClickListener(this);
        findView(R.id.tv_matrix_view).setOnClickListener(this);
        findView(R.id.tv_bitmap_mesh).setOnClickListener(this);
        findView(R.id.tv_path_view).setOnClickListener(this);
        findView(R.id.tv_ecg_view).setOnClickListener(this);
        findView(R.id.tv_shader_view).setOnClickListener(this);
        findView(R.id.tv_matrix_view).setOnClickListener(this);
        findView(R.id.tv_bitmap_mesh).setOnClickListener(this);
        findView(R.id.tv_path_view).setOnClickListener(this);
        findView(R.id.tv_clip_view).setOnClickListener(this);
        findView(R.id.tv_canvas_layer_view).setOnClickListener(this);
        findView(R.id.tv_canvas_transform_view).setOnClickListener(this);
        findView(R.id.tv_img_view).setOnClickListener(this);
        findView(R.id.tv_layout_view).setOnClickListener(this);
        findView(R.id.tv_lifecycle_view).setOnClickListener(this);
        findView(R.id.tv).setOnClickListener(this);
        findView(R.id.tv_progress_text_view).setOnClickListener(this);
        findView(R.id.tv_paint_tool).setOnClickListener(this);
    }

    private void initScreen() {
        Constant.screenW = getResources().getDisplayMetrics().widthPixels;
        Constant.screenH = getResources().getDisplayMetrics().heightPixels;
        Constant.density = getResources().getDisplayMetrics().density;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
            case R.id.tv_paint_tool:
                //                startFragment(ViewFragment.class.getName());
//                startFragment(ViewTranslateFragment.class.getName());
                startFragment(PaintToolFragment.class.getName());
                break;
            case R.id.tv_color_filter:
                startFragment(ColorFilterFragment.class.getName());
                break;
            case R.id.tv_porter_duff:
                startFragment(PorterDuffModeFragment.class.getName());
                break;
            case R.id.tv_eraser_view:
                startFragment(EraserViewFragment.class.getName());
                break;
            case R.id.tv_font_view:
                startFragment(FontViewFragment.class.getName());
                break;
            case R.id.tv_mask_filter_view:
                startFragment(MaskFilterFragment.class.getName());
                break;
            case R.id.tv_path_effect_view:
                startFragment(PathEffectFragment.class.getName());
                break;
            case R.id.tv_ecg_view:
                startFragment(ECGFragment.class.getName());
                break;
            case R.id.tv_shader_view:
                startFragment(CustomShaderFragment.class.getName());
                break;
            case R.id.tv_matrix_view:
                startFragment(MatrixImgFragment.class.getName());
                break;
            case R.id.tv_bitmap_mesh:
                startFragment(BitmapMeshFragment.class.getName());
                break;
            case R.id.tv_path_view:
                startFragment(PathViewFragment.class.getName());
                break;
            case R.id.tv_clip_view:
                startFragment(ClipFragment.class.getName());
                break;
            case R.id.tv_canvas_layer_view:
                startFragment(CanvasLayerFragment.class.getName());
                break;
            case R.id.tv_canvas_transform_view:
                startFragment(CanvasTransformFragment.class.getName());
                break;
            case R.id.tv_img_view:
                startFragment(ImgFragment.class.getName());
                break;
            case R.id.tv_layout_view:
                startFragment(CustomLayoutFragment.class.getName());
                break;
            case R.id.tv_lifecycle_view:
                startFragment(LifeCycleFragment.class.getName());
                break;
            case R.id.tv_progress_text_view:
                startFragment(ProgressTextViewFragment.class.getName());
                break;
        }
    }

    public void startFragment(String className) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, Fragment.instantiate(this, className, null))
                .addToBackStack(className)
                .commit();
    }

//    public void startFragment(String className) {
//        getFragmentManager().beginTransaction().add(R.id.container, ColorFilterFragment.newInstance()).addToBackStack("ColorFilterFragment").commit();
//        getFragmentManager().beginTransaction().add(R.id.container, PorterDuffModeFragment.newInstance()).addToBackStack("PorterDuffModeFragment").commit();
//        getFragmentManager().beginTransaction().add(R.id.container, EraserViewFragment.newInstance()).addToBackStack("EraserViewFragment").commit();
//        getFragmentManager().beginTransaction().add(R.id.container, FontViewFragment.newInstance()).addToBackStack("FontViewFragment").commit();
//        getFragmentManager().beginTransaction().add(R.id.container, MaskFilterFragment.newInstance()).addToBackStack("MaskFilterFragment").commit();
//    }

    public View findView(int id) {
        return findViewById(id);
    }
}
