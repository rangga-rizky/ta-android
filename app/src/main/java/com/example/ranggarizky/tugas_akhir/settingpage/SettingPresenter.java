package com.example.ranggarizky.tugas_akhir.settingpage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.Presenter;
import com.example.ranggarizky.tugas_akhir.mainpage.DashboardView;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public class SettingPresenter implements Presenter<SettingView> {
    private SettingView mView;

    @Override
    public void onAttach(final SettingView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void logout(){
        SessionManager sessionManager = mView.getSessionManager();
        sessionManager.setToken(null);
        sessionManager.setLogin(false);
        mView.toLoginScreen();
    }

}
