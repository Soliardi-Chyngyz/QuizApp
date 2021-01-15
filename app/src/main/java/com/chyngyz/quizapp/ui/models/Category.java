package com.chyngyz.quizapp.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Category {
    @SerializedName("trivia_categories")
    @Expose
    private ArrayList<UnderCategory> trivia_categories;

    public ArrayList<UnderCategory> getTrivia_categories() {
        return trivia_categories;
    }

    public void setTrivia_categories(ArrayList<UnderCategory> trivia_categories) {
        this.trivia_categories = trivia_categories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "trivia_categories=" + trivia_categories +
                '}';
    }
}

