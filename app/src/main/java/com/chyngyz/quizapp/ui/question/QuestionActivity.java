package com.chyngyz.quizapp.ui.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.ActivityQuestionBinding;
import com.chyngyz.quizapp.ui.adapter.QuizAdapter;
import com.chyngyz.quizapp.ui.mainFragment.MainFragment;
import com.chyngyz.quizapp.ui.models.Question;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class QuestionActivity extends AppCompatActivity implements QuizAdapter.MainListener {
    private ActivityQuestionBinding binding;
    private QuestionViewModel viewModel;
    private ArrayList<Question> list = new ArrayList<>();
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
        super.onBackPressed();
    }

    @Override
    public void onAnswerClick(int questionPosition, int answerPosition, int result) {
        viewModel.onAnswersClick(questionPosition, answerPosition);
    }
}