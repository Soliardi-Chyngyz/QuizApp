package com.chyngyz.quizapp.ui.settingsFragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public MutableLiveData<String> settingData = new MutableLiveData<>();

    public void setData (String s){
        settingData.setValue(s);
    }

    public String getData(){
        return settingData.getValue();
    }
}