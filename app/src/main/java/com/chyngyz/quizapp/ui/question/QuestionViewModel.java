package com.chyngyz.quizapp.ui.question;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.interfaces.ShortCountDownTimer;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.models.QuizResponse;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel extends ViewModel implements IQuizApiCallBack.QuestionsCallBask {

    private MutableLiveData<ArrayList<Question>> mQuestions = new MutableLiveData<>();
    private final MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>(0);
    private ArrayList<Question> questions = new ArrayList<>();

    public LiveData<Integer> getCurrentQuestionPosition() {
        return currentQuestionPosition;
    }

    public LiveData<ArrayList<Question>> getMQuestion() {
        return mQuestions;
    }

    public void getQuestionsFromBack(int amount, int category, String difficulty) {
        QuizApp.getInstance().getQuizRepository().getQuestions(amount, category, difficulty, this);
    }

    @Override
    public void onSuccess(ArrayList<Question> result) {
        if (result != null) {
            mQuestions.setValue(result);
            questions = result;
        }
    }

    @Override
    public void onFailure(Exception e) {
    }

    public void moveToQuestionOnFinish(int questionsPosition) {
        if(questionsPosition == mQuestions.getValue().size() - 1 ){
            finishQuiz();
        } else {
            new ShortCountDownTimer(500, 500) {
                @Override
                public void onFinish() {
                    currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
                }
            }.start();
        }
    }

    private void finishQuiz() {

    }

    public void onSkip() {
        if (currentQuestionPosition.getValue() == mQuestions.getValue().size() - 1) {
            lastAnswerClick();
        } else {
            currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
        }
    }

    private void lastAnswerClick() {

    }

    void onBackPressed() {
    }

    public void onAnswersClick(int questionsPosition, int answerPosition) {
        if (questions == null) {
            return;
        }

        moveToQuestionOnFinish(questionsPosition);
    }
}
/* Question question = questions.get(questionsPosition);
        question.setSelectAnswerPosition(answerPosition);
        questions.add(questionsPosition, question);
        mQuestions.setValue(questions);*/