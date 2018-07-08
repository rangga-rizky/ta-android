package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class Document {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("tweet")
    @Expose
    private String tweet;
    @SerializedName("predicted")
    @Expose
    private String predicted;
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getPredicted() {
        return predicted;
    }

    public void setPredicted(String predicted) {
        this.predicted = predicted;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
