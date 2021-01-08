package com.chyngyz.quizapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainVModel extends ViewModel {
    public MutableLiveData<String> mViewData = new MutableLiveData<>();

    public void getName() {
        mViewData.setValue("ioollo");
    }
}
