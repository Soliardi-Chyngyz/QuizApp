package com.chyngyz.quizapp.data;

import android.util.Log;

import com.chyngyz.quizapp.ui.models.Quiz;
import com.chyngyz.quizapp.ui.models.QuizResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient implements IQuizApiClient {

    private QuizApiClient() {
    }

    private static QuizApi quizApi;

    public static QuizApi getInstance() {
        if (quizApi == null) {
            quizApi = buildRetrofit();
        }
        return quizApi;
    }

    private static QuizApi buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()) //он для того чтобы мог работать с моделькой
                .build()
                .create(QuizApi.class);
    }

    @Override
    public void getQuestions(QuestionsCallBask callBask, int amount, int categories, String difficulty) {
        Call<QuizResponse> call = quizApi.getQuestions(amount, categories, difficulty);

        Log.d("olala", "URL " + call.request().url());

        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callBask.onSuccess(response.body().getResults());
                    } else {
                        callBask.onFailure(new Exception("Response if empty " + response.code()));
                    }
                } else {
                    callBask.onFailure(new Exception("Response if empty " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                callBask.onFailure(new Exception(t));
            }
        });
    }
}
