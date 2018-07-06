package com.example.ranggarizky.tugas_akhir.keywordpage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.mainpage.DashboardView;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;
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

    public void loadData(String page){
        if(mView != null){
            mView.showProgresBar();
            API apiService = API.client.create(API.class);
            Call<ResponseTerm> call = apiService.getTerms(mView.getSessionManager().getToken(),page);

            //proses call
            call.enqueue(new Callback<ResponseTerm>() {
                @Override
                public void onResponse(Call<ResponseTerm> call, Response<ResponseTerm> response) {
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


                @Override
                public void onFailure(Call<ResponseTerm> call, Throwable t) {
                    Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                    mView.showToast("Failed to Connect Internet");
                    mView.hideProgresBar();

                }
            });
        }
    }
}
