package com.chyngyz.quizapp.ui.historyFragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.ui.settingsFragment.SettingsViewModel;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private SettingsViewModel settingsViewModel;
    private TextView txt;
    private Button btn;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt = view.findViewById(R.id.his_txt);
        btn = view.findViewById(R.id.set_btn);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        settingsViewModel.settingData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null)
                    txt.setText(s);
            }
        });

        if (getArguments() != null)
            btn.setOnClickListener(v -> settingsViewModel.getData());

    }
}