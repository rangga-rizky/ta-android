package com.example.ranggarizky.tugas_akhir;

import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;
import com.example.ranggarizky.tugas_akhir.model.ResponseDocument;
import com.example.ranggarizky.tugas_akhir.model.ResponsePost;
import com.example.ranggarizky.tugas_akhir.model.ResponseTerm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
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
    public Call<ResponsePost> login(@Field("email") String uid, @Field("password")String pass);

    @GET("mobile/dashboard")
    public Call<ResponseDashboard> dashboard(@Header("Authorization")String token);

    @FormUrlEncoded
    @POST("terms")
    public Call<ResponsePost> createTerms(@Header("Authorization")String token,
                                          @Field("project_id")String project_id,
                                          @Field("term")String term,
                                          @Field("category_id")String category_id);

    @FormUrlEncoded
    @POST("categories")
    public Call<ResponsePost> createCategory(@Header("Authorization")String token,
                                          @Field("project_id")String project_id,
                                          @Field("category")String category);

    @GET("terms")
    public Call<ResponseTerm> getTerms(@Header("Authorization")String token,
                                       @Query("page") String page,
                                       @Query("category_id") String category_id);

    @GET("complaints")
    public Call<ResponseDocument> getComplaints(@Header("Authorization")String token,
                                                @Query("page") String page);

    @GET("categories")
    public Call<List<Category>> getCategories(@Header("Authorization")String token);

    @GET("terms/search/{q}")
    public Call<ResponseTerm> searcHterms(@Header("Authorization")String token, @Path("q") String page);

    @DELETE("terms/{id}")
    public Call<ResponsePost> deleteTerms(@Header("Authorization")String token, @Path("id") String id);

    @DELETE("categories/{id}")
    public Call<ResponsePost> deleteCategories(@Header("Authorization")String token, @Path("id") String id);

}