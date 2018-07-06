package com.example.ranggarizky.tugas_akhir.loginpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.mainpage.MainActivity;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.example.ranggarizky.tugas_akhir.utils.Validation;
import com.xwray.passwordview.PasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {
    LoginPresenter presenter;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editPassword)
    PasswordView editPassword;
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        initPresenter();
        onAttachView();
    }

    @Override
    public void onAttachView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu");
        presenter.onAttach(this);
        presenter.isLogin(sessionManager);
    }

    @Override
    public SessionManager getSessionManager(){
        return  sessionManager;
    }

    private void initPresenter() {
        presenter = new LoginPresenter();
    }


    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @OnClick(R.id.btnLogin)
    public void onClikcLogin(){
        if(Validation.checkisEmail(editEmail) && Validation.checkPassword(this,editPassword)) {
            presenter.login(editEmail.getText().toString(), editPassword.getText().toString());
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    public void toHomeScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
