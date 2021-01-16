package com.chyngyz.quizapp.ui.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.models.QuizResponse;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel extends ViewModel implements IQuizApiCallBack.QuestionsCallBask {
    private MutableLiveData<String> toastMessageObserver = new MutableLiveData();
    private MutableLiveData<ArrayList<Question>> mQuestions = new MutableLiveData<>();

    public LiveData<String> getToastObserver(){
        return toastMessageObserver;
    }

    public LiveData<ArrayList<Question>> getMQuestion(){
        return mQuestions;
    }

    public void getQuestionsFromBack(int amount, int category, String difficulty) {
        QuizApp.getInstance().getQuizRepository().getQuestions(amount, category, difficulty, this);
    }

    @Override
    public void onSuccess(ArrayList<Question> result) {
    }

    @Override
    public void onFailure(Exception e) {
    }
}

  /*.enqueue(new Callback<QuizResponse>() {
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
        });*/