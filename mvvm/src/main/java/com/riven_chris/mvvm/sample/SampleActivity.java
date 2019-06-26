package com.riven_chris.mvvm.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.riven_chris.mvvm.R;
import com.riven_chris.mvvm.base.BaseApplication;
import com.riven_chris.mvvm.base.BaseMVVMActivity;
import com.riven_chris.mvvm.databinding.ActivitySampleBinding;
import com.riven_chris.mvvm.model.UserInfo;

import java.util.HashMap;

public class SampleActivity extends BaseMVVMActivity<SampleViewModel> {
    private ActivitySampleBinding mBinding;

    private HashMap<String, String> mMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);

        mViewModel.mName.observe(this, s -> {
            Log.d(BaseApplication.TAG, "onChange: map name:" + s);
            mBinding.tvMapName.setText(s);
        });

        mViewModel.getId().observe(this, userInfo -> {

        });

        mViewModel.getName().observe(this, s -> {
            Log.d(BaseApplication.TAG, "onchange: " + s);
        });

        mMap.put("key1", "value1,value1,value1");
        mMap.put("key2", "value2,value2,value2");
        mMap.put("key3", "value3");
        mMap.put("key4", "value4");
        mMap.put("key5", "value5");
        mBinding.setMap(mMap);
    }

    @Override
    protected SampleViewModel createViewModel() {
        return ViewModelProviders.of(this).get(SampleViewModel.class);
    }

    public void setName(View view) {
        mViewModel.setName("my name");
    }

    public void transformName(View view) {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName("riven mapped");
//        mViewModel.getUserLiveData().setValue(userInfo);

        mViewModel.setId(20);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName("riven2");
        userInfo1.setUid("25");
        mViewModel.setUserLiveData(userInfo1);
    }
}
