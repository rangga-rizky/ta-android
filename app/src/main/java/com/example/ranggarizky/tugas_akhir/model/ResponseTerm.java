package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public class ResponseTerm {
    @SerializedName("data")
    @Expose
    private List<Term> data = null;
    @SerializedName("paginator")
    @Expose
    private Paginator paginator;

    public List<Term> getData() {
        return data;
    }

    public void setData(List<Term> data) {
        this.data = data;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
