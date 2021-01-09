package com.chyngyz.quizapp.ui.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.os.Bundle;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.ActivityMainBinding;
import com.chyngyz.quizapp.databinding.ActivityQuestionBinding;
import com.chyngyz.quizapp.ui.adapter.QuizAdapter;
import com.chyngyz.quizapp.ui.models.Quiz;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static java.security.AccessController.getContext;

public class QuestionActivity extends AppCompatActivity {

    private ActivityQuestionBinding binding;
    private QuestionViewModel viewModel;
    private RecyclerView recyclerView;
    private ArrayList<Quiz> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_question);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        viewModel = new QuestionViewModel();
        binding.setQuestionViewModel(viewModel);
        binding.quizRecycler.setAdapter(new QuizAdapter(list));

        initRecycler();
        fillQuiz();
    }

    private void fillQuiz() {
        list.add(new Quiz("How much gamers", "1", "2", "3", "4"));
        list.add(new Quiz("Poops", "1", "2", "3", "4"));
        list.add(new Quiz("Donald Duck is man?", true));
        list.add(new Quiz("Monica Belluci is perfect?", true));
        list.add(new Quiz("2 + 2 = ", "3", "1", "4", "0"));
        list.add(new Quiz("The God is Great?", true));
        list.add(new Quiz("The Sun is light?", true));
        list.add(new Quiz("How old is Earth", "10000", "2years", "10years", "20"));
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.quiz_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        QuizAdapter quizAdapter = new QuizAdapter(list);
        recyclerView.setAdapter(quizAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }
}