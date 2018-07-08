package com.example.ranggarizky.tugas_akhir.databarupage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.keywordpage.KeywordView;
import com.example.ranggarizky.tugas_akhir.model.ResponseDocument;
import com.example.ranggarizky.tugas_akhir.model.ResponseTerm;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class NewDataPresenter implements Presenter<NewDataView> {
    private NewDataView mView;

    @Override
    public void onAttach(NewDataView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void loadData(String page){
        mView.showProgresBar();
        API apiService = API.client.create(API.class);
        Call<ResponseDocument> call = apiService.getComplaints(mView.getSessionManager().getToken(),page);

        //proses call
        call.enqueue(new Callback<ResponseDocument>() {
            @Override
            public void onResponse(Call<ResponseDocument> call, Response<ResponseDocument> response) {
                if(mView!=null){
                    if(response.code() == 200){
                        mView.updateRecyclerView(response.body().getData());
                        mView.setMetaData(response.body().getMeta());
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
            public void onFailure(Call<ResponseDocument> call, Throwable t) {
                Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                mView.showToast("Failed to Connect Internet");
                mView.hideProgresBar();

            }
        });

    }
}

