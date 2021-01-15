package com.chyngyz.quizapp.ui.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.ActivityQuestionBinding;
import com.chyngyz.quizapp.ui.adapter.QuizAdapter;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.models.QuizResponse;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class QuestionActivity extends AppCompatActivity {
    private ActivityQuestionBinding binding;

    private QuestionViewModel viewModel;
    private RecyclerView recyclerView;
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
        viewModel = new QuestionViewModel();
        binding.setQuestionViewModel(viewModel);
        binding.quizRecycler.setAdapter(new QuizAdapter(list));

        initRecycler();
        getDataIntent();
        getQuestionsData();

        viewModel.getToastObserver().observe(this, s -> {
            Toast.makeText(this, "Что-то пошло не так", Toast.LENGTH_SHORT).show();
        });
    }

    private void getDataIntent() {
        amount = getIntent().getExtras().getInt("amount");
        countOfCategory = getIntent().getExtras().getInt("countOfCategory");
        valueOfDifficult = getIntent().getExtras().getString("valueOfDifficult");
    }

    private void getQuestionsData() {
        viewModel.getQuestionsFromBack(amount, countOfCategory, valueOfDifficult);
        viewModel.mQuestions.observe(this, new Observer<QuizResponse>() {
            @Override
            public void onChanged(QuizResponse quizResponse) {
                if (quizResponse.getResults().size() > 0) {
                    binding.progressBar.setVisibility(View.GONE);
                    quizAdapter = new QuizAdapter(quizResponse.getResults());
                    recyclerView.setAdapter(quizAdapter);
                }
            }
        });
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.quiz_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

}