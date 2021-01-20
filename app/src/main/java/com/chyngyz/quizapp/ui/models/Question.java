package com.chyngyz.quizapp.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class Question {
    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("difficulty")
    @Expose
    private String difficulty;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("correct_answer")
    @Expose
    private String correct_answer;

    @SerializedName("incorrect_answers")
    @Expose
    private ArrayList<String> incorrect_answers;


    private ArrayList<String> allSortedAnswers;

    public ArrayList<String> getAllSortedAnswers() {
        return allSortedAnswers;
    }

    public void setAllSortedAnswers(ArrayList<String> allSortedAnswers) {
        this.allSortedAnswers = allSortedAnswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(ArrayList<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(category, question1.category) &&
                Objects.equals(type, question1.type) &&
                Objects.equals(difficulty, question1.difficulty) &&
                Objects.equals(question, question1.question) &&
                Objects.equals(correct_answer, question1.correct_answer) &&
                Objects.equals(incorrect_answers, question1.incorrect_answers) &&
                Objects.equals(allSortedAnswers, question1.allSortedAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, type, difficulty, question, correct_answer, incorrect_answers, allSortedAnswers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", question='" + question + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", incorrect_answers=" + incorrect_answers +
                ", allSortedAnswers=" + allSortedAnswers +
                '}';
    }
}
