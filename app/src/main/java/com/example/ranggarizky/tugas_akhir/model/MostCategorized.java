package com.example.ranggarizky.tugas_akhir.model;

/**
 * Created by RanggaRizky on 7/5/2018.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostCategorized {

    @SerializedName("jumlah")
    @Expose
    private Integer jumlah;
    @SerializedName("predicted")
    @Expose
    private String predicted;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("y")
    @Expose
    private Float y;

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getPredicted() {
        return predicted;
    }

    public void setPredicted(String predicted) {
        this.predicted = predicted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public Float getY() {
        return y;
    }
}