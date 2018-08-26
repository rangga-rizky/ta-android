package com.example.ranggarizky.tugas_akhir.databarupage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.keywordpage.KeywordView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.ResponseDocument;
import com.example.ranggarizky.tugas_akhir.model.ResponsePost;
import com.example.ranggarizky.tugas_akhir.model.ResponseTerm;

import java.io.IOException;
import java.util.List;

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

    public void loadData(final String page,String category_id){
        if(page == "1"){
            mView.showProgresBar();
        }
        mView.setPending(true);
        API apiService = API.client.create(API.class);
        Call<ResponseDocument> call = apiService.getComplaints(mView.getSessionManager().getToken(),page,category_id);

        //proses call
        call.enqueue(new Callback<ResponseDocument>() {
            @Override
            public void onResponse(Call<ResponseDocument> call, Response<ResponseDocument> response) {
                if(mView!=null){
                    mView.setPending(false);
                    if(response.code() == 200){
                        if(page != "1"){
                            mView.updateRecyclerView(response.body().getData());
                        }else{
                            mView.setRecyclerView(response.body().getData());
                        }
                        mView.setTotalPage(response.body().getPaginator());
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
                mView.setPending(false);
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

    public void crawl() {
        mView.showProgressdialog();

        API apiService = API.client.create(API.class);
        Call<ResponsePost> call = apiService.crawl(mView.getSessionManager().getToken(),"10");

        //proses call
        call.enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if(response.code() == 200){
                    mView.showToast(response.body().getMessages());
                    loadData("1","");
                }else{
                    try {
                        Log.d("ayam",response.errorBody().string().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mView.showToast("Terdapat Kesalhan pada Server");
                }
                mView.hideProgressdialog();
            }


            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                mView.showToast("Failed to Connect Internet");
                mView.hideProgressdialog();

            }
        });
    }
}

