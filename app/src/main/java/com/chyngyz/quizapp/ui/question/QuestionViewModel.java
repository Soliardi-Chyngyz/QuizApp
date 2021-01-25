package com.chyngyz.quizapp.ui.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.interfaces.ShortCountDownTimer;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.models.QuizResultNoRoom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.chyngyz.quizapp.core.StaticMethods.CORRECT_AND_FINAL_ANSWER;
import static com.chyngyz.quizapp.core.StaticMethods.CORRECT_ANSWER;
import static com.chyngyz.quizapp.core.StaticMethods.WRONG_AND_FINAL_ANSWER;

public class QuestionViewModel extends ViewModel implements IQuizApiCallBack.QuestionsCallBask {

    private final MutableLiveData<ArrayList<Question>> mQuestions = new MutableLiveData<>();
    private final MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>(0);
    private final MutableLiveData<QuizResultNoRoom> quizResultNoRoom = new MutableLiveData<>();
    private final List<String> answers = new ArrayList<>();
    private final MutableLiveData<Boolean> isFinish = new MutableLiveData<>(false);
    private int correctAnswerAmount = 0;

    public LiveData<Integer> getCurrentQuestionPosition() {
        return currentQuestionPosition;
    }

    public void setCurrentQuestionPosition(int pos) {
        currentQuestionPosition.setValue(pos);
    }

    public LiveData<ArrayList<Question>> getMQuestion() {
        return mQuestions;
    }

    public void getQuestionsFromBack(int amount, int category, String difficulty) {
        QuizApp.getInstance().getQuizRepository().getQuestions(amount, category, difficulty, this);
    }

    public LiveData<QuizResultNoRoom> getQuizResultNoRoom() {
        return quizResultNoRoom;
    }

    @Override
    public void onSuccess(ArrayList<Question> result) {
        if (result != null) {
            mQuestions.setValue(result);
        }
    }

    @Override
    public void onFailure(Exception e) {
    }


    public void onSkip() {
        if (currentQuestionPosition.getValue() == Objects.requireNonNull(mQuestions.getValue()).size() - 1) {
            onLastAnswerClick("", "");
        } else {
            mQuestions.getValue().get(currentQuestionPosition.getValue()).getIsSkipClicked().setValue(true);
            new ShortCountDownTimer(500, 500) {
                @Override
                public void onFinish() {
                    super.onFinish();
                    currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
                }
            }.start();
        }
    }

    public void onAnswerClick(int result, String answer, String title, String valueOfDifficult) {
        answers.add(answer);
        if (result == CORRECT_AND_FINAL_ANSWER) {
            correctAnswerAmount++;
            onLastAnswerClick(title, valueOfDifficult);
        } else if (result == CORRECT_ANSWER) {
            correctAnswerAmount++;
        } else if (result == WRONG_AND_FINAL_ANSWER) {
            onLastAnswerClick(title, valueOfDifficult);
        }

        new ShortCountDownTimer(500, 500) {
            @Override
            public void onFinish() {
                currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
            }
        }.start();
    }

    private void onLastAnswerClick(String title, String valueOfDifficult) {
        int amountOfQuestions = Objects.requireNonNull(mQuestions.getValue()).size();
        Date dateObj = new Date(System.currentTimeMillis());
        int result = (int) (((float) correctAnswerAmount / (float) amountOfQuestions) * 100);
        quizResultNoRoom.setValue(new QuizResultNoRoom(title, valueOfDifficult,
                correctAnswerAmount + "/" + amountOfQuestions, dateObj, result
        ));
    }

    public void onBackImgClick() {
        if (currentQuestionPosition.getValue() != null) {
            if (currentQuestionPosition.getValue() <= 0)
                isFinish.setValue(true);
            currentQuestionPosition.setValue(currentQuestionPosition.getValue() - 1);
        }
    }
}
/* Question question = questions.get(questionsPosition);
        question.setSelectAnswerPosition(answerPosition);
        questions.add(questionsPosition, question);
        mQuestions.setValue(questions);*/