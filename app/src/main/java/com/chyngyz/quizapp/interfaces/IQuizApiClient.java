package com.chyngyz.quizapp.interfaces;

public interface IQuizApiClient {
    void getQuestions(int amount, int category, String difficulty, IQuizApiCallBack.QuestionsCallBask callBack);

    void getCategories(IQuizApiCallBack.CategoriesCallBack callBask);
}
