package com.chyngyz.quizapp.ui.settingsFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chyngyz.quizapp.QuizApp;

public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<Boolean> showToast = new MutableLiveData<>();

    public LiveData<Boolean> getShowToast(){
        return showToast;
    }

    public void clear(){
        QuizApp.getInstance().getQuizRepository().deleteAll();
        showToast.setValue(true);
    }
}