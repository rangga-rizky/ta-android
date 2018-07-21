package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public class Term {
    @SerializedName("term")
    @Expose
    private String term;

    @SerializedName("score")
    @Expose
    private int score;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("id")
    @Expose
    private String id;


    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
