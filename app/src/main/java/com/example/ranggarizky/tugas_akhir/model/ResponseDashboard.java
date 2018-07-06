package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RanggaRizky on 7/5/2018.
 */

public class ResponseDashboard {

    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("n_data")
    @Expose
    private Integer nData;
    @SerializedName("n_category")
    @Expose
    private Integer nCategory;
    @SerializedName("most_categorized")
    @Expose
    private List<MostCategorized> mostCategorized = null;

    @SerializedName("distCategory")
    @Expose
    private List<MostCategorized> distCategory = null;

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setNData(Integer nData) {
        this.nData = nData;
    }


    public void setNCategory(Integer nCategory) {
        this.nCategory = nCategory;
    }

    public List<MostCategorized> getMostCategorized() {
        return mostCategorized;
    }

    public void setMostCategorized(List<MostCategorized> mostCategorized) {
        this.mostCategorized = mostCategorized;
    }

    public void setDistCategory(List<MostCategorized> distCategory) {
        this.distCategory = distCategory;
    }

    public Integer getnCategory() {
        return nCategory;
    }

    public Integer getnData() {
        return nData;
    }

    public List<MostCategorized> getDistCategory() {
        return distCategory;
    }


}