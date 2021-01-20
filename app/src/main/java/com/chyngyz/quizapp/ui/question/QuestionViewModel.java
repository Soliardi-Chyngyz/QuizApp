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
    private ArrayList<Question> questions = new ArrayList<>();


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
        mQuestions.setValue(result);
    }

    @Override
    public void onFailure(Exception e) {
    }

    public void onAnswersClick(int questionsPosition){

    }
}