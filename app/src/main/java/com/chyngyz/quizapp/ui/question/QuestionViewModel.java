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
    private MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    private ArrayList<Question> questions = new ArrayList<>();

    public LiveData<Integer> getCurrentQuestionPosition() {
        return currentQuestionPosition;
    }

    public LiveData<String> getToastObserver() {
        return toastMessageObserver;
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

    public void moveToQuestionOnFinish(int position) {
        if(position == questions.size() - 1){
            finishQuiz();
        } else {
            currentQuestionPosition.setValue(position);
        }
    }

    private void finishQuiz() {

    }

    void onSkip(){}

    void onBackPressed(){}

    public void onAnswersClick(int questionsPosition, int answerPosition) {
        if (questions == null) {
            return;
        }
        Question question = questions.get(questionsPosition);
        question.setSelectAnswerPosition(answerPosition);
        questions.add(questionsPosition, question);
        mQuestions.setValue(questions);
        moveToQuestionOnFinish(questionsPosition);
    }
}