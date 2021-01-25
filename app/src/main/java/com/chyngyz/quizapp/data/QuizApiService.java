package com.chyngyz.quizapp.data;

import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.interfaces.IQuizApiClient;
import com.chyngyz.quizapp.ui.models.Category;
import com.chyngyz.quizapp.ui.models.QuizResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiService implements IQuizApiClient {
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create()) //он для того чтобы мог работать с моделькой
            .build();
    private final QuizApi quizApi = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(int amount, int category, String difficulty, IQuizApiCallBack.QuestionsCallBask callBack) {
        Call<QuizResponse> call = quizApi.getQuestions(amount, category, difficulty);
        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(@NotNull Call<QuizResponse> call, @NotNull Response<QuizResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callBack.onSuccess(response.body().getResults());
                    } else {
                        callBack.onFailure(new Exception("Response if empty " + response.code()));
                    }
                } else {
                    callBack.onFailure(new Exception("Response if empty " + response.code()));
                }
            }
            @Override
            public void onFailure(@NotNull Call<QuizResponse> call, @NotNull Throwable t) {
                callBack.onFailure(new Exception(t));
            }
        });
    }

    @Override
    public void getCategories(IQuizApiCallBack.CategoriesCallBack callBask) {
        quizApi.getCategories()
                .enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(@NotNull Call<Category> call, @NotNull Response<Category> response) {
                        callBask.onSuccess(response.body());
                    }
                    @Override
                    public void onFailure(@NotNull Call<Category> call, @NotNull Throwable t) {
                        callBask.onFailure((Exception) t);
                    }
                });
    }
}
