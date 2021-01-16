package com.chyngyz.quizapp;

import android.app.Application;

import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.data.QuizApiService;
import com.chyngyz.quizapp.data.QuizRepository;
import com.chyngyz.quizapp.interfaces.IQuizApiClient;

public class QuizApp extends Application {
    private static QuizApp instance;
    private QuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        IQuizApiClient iQuizApiClient = new QuizApiService();
        quizRepository = new QuizRepository(iQuizApiClient);
    }

    public static QuizApp getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository(){
        return quizRepository;
    }
}
