package com.example.ranggarizky.tugas_akhir.settingpage;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.categorypage.CategoryActivity;
import com.example.ranggarizky.tugas_akhir.loginpage.LoginActivity;
import com.example.ranggarizky.tugas_akhir.mainpage.DashBoardPresenter;
import com.example.ranggarizky.tugas_akhir.mainpage.MainActivity;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.example.ranggarizky.tugas_akhir.utils.Validation;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements SettingView {

    SessionManager sessionManager;
    SettingPresenter presenter;
    private ProgressDialog progressDialog;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this,view);
        sessionManager = new SessionManager(getContext());
        initPresenter();
        onAttachView();
        return view;
    }

    private void initPresenter() {
        presenter = new SettingPresenter();
    }


    @Override
    public void onAttachView() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu");
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressdialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressdialog() {
        progressDialog.hide();
    }

    @Override
    public void toLoginScreen() {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void toCategoryListScreen() {
        Intent intent = new Intent(getActivity(),CategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    @Override
    public void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @OnClick(R.id.btnLogout)
    public void onClikcLogout(){
        presenter.logout();
    }

    @OnClick(R.id.btnCategory)
    public void onCLickCategory(){
        toCategoryListScreen();
    }
}
