package com.chyngyz.quizapp;

import android.app.Application;

import com.chyngyz.quizapp.data.IHistoryStorage;
import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.data.QuizApiService;
import com.chyngyz.quizapp.data.QuizRepository;
import com.chyngyz.quizapp.interfaces.IQuizApiClient;
import com.chyngyz.quizapp.ui.historyFragment.HistoryStorage;

public class QuizApp extends Application {
    private static QuizApp instance;
    private static IHistoryStorage historyStorage;
    private QuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        IQuizApiClient iQuizApiClient = new QuizApiService();
        historyStorage = new HistoryStorage();
        quizRepository = new QuizRepository(iQuizApiClient, historyStorage);
    }

    public static QuizApp getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository(){
        return quizRepository;
    }
}
