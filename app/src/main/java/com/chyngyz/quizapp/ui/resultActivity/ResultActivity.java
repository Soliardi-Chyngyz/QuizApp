package com.chyngyz.quizapp.ui.resultActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.ActivityResultBinding;
import com.chyngyz.quizapp.ui.models.QuizResultNoRoom;
import static com.chyngyz.quizapp.core.StaticMethods.RESULT_ACTIVITY_CODE;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    private ResultViewModel vModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(QuizApp.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        vModel = new ViewModelProvider(this).get(ResultViewModel.class);
        binding.setResultViewModel(vModel);

        getData();
        observe();

        binding.resultFininsh.setOnClickListener(v -> vModel.setIsFinish());
    }

    private void observe() {
        vModel.getIsFinish().observe(this, aBoolean -> {
            if (aBoolean) {
                finish();
            }
        });
    }

    private void getData() {
        QuizResultNoRoom quizResultNoRoom = (QuizResultNoRoom) getIntent().getSerializableExtra(RESULT_ACTIVITY_CODE);
        binding.setResult(quizResultNoRoom);
        vModel.setQuizResult(quizResultNoRoom);
    }
}