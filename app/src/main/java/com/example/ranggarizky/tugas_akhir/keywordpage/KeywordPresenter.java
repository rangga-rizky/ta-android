package com.example.ranggarizky.tugas_akhir.keywordpage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.mainpage.DashboardView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;
import com.example.ranggarizky.tugas_akhir.model.ResponsePost;
import com.example.ranggarizky.tugas_akhir.model.ResponseTerm;
import com.example.ranggarizky.tugas_akhir.model.Term;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public class KeywordPresenter implements Presenter<KeywordView> {
    private KeywordView mView;

    @Override
    public void onAttach(final KeywordView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void loadData(String page,String category){
            mView.showProgresBar();
            API apiService = API.client.create(API.class);
            Call<ResponseTerm> call = apiService.getTerms(mView.getSessionManager().getToken(),page,category);

            //proses call
            call.enqueue(new Callback<ResponseTerm>() {
                @Override
                public void onResponse(Call<ResponseTerm> call, Response<ResponseTerm> response) {
                    if(mView!=null){
                        if(response.code() == 200){
                            mView.updateRecyclerView(response.body().getData());
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
                        mView.hideProgresBar();
                    }
                }


                @Override
                public void onFailure(Call<ResponseTerm> call, Throwable t) {
                    Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                    mView.showToast("Failed to Connect Internet");
                    mView.hideProgresBar();

                }
            });

    }

    public void searchKeyword(String q){
            mView.showProgresBar();
            mView.hideEmptyResult();
            API apiService = API.client.create(API.class);
            Call<ResponseTerm> call = apiService.searcHterms(mView.getSessionManager().getToken(),q);

            //proses call
            call.enqueue(new Callback<ResponseTerm>() {
                @Override
                public void onResponse(Call<ResponseTerm> call, Response<ResponseTerm> response) {
                    if(mView!=null) {
                        mView.hideProgresBar();
                        if (response.code() == 200) {
                            List<Term> responseData = response.body().getData();
                            if(responseData.size() > 0){
                                mView.hideEmptyResult();
                            }else{
                                mView.showEmptyResult();
                            }
                            mView.updateRecyclerView(responseData);
                        } else if (response.code() == 401) {
                            mView.showToast("Token Expired");
                        } else {
                            try {
                                Log.d("ayam", response.errorBody().string().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mView.showToast("Terdapat Kesalhan pada Server");
                        }
                    }
                }


                @Override
                public void onFailure(Call<ResponseTerm> call, Throwable t) {
                    Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                    mView.showToast("Failed to Connect Internet");
                    mView.hideProgresBar();

                }
            });
    }

    public void deleteTerms(String id, final int position){
        mView.showProgresBar();
        API apiService = API.client.create(API.class);
        Call<ResponsePost> call = apiService.deleteTerms(mView.getSessionManager().getToken(),id);

        //proses call
        call.enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if(mView!=null){
                    mView.hideProgresBar();
                    if(response.code() == 200){
                        mView.showToast("Kata Kunci Berhasil di hapus");
                        mView.deleteReyclerViewItem(position);
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
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                mView.showToast("Failed to Connect Internet");
                mView.hideProgresBar();

            }
        });

    }

    public void loadCategory(){
        API apiService = API.client.create(API.class);
        Call<List<Category>> call = apiService.getCategories(mView.getSessionManager().getToken());

        //proses call
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(mView!=null){
                    if(response.code() == 200){
                        mView.setCategoriesSpinner(response.body());
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

            }
        });
    }

}
