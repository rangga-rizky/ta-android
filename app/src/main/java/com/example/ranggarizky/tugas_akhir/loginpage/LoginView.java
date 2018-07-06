package com.example.ranggarizky.tugas_akhir.loginpage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public interface LoginView extends BaseView {
    void showToast(String message);
    void showProgressdialog();
    void hideProgressdialog();
    void toHomeScreen();
    SessionManager getSessionManager();
}