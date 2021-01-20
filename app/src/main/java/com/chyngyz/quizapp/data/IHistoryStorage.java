package com.chyngyz.quizapp.data;

import com.chyngyz.quizapp.ui.models.QuizResult;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    void delete(int id);

    void deleteAll();
}
