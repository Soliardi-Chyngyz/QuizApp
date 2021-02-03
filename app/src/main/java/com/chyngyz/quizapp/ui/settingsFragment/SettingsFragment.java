package com.chyngyz.quizapp.ui.settingsFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.SettingsFragmentBinding;
import com.chyngyz.quizapp.ui.historyFragment.HistoryViewModel;
import com.chyngyz.quizapp.ui.settingsFragment.settings.SettingsActivity;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private SettingsFragmentBinding binding;
    private HistoryViewModel historyViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.settings_fragment, container, false));
        assert binding != null;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        historyViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        binding.setBinding(mViewModel);


        binding.historyTxt.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    mViewModel.clear();
                    historyViewModel.setDeleteAdapter(true);
                })
                .setNegativeButton("No", null)
                .show());

        mViewModel.getShowToast().observe(requireActivity(), aBoolean -> {
            if (aBoolean)
                Toast.makeText(requireContext(), "History has been successful cleared", Toast.LENGTH_SHORT).show();
        });

        clickOnTheme();
    }

    private void clickOnTheme() {
        binding.historyTheme.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), SettingsActivity.class));
        });
    }


}