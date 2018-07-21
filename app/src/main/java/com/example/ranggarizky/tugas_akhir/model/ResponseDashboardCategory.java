package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RanggaRizky on 7/10/2018.
 */

public class ResponseDashboardCategory {


    @SerializedName("top-words")
    @Expose
    private List<TopWords> topWords = null;

    @SerializedName("frekuensi")
    @Expose
    private FreqData freqData;

    public void setFreqData(FreqData freqData) {
        this.freqData = freqData;
    }

    public void setTopWords(List<TopWords> topWords) {
        this.topWords = topWords;
    }

    public FreqData getFreqData() {
        return freqData;
    }

    public List<TopWords> getTopWords() {
        return topWords;
    }
}
