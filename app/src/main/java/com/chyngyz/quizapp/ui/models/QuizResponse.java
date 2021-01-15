package com.chyngyz.quizapp.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuizResponse {
    @SerializedName("response_mode")
    @Expose
    private int responseCode;

    @SerializedName("results")
    @Expose
    private ArrayList<Question> results;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<Question> getResults() {
        return results;
    }

    public void setResults(ArrayList<Question> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "QuizResponse{" +
                "responseCode=" + responseCode +
                ", results=" + results +
                '}';
    }
}
