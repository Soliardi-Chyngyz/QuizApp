package com.chyngyz.quizapp.ui.historyFragment;

import androidx.lifecycle.LiveData;
import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.data.IHistoryStorage;
import com.chyngyz.quizapp.ui.models.QuizResult;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {
    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public void saveQuizResult(QuizResult quizResult) {
        QuizApp.getInstance().getDatabase().quizDao().insert(quizResult);
    }

    @Override
    public LiveData <List<QuizResult>> getAll() {
        return QuizApp.getInstance().getDatabase().quizDao().getAll();
    }

    @Override
    public void delete(long id) {
        QuizApp.getInstance().getDatabase().quizDao().deleteById(id);
    }

    @Override
    public void deleteAll() {
        QuizApp.getInstance().getDatabase().quizDao().deleteAll();
    }
}
