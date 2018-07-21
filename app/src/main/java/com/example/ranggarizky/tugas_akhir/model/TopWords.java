package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RanggaRizky on 7/10/2018.
 */

public class TopWords {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("weight")
    @Expose
    private Integer weight;

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }
}
