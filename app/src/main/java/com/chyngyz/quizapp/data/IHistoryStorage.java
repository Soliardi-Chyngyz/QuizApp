package com.chyngyz.quizapp.data;

import androidx.lifecycle.LiveData;
import com.chyngyz.quizapp.ui.models.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    void saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    void delete(long id);

    void deleteAll();
}
