package com.chyngyz.quizapp.ui.historyFragment;

import com.chyngyz.quizapp.data.IHistoryStorage;
import com.chyngyz.quizapp.ui.models.QuizResult;

public class HistoryStorage implements IHistoryStorage {
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
