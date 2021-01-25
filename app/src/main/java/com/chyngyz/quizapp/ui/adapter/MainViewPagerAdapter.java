package com.chyngyz.quizapp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chyngyz.quizapp.ui.historyFragment.HistoryFragment;
import com.chyngyz.quizapp.ui.mainFragment.MainFragment;
import com.chyngyz.quizapp.ui.settingsFragment.SettingsFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new HistoryFragment();
                break;
            default:
                fragment = new SettingsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
