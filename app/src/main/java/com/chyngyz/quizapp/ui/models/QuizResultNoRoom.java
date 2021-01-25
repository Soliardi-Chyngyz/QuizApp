package com.chyngyz.quizapp.ui.models;

import java.io.Serializable;
import java.util.Date;

public class QuizResultNoRoom implements Serializable {
    private String title;
    private String difficulty;
    private String answerCorrect;
    private Date date;
    private int result;

    public QuizResultNoRoom(String title, String difficulty, String answerCorrect, Date date, int result) {
        this.title = title;
        this.difficulty = difficulty;
        this.answerCorrect = answerCorrect;
        this.date = date;
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public Date getDate() {
        return date;
    }
}
