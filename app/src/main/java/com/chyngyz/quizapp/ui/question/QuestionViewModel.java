package com.chyngyz.quizapp.ui.question;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.chyngyz.quizapp.data.QuizApi;
import com.chyngyz.quizapp.data.QuizApiClient;
import com.chyngyz.quizapp.ui.mainFragment.MainFragment;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.models.Quiz;
import com.chyngyz.quizapp.ui.models.QuizResponse;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionViewModel extends ViewModel {
    private MutableLiveData<QuizResponse> mQuestions = new MutableLiveData<>();
    private MutableLiveData<String> toastMessageObserver = new MutableLiveData();

    public LiveData<String> getToastObserver(){
        return toastMessageObserver;
    }

    public LiveData<QuizResponse> getMQuestion(){
        return mQuestions;
    }

    public void getQuestionsFromBack(int amount, int category, String value) {
        QuizApiClient.getInstance().getQuestions(amount, category, value).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mQuestions.setValue(response.body());
                    for (int i = 0; i < mQuestions.getValue().getResults().size(); i++) {
                        mQuestions.getValue().getResults().get(i).getIncorrect_answers().add(mQuestions.getValue().getResults().get(i).getCorrect_answer());
                        Collections.shuffle(mQuestions.getValue().getResults().get(i).getIncorrect_answers());
                    }
                }
            }
            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
                toastMessageObserver.setValue(t.getMessage());
            }
        });
    }


}
