package com.example.ranggarizky.tugas_akhir.createcategorypage;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.createkeywordpage.CreateKeywordPresenter;
import com.example.ranggarizky.tugas_akhir.createkeywordpage.SpinnerCategoryAdapter;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.example.ranggarizky.tugas_akhir.utils.Validation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateCategoryActivity extends AppCompatActivity implements  CreateCategoryView {
    private CreateCategoryPresenter presenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private final int SUCCESS = 200;
    @BindView(R.id.editCategory)
    EditText editCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        initPresenter();
        onAttachView();
    }

    private void initPresenter() {
        presenter = new CreateCategoryPresenter();
    }


    @Override
    public void onAttachView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu");
        presenter.onAttach(this);
    }


    @Override
    public void onDetachView() {
        presenter.onDetach();
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

    @OnClick(R.id.btnSubmit)
    public void createCategory(View view){
        if(Validation.checkEmpty(editCategory)){
            presenter.submitData(editCategory.getText().toString());
        }
    }

    @Override
    public void finishActiivity() {
        setResult(SUCCESS);
        finish();
    }

    @Override
    public SessionManager getSessionManager(){
        return  sessionManager;
    }


    @Override
    public void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

}
