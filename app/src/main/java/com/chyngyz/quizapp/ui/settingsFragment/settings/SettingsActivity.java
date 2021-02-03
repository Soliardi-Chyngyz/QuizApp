package com.chyngyz.quizapp.ui.settingsFragment.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.ActivitySettingsBinding;
import com.chyngyz.quizapp.interfaces.OnItemThemeClickListener;
import com.chyngyz.quizapp.ui.adapter.ThemeAdapter;

public class SettingsActivity extends AppCompatActivity implements OnItemThemeClickListener {

    private ActivitySettingsBinding binding;
    private SettingsActivityViewModel vModel;
    private ThemeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(QuizApp.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        adapter = new ThemeAdapter();
        adapter.onClick(this);
        vModel = new ViewModelProvider(this).get(SettingsActivityViewModel.class);

        vModel.getTheme().observe(this, themeItems -> {
            adapter.setList(themeItems);
        });
        binding.recyclerTheme.setAdapter(adapter);

        vModel.getDrawable().observe(this, integer -> {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });

    }

    @Override
    public void onThemeClicked(int pos) {
        vModel.onThemeChanged(pos);
    }
}