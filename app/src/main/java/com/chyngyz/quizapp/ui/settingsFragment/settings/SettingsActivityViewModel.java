package com.chyngyz.quizapp.ui.settingsFragment.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.ui.models.ThemeItem;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivityViewModel extends ViewModel {
    private MutableLiveData<List<ThemeItem>> themes = new MutableLiveData<>();
    private MutableLiveData<Integer> drawable = new MutableLiveData<>();
    
    public SettingsActivityViewModel(){
        main();
    }

    private void main() {
        List<ThemeItem> list = new ArrayList<>();
        list.add(new ThemeItem(R.drawable.dark_theme_icon, false));
        list.add(new ThemeItem(R.drawable.green_theme_icon, false));
        list.add(new ThemeItem(R.drawable.light_theme_icon, false));
        list.add(new ThemeItem(R.drawable.orange_theme_icon, false));
        list.get(QuizApp.getInstance().getPrefs().getThemePos()).setChange(true);
        themes.setValue(list);
    }

    public LiveData<List<ThemeItem>> getTheme(){
        return themes;
    }

    public LiveData<Integer> getDrawable(){
        return drawable;
    }

    public void onThemeChanged(int pos) {
        switch (pos) {
            case 0:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.AppDarkTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.AppDarkTheme);
                    drawable.setValue(R.style.AppDarkTheme);
                }
                break;
            case 1:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.AppGreenTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.AppGreenTheme);
                    drawable.setValue(R.style.AppGreenTheme);
                }
                break;
            case 2:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.AppLightTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.AppLightTheme);
                    drawable.setValue(R.style.AppLightTheme);
                }
                break;
            case 3:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.AppOrangeTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.AppOrangeTheme);
                    drawable.setValue(R.style.AppOrangeTheme);
                }
                break;
        }
        QuizApp.getInstance().getPrefs().setThemePos(pos);
    }

}
