package com.example.ranggarizky.tugas_akhir.dashboardcategorypage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.categorypage.CategoryView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.FreqData;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboardCategory;
import com.example.ranggarizky.tugas_akhir.model.ResponsePost;
import com.example.ranggarizky.tugas_akhir.model.TopWords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class DashboardCategoryPresenter implements Presenter<DashboardCategoryView> {
    private DashboardCategoryView mView;

    @Override
    public void onAttach(DashboardCategoryView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void loadCategories(){
        mView.showProgresBar();
        API apiService = API.client.create(API.class);
        Call<List<Category>> call = apiService.getCategories(mView.getSessionManager().getToken());

        //proses call
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(mView!=null){
                    mView.hideProgresBar();
                    if(response.code() == 200){
                        mView.setTitle(response.body().get(0).getCategory());
                        loadCategoryFreq(response.body().get(0).getSlug());
                        mView.updateRecyclerView(response.body());
                    }else if(response.code() == 401){
                        mView.showToast("Token Expired");
                    }else{
                        try {
                            Log.d("ayam",response.errorBody().string().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mView.showToast("Terdapat Kesalhan pada Server");
                    }
                }
            }


            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                mView.showToast("Failed to Connect Internet");
                mView.hideProgresBar();
            }
        });
    }

    public void loadCategoryFreq(String slug){
        mView.showProgresBar();
        API apiService = API.client.create(API.class);
        Call<ResponseDashboardCategory> call = apiService.getDashboardCategory(mView.getSessionManager().getToken(),slug);

        //proses call
        call.enqueue(new Callback<ResponseDashboardCategory>() {
            @Override
            public void onResponse(Call<ResponseDashboardCategory> call, Response<ResponseDashboardCategory> response) {
                if(mView!=null){
                    mView.hideProgresBar();
                    if(response.code() == 200){
                        mView.setLineChart(response.body().getFreqData());
                        mView.setWordCloud(response.body().getTopWords());
                    }else if(response.code() == 401){
                        mView.showToast("Token Expired");
                    }else{
                        try {
                            Log.d("ayam",response.errorBody().string().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mView.showToast("Terdapat Kesalhan pada Server");
                    }
                }
            }


            @Override
            public void onFailure(Call<ResponseDashboardCategory> call, Throwable t) {
                Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                mView.showToast("Failed to Connect Internet");
                mView.hideProgresBar();
            }
        });
    }

}

