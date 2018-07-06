package com.example.ranggarizky.tugas_akhir.settingpage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public interface SettingView extends BaseView {
    void showToast(String message);
    void showProgressdialog();
    void hideProgressdialog();
    void toLoginScreen();
    SessionManager getSessionManager();
}