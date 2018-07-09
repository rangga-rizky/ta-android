package com.example.ranggarizky.tugas_akhir.categorypage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.databarupage.NewDataView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.ResponseDocument;
import com.example.ranggarizky.tugas_akhir.model.ResponsePost;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class CategoryPresenter implements Presenter<CategoryView> {
    private CategoryView mView;

    @Override
    public void onAttach(CategoryView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void loadData(){
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

    public void deleteCategory(String id, final int position) {
        mView.showProgresBar();
        API apiService = API.client.create(API.class);
        Call<ResponsePost> call = apiService.deleteCategories(mView.getSessionManager().getToken(), id);

        //proses call
        call.enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if (mView != null) {
                    mView.hideProgresBar();
                    if (response.code() == 200) {
                        mView.showToast("Kategori Berhasil di hapus");
                        mView.deleteReyclerViewItem(position);
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
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                mView.showToast("Failed to Connect Internet");
                mView.hideProgresBar();

            }
        });

    }

}

