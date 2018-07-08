package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class ResponseDocument {
    @SerializedName("data")
    @Expose
    private List<Document> data = null;
    @SerializedName("meta")
    @Expose
    private DocumentMeta meta;
    @SerializedName("paginator")
    @Expose
    private Paginator paginator;

    public List<Document> getData() {
        return data;
    }

    public void setData(List<Document> data) {
        this.data = data;
    }

    public DocumentMeta getMeta() {
        return meta;
    }

    public void setMeta(DocumentMeta meta) {
        this.meta = meta;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

}
