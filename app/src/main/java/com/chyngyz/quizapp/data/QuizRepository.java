package com.chyngyz.quizapp.data;

import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.interfaces.IQuizApiClient;
import com.chyngyz.quizapp.ui.models.Category;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.models.QuizResult;
import com.chyngyz.quizapp.ui.models.UnderCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient, IHistoryStorage {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;

    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
    }

    @Override
    public void getQuestions(int amount, int category, String difficulty, IQuizApiCallBack.QuestionsCallBask callBack) {
        quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiCallBack.QuestionsCallBask() {
            @Override
            public void onSuccess(ArrayList<Question> result) {
                addAnswers(result);
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void getCategories(IQuizApiCallBack.CategoriesCallBack callBask) {
        quizApiClient.getCategories(new IQuizApiCallBack.CategoriesCallBack() {
            @Override
            public void onSuccess(Category result) {
                callBask.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBask.onFailure(e);
            }
        });
    }

    private void addAnswers(List<Question> result) {
        for (int i = 0; i < result.size(); i++) {
            result.get(i).getIncorrect_answers().add(result.get(i).getCorrect_answer());
            Collections.shuffle(result.get(i).getIncorrect_answers());
        }
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
