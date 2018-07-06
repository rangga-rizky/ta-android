package com.example.ranggarizky.tugas_akhir;

import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;
import com.example.ranggarizky.tugas_akhir.model.ResponseLogin;
import com.example.ranggarizky.tugas_akhir.model.ResponseTerm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public interface API {
    //variable base URL
    public static String baseURL = "http://178.128.36.147/api/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
    Retrofit client = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @FormUrlEncoded
    @POST("login")
    public Call<ResponseLogin> login(@Field("email") String uid, @Field("password")String pass);

    @GET("mobile/dashboard")
    public Call<ResponseDashboard> dashboard(@Header("Authorization")String token);

    @GET("terms")
    public Call<ResponseTerm> getTerms(@Header("Authorization")String token, @Query("page") String page);

    @GET("terms/search/{q}")
    public Call<ResponseTerm> searcHterms(@Header("Authorization")String token, @Path("q") String page);

}