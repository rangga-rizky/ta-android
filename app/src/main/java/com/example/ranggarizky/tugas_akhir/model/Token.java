package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public class Token {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("last_login")
    @Expose
    private String last_login;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getLast_login() {
        return last_login;
    }
}
