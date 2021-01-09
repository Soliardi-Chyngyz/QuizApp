package com.chyngyz.quizapp.ui.models;

import java.util.Objects;

public class Quiz {
    private String question;
    private Boolean answerTrue = null;
    private String v1;
    private String v2;
    private String v3;
    private String v4;

    public Quiz(String question,  String v1, String v2, String v3, String v4) {
        this.question = question;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
    }

    public Quiz(String question, Boolean answerTrue) {
        this.question = question;
        this.answerTrue = answerTrue;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(Boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "question='" + question + '\'' +
                ", answerTrue=" + answerTrue +
                ", v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                ", v3='" + v3 + '\'' +
                ", v4='" + v4 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(question, quiz.question) &&
                Objects.equals(answerTrue, quiz.answerTrue) &&
                Objects.equals(v1, quiz.v1) &&
                Objects.equals(v2, quiz.v2) &&
                Objects.equals(v3, quiz.v3) &&
                Objects.equals(v4, quiz.v4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answerTrue, v1, v2, v3, v4);
    }
}
