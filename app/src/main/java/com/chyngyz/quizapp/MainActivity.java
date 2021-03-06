package com.chyngyz.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import com.chyngyz.quizapp.ui.MineViewPager;
import com.chyngyz.quizapp.ui.adapter.MainViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MineViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_QuizApp);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(QuizApp.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setPagingScrollEnabled(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
//        getSupportActionBar().hide();

        onClickBottomNavGetByIdViewPager();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.purple_500));

    }

    @SuppressLint("NonConstantResourceId")
    private void onClickBottomNavGetByIdViewPager() {
        bottomNavigationView.setOnNavigationItemSelectedListener((MenuItem item) -> {
            switch (item.getItemId()) {
                case R.id.main_nav:
                    viewPager.setCurrentItem(0, false);
                    return true;
                case R.id.history_nav:
                    viewPager.setCurrentItem(1, false);
                    return true;
                case R.id.settings_nav:
                    viewPager.setCurrentItem(2, false);
                    return true;
            }
            return false;
        });
    }
}