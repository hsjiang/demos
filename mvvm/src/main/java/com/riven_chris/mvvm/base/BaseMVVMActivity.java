package com.riven_chris.mvvm.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class BaseMVVMActivity<T extends BaseViewModel> extends BaseActivity {

    protected T mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = createViewModel();
    }

    protected T createViewModel() {
        return null;
    }
}
