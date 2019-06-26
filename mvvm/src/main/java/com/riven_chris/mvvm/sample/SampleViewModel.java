package com.riven_chris.mvvm.sample;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.riven_chris.mvvm.LiveData.UserLiveData;
import com.riven_chris.mvvm.base.BaseApplication;
import com.riven_chris.mvvm.base.BaseViewModel;
import com.riven_chris.mvvm.model.UserInfo;

public class SampleViewModel extends BaseViewModel {

    public MutableLiveData<String> mName = new MutableLiveData<>();
    public UserLiveData mUserLiveData;

    MutableLiveData<Integer> id = new MutableLiveData<>();

    public SampleViewModel(@NonNull Application application) {
        super(application);
        Log.d(BaseApplication.TAG, "Constructor: SampleViewModel, Application: " + application.toString());
        id.setValue(10);
        mName.setValue("hahahahahahaha");
    }

    public MutableLiveData<String> getName() {
        return mName;
    }

    public void setName(String name) {
        mName.setValue(name);
    }

    public UserLiveData getUserLiveData() {
        if (mUserLiveData == null) {
            mUserLiveData = new UserLiveData();
            UserInfo userInfo = new UserInfo();
            userInfo.setName("riven");
            userInfo.setUid("15");
            mUserLiveData.setValue(userInfo);
        }
        return mUserLiveData;
    }

    public void setUserLiveData(UserInfo info) {
        if (mUserLiveData == null) {
            mUserLiveData = new UserLiveData();
        }
        mUserLiveData.setValue(info);
    }

    public MutableLiveData<Integer> getId() {
        return id;
    }

    public void setId(int uid) {
        id.setValue(uid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(BaseApplication.TAG, "onCleared");
    }
}
