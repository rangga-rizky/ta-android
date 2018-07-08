package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class DocumentMeta {
    @SerializedName("number_of_data")
    @Expose
    private Integer numberOfData;
    @SerializedName("latest_data")
    @Expose
    private String latestData;

    public Integer getNumberOfData() {
        return numberOfData;
    }

    public void setNumberOfData(Integer numberOfData) {
        this.numberOfData = numberOfData;
    }

    public String getLatestData() {
        return latestData;
    }

    public void setLatestData(String latestData) {
        this.latestData = latestData;
    }
}
