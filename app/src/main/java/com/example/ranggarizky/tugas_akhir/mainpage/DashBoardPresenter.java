package com.example.ranggarizky.tugas_akhir.mainpage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public class DashBoardPresenter implements Presenter<DashboardView> {
    private DashboardView mView;

    @Override
    public void onAttach(final DashboardView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void loadDashboard(){
       if(mView != null){
           mView.showProgresBar();
           API apiService = API.client.create(API.class);
           Call<ResponseDashboard> call = apiService.dashboard(mView.getSessionManager().getToken());

           //proses call
           call.enqueue(new Callback<ResponseDashboard>() {
               @Override
               public void onResponse(Call<ResponseDashboard> call, Response<ResponseDashboard> response) {
                   if(response.code() == 200){
                       mView.setMeta(response.body());
                       mView.setLineChart(response.body().getFreqData());
                       mView.setpieChart(response.body().getDistCategory());
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
               public void onFailure(Call<ResponseDashboard> call, Throwable t) {
                   Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                   mView.showToast("Failed to Connect Internet");
                   mView.hideProgresBar();

               }
           });
       }
    }


}
