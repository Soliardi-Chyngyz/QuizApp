package com.chyngyz.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.chyngyz.quizapp.data.IHistoryStorage;
import com.chyngyz.quizapp.data.room.QuizDataBase;
import com.chyngyz.quizapp.data.QuizApiService;
import com.chyngyz.quizapp.data.QuizRepository;
import com.chyngyz.quizapp.interfaces.IQuizApiClient;
import com.chyngyz.quizapp.ui.historyFragment.HistoryStorage;

public class QuizApp extends Application {
    private static QuizApp instance;
    private QuizRepository quizRepository;
    private QuizDataBase quizDataBase;
    private Prefs prefs;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        IQuizApiClient iQuizApiClient = new QuizApiService();
        IHistoryStorage historyStorage = new HistoryStorage();
        quizRepository = new QuizRepository(iQuizApiClient, historyStorage);
        quizDataBase = Room.databaseBuilder(this, QuizDataBase.class, "quiz.room")
                .fallbackToDestructiveMigration() //разрешить миграцию
                .allowMainThreadQueries()
                .build();
        prefs = new Prefs(this);
    }

    public static QuizApp getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository() {
        return quizRepository;
    }

    public QuizDataBase getDatabase() {
        return quizDataBase;
    }

    public Prefs getPrefs() {
        return prefs;
    }
}
