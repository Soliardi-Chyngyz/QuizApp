package com.chyngyz.quizapp.ui.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.ActivityQuestionBinding;
import com.chyngyz.quizapp.ui.adapter.QuizAdapter;
import com.chyngyz.quizapp.ui.mainFragment.MainFragment;
import com.chyngyz.quizapp.ui.resultActivity.ResultActivity;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static com.chyngyz.quizapp.core.StaticMethods.RESULT_ACTIVITY_CODE;

public class QuestionActivity extends AppCompatActivity implements QuizAdapter.OnResultAnswerClickListener {
    private ActivityQuestionBinding binding;
    private QuestionViewModel viewModel;
    private QuizAdapter quizAdapter;
    int amount;
    int countOfCategory;
    String valueOfDifficult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        binding.setQuestionViewModel(viewModel);

        initRecycler();
        getDataIntent();
        getQuestionsData();

        setArguments();
    }

    private void setArguments() {
        binding.progressHorizontBar.setMax(amount);
        binding.progressHorizontBar.setProgress(0);
        viewModel.getCurrentQuestionPosition().observe(this, position -> {
            binding.quizRecycler.scrollToPosition(position);
            binding.progressHorizontBar.setProgress(position + 1);
        });
        viewModel.getQuizResultNoRoom().observe(this, quizResultNoRoom -> {
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtra(RESULT_ACTIVITY_CODE, quizResultNoRoom);
            startActivity(intent);
            finish();
        });
    }

    private void getDataIntent() {
        amount = getIntent().getExtras().getInt("amount");
        countOfCategory = getIntent().getExtras().getInt("countOfCategory");
        valueOfDifficult = getIntent().getExtras().getString("valueOfDifficult");
        binding.questionTitle.setText(getIntent().getExtras().getString(MainFragment.TITLE_KEY));
    }

    private void getQuestionsData() {
        viewModel.getQuestionsFromBack(amount, countOfCategory, valueOfDifficult);
        viewModel.getMQuestion().observe(this, questions -> {
            binding.progressHorizontBar.setMax(questions.size());
            if (questions.size() > 0) {
                binding.progressBar.setVisibility(View.GONE);
                quizAdapter = new QuizAdapter(questions, this);
                binding.quizRecycler.setAdapter(quizAdapter);
            }
        });
    }

    private void initRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        binding.quizRecycler.setLayoutManager(layoutManager);
        binding.quizRecycler.setItemAnimator(new DefaultItemAnimator());

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.quizRecycler);

    }

    @Override
    public void onBackPressed() {
        if (viewModel.getCurrentQuestionPosition().getValue() != null) {
            viewModel.setCurrentQuestionPosition(viewModel.getCurrentQuestionPosition().getValue() - 1);
            if (viewModel.getCurrentQuestionPosition().getValue() < 0)
                super.onBackPressed();
        }
    }

    @Override
    public void onClick(int result, String answer) {
        viewModel.onAnswerClick(result, answer, binding.questionTitle.getText().toString(), valueOfDifficult);
    }
}