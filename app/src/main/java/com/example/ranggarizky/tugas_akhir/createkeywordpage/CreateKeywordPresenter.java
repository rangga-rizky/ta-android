package com.example.ranggarizky.tugas_akhir.createkeywordpage;

import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
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

public class CreateKeywordPresenter implements Presenter<CreateKeywordView> {
    private CreateKeywordView mView;

    @Override
    public void onAttach(final CreateKeywordView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void submitData(String term,String category_id){
        mView.showProgressdialog();

        API apiService = API.client.create(API.class);
        Call<ResponsePost> call = apiService.createTerms(mView.getSessionManager().getToken(),"1",term,category_id);

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

    public void loadCategory(){
        API apiService = API.client.create(API.class);
        Call<List<Category>> call = apiService.getCategories(mView.getSessionManager().getToken());

        //proses call
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(mView!=null){
                    if(response.code() == 200){
                        mView.setCategorySpinner(response.body());
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
