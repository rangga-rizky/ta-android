package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RanggaRizky on 7/10/2018.
 */

public class FreqData {
    @SerializedName("labels")
    @Expose
    private List<String> labels = null;

    @SerializedName("values")
    @Expose
    private List<Integer> values = null;

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public List<Integer> getValues() {
        return values;
    }

    public List<String> getLabels() {
        return labels;
    }
}
