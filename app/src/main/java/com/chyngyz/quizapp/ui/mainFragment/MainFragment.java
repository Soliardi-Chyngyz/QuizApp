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
import com.chyngyz.quizapp.data.QuizApiClient;
import com.chyngyz.quizapp.databinding.MainFragmentBinding;
import com.chyngyz.quizapp.ui.adapter.spinner.CustomAdapter;
import com.chyngyz.quizapp.ui.models.Category;
import com.chyngyz.quizapp.ui.models.UnderCategory;
import com.chyngyz.quizapp.ui.question.QuestionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.chyngyz.quizapp.R.layout.my_own_spinner_item;
import static com.chyngyz.quizapp.R.layout.support_simple_spinner_dropdown_item;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private int sumOfAmount = 5;
    private int countOfCategory;
    private String valueOfDifficulty;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.main_fragment, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 1) {
                    binding.amount.setText(String.valueOf(progress));
                } else {
                    progress = 1;
                }
                sumOfAmount = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinnerBack();
        onSelectedSpinnerCategory();

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
                    valueOfDifficulty = choose[(int) (Math.random() * + + 3) + 1];
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

    private void onSelectedSpinnerCategory() {
        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countOfCategory = (position + 8);
                if (position == 0) {
                    countOfCategory = (int) (Math.random() * + + 23) + 9;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinnerBack() {
        List<UnderCategory> list = new ArrayList<>();
        QuizApiClient.getInstance().getCategories().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ArrayList<UnderCategory> categories = response.body().getTrivia_categories();
                        for (int i = 0; i < categories.size(); i++) {
                            int id = categories.get(i).getId();
                            String name = categories.get(i).getName();
                            UnderCategory underCategory = new UnderCategory(id, name);
                            list.add(underCategory);
                        }
                        UnderCategory anyCat = new UnderCategory(8, "Any-type");
                        list.add(0, anyCat);
                        CustomAdapter customAdapter = new CustomAdapter(getContext(), R.layout.my_own_spinner_item, list);
                        binding.spinnerCategory.setAdapter(customAdapter);
                        binding.mainFrProgressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}