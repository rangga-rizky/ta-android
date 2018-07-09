package com.example.ranggarizky.tugas_akhir.createcategorypage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.createkeywordpage.CreateKeywordView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.ResponsePost;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public class CreateCategoryPresenter implements Presenter<CreateCategoryView> {
    private CreateCategoryView mView;

    @Override
    public void onAttach(final CreateCategoryView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void submitData(String category){
        mView.showProgressdialog();

        API apiService = API.client.create(API.class);
        Call<ResponsePost> call = apiService.createCategory(mView.getSessionManager().getToken(),"1",category);

        //proses call
        call.enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
               if(mView !=null){
                   if(response.code() == 200){
                       mView.finishActiivity();
                   }else if(response.code() == 401){
                       mView.showToast("Token Expored");
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
