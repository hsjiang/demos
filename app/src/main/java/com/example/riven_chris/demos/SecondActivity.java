package com.example.riven_chris.demos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by riven_chris on 16/6/24.
 */
public class SecondActivity extends AppCompatActivity implements SecondFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("lifecycle", "SecondActivity onCreate");
    }

    public void startThird(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
//        AlertDialog alertDialog = new AlertDialog.Builder(this)
//                .setMessage("sssssssssssss")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).create();
//        alertDialog.show();
    }

    public void add(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                SecondFragment.newInstance("", "")).addToBackStack("").commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("lifecycle", "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause");
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                add(null);
//            }
//        }, 3000);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
