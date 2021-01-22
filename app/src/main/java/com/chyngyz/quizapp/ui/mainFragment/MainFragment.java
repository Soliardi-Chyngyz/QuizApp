package com.chyngyz.quizapp.ui.mainFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.core.MineSeekBarChangeListener;
import com.chyngyz.quizapp.databinding.MainFragmentBinding;
import com.chyngyz.quizapp.ui.adapter.spinner.CustomAdapter;
import com.chyngyz.quizapp.ui.models.UnderCategory;
import com.chyngyz.quizapp.ui.question.QuestionActivity;
import java.util.List;

import static com.chyngyz.quizapp.R.layout.my_own_spinner_item;

public class MainFragment extends Fragment {
    public static final String TITLE_KEY = "title_key";

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private int sumOfAmount = 5;
    private int countOfCategory;
    private String valueOfDifficulty;
    private String titleOfCategory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.main_fragment, container, false));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.setMainFragment(mViewModel);

        binding.seekBar.setOnSeekBarChangeListener(new MineSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 1) {
                    binding.amount.setText(String.valueOf(progress));
                } else {
                    progress = 1;
                }
                sumOfAmount = progress;
            }
        });

        spinnerBack();

        spinnerDifficulty();
        onSelectedSpinnerDifficulty();

        intent();
    }

    private void intent() {
        binding.start.setOnClickListener(v -> {
            if (countOfCategory >= 9) {
                Intent intent = new Intent(getContext(), QuestionActivity.class);
                intent.putExtra("amount", sumOfAmount);
                intent.putExtra("countOfCategory", countOfCategory);
                intent.putExtra("valueOfDifficult", valueOfDifficulty);
                intent.putExtra(TITLE_KEY, titleOfCategory);
                startActivity(intent);
                Log.v("value", "amount: " + sumOfAmount + "categort " + countOfCategory + "diff " + valueOfDifficulty);
            }
        });
    }

    private void onSelectedSpinnerDifficulty() {
        binding.spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.difficult);
                if (position != 0) {
                    valueOfDifficulty = choose[position];
                } else {
                    valueOfDifficulty = choose[(int) (Math.random() * + +3) + 1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinnerDifficulty() {
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(), R.array.difficult, my_own_spinner_item);
        adapter.setDropDownViewResource(my_own_spinner_item);
        binding.spinnerDifficulty.setAdapter(adapter);
        binding.spinnerDifficulty.setSelection(0);
    }

    private void spinnerBack() {
        mViewModel.getUnderCategoryBack();
        mViewModel.getUnderCategoryLiceData().observe(this, (List<UnderCategory> underCategories) -> {

            CustomAdapter customAdapter = new CustomAdapter(binding.getRoot().getContext(), R.layout.my_own_spinner_item, underCategories);
            binding.spinnerCategory.setAdapter(customAdapter);
            binding.mainFrProgressBar.setVisibility(View.GONE);

            binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    countOfCategory = (position + 8);
                    if (position == 0) {
                        countOfCategory = (int) (Math.random() * + +23) + 9;
                    }
                    titleOfCategory = underCategories.get(position).getName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });

    }
}