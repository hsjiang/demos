package com.riven_chris.mvvm.LiveData;


import androidx.lifecycle.LiveData;

import com.riven_chris.mvvm.model.UserInfo;

public class UserLiveData extends LiveData<UserInfo> {

    @Override
    public void setValue(UserInfo value) {
        super.setValue(value);
    }
}
