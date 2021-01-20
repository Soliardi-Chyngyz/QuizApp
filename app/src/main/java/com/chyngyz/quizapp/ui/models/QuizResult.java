package com.chyngyz.quizapp.ui.models;

import android.provider.ContactsContract;

import java.util.Date;
import java.util.List;

public class QuizResult  {
    private String category;
    private String difficulty;
    private int correctAnswer;
    private Date createdAt;
    private List<Question> questionList;

    public QuizResult(String category, String difficulty, int correctAnswer, Date createdAt, List<Question> questionList) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.createdAt = createdAt;
        this.questionList = questionList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
