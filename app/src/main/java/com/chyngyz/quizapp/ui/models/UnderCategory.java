package com.chyngyz.quizapp.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UnderCategory implements Serializable {
    @SerializedName("id")
    @Expose
    private int id = 0;

    @SerializedName("name")
    @Expose
    private String name = "";

    public UnderCategory() {
    }

    public UnderCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + id;
    }
}
