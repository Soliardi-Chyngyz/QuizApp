package com.chyngyz.quizapp.ui.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.chyngyz.quizapp.data.room.converter.DateConverter;
import com.chyngyz.quizapp.data.room.converter.QuestionConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity(tableName = "QResult")
public class QuizResult implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "correct_answer")
    private String correctAnswer;
    @TypeConverters({DateConverter.class})
    private Date createdAt;
    @TypeConverters({QuestionConverter.class})
    private List<Question> questionList;


    public QuizResult(String category, String difficulty, String correctAnswer, Date createdAt, List<Question> questionList) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.createdAt = createdAt;
        this.questionList = questionList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
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
