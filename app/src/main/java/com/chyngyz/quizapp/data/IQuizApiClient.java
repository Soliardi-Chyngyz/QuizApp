package com.chyngyz.quizapp.data;

import com.chyngyz.quizapp.core.IBaseCallBack;
import com.chyngyz.quizapp.ui.models.Question;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionsCallBask questionsCallBask, int amount, int categories, String difficulty);

    interface  QuestionsCallBask extends IBaseCallBack<List<Question>> {
        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception e);
    }
}
