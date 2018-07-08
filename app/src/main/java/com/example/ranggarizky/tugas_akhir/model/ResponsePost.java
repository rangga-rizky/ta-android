package com.example.ranggarizky.tugas_akhir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public class ResponsePost {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Token TokenData;

    public void setError(String error) {
        this.error = error;
    }


    public void setTokenData(Token tokenData) {
        TokenData = tokenData;
    }

    public String getError() {
        return error;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public Token getTokenData() {
        return TokenData;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
