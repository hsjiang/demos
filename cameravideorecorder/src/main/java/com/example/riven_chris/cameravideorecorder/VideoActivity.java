package com.example.riven_chris.cameravideorecorder;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements Camera.PreviewCallback, SurfaceHolder.Callback, View.OnClickListener {

    private static final String TAG = VideoActivity.class.getSimpleName();

    private SurfaceView surfaceView;
    private TextView tvSurface;

    private Camera mCamera;
    private SurfaceHolder surfaceHolder;
    private boolean isStart = false;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        tvSurface = (TextView) findViewById(R.id.tvStart);

        surfaceHolder = surfaceView.getHolder();

        try {
            mCamera = Camera.open(findBackFacingCamera());
            mCamera.unlock();
            surfaceHolder.addCallback(this);
            mCamera.setPreviewCallback(this);
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean b, Camera camera) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.d(TAG, "frame: " + (i++));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: " + "width: " + width + " height: " + height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStart:
                i = 0;
                if (isStart) {
                    mCamera.stopPreview();
                    isStart = false;
                } else {
                    mCamera.startPreview();
                    isStart = true;
                }
                break;
        }
    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        // Search for the back facing camera
        // get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        // for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        } catch (Exception e) {

        }
    }
}
