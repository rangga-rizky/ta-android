package com.example.ranggarizky.tugas_akhir.loginpage;

import android.content.Intent;
import android.util.Log;

import com.example.ranggarizky.tugas_akhir.API;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.mainpage.MainActivity;
import com.example.ranggarizky.tugas_akhir.model.ResponseLogin;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public class LoginPresenter implements Presenter<LoginView> {
    private LoginView mView;

    @Override
    public void onAttach(final LoginView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void login(String username,String password) {
        mView.showProgressdialog();

        API apiService = API.client.create(API.class);
        Call<ResponseLogin> call = apiService.login(username,password);

        //proses call
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.code() == 200){
                    mView.showToast("Sukses");
                    setSession(response.body());
                    mView.toHomeScreen();
                }else if(response.code() == 401){
                    mView.showToast("Password dan Email tidak cocok");
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
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e("ayam", "onFailure: ", t.fillInStackTrace());
                mView.showToast("Failed to Connect Internet");
                mView.hideProgressdialog();

            }
        });
    }

    private void setSession(ResponseLogin loginObject){
        SessionManager sessionManager = mView.getSessionManager();
        sessionManager.setLastLogin(loginObject.getTokenData().getLast_login());
        sessionManager.setToken("Bearer "+loginObject.getTokenData().getToken());
        sessionManager.setLogin(true);
    }

    public void isLogin(SessionManager session){
        if(session.isLogin()){
            mView.toHomeScreen();
        }
    }
}
