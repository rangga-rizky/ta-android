package com.example.ranggarizky.tugas_akhir.createkeywordpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.keywordpage.KeywordPresenter;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.Term;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.example.ranggarizky.tugas_akhir.utils.Validation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateKeywordActivity extends AppCompatActivity implements  CreateKeywordView{
    private CreateKeywordPresenter presenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private List<Category> categoryOptions;
    private List<String> scoreOptions;
    private final int SUCCESS = 200;
    @BindView(R.id.editKeyword)
    EditText editKeyword;
    @BindView(R.id.spinnerCategory)
    Spinner spinnerCategory;
    @BindView(R.id.spinnerScore)
    Spinner spinnerScore;
    private SpinnerCategoryAdapter adapter;
    private ArrayAdapter<String> scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_keyword);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        categoryOptions = new ArrayList<>();
        initPresenter();
        onAttachView();
        initScoreSpinner();
        adapter = new SpinnerCategoryAdapter(
                this, android.R.layout.simple_spinner_item, categoryOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        presenter.loadCategory();
    }

    private void initPresenter() {
        presenter = new CreateKeywordPresenter();
    }

    private void initScoreSpinner(){
        scoreOptions = new ArrayList<>();
        scoreOptions.add("MAX");
        scoreOptions.add("AVG");
        scoreOptions.add("MIN");
        scoreAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, scoreOptions);
        scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScore.setAdapter(scoreAdapter);
        spinnerScore.setSelection(0);
    }

    @Override
    public void onAttachView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Adding keyword and build matrice");
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
    public void createTerm(View view){
        if(Validation.checkEmpty(editKeyword)){
            Category selected = (Category) spinnerCategory.getSelectedItem();
            presenter.submitData(editKeyword.getText().toString(),selected.getId().toString(),spinnerScore.getSelectedItem().toString());
        }
    }

    @Override
    public void finishActiivity() {
        setResult(SUCCESS);
        finish();
    }

    @Override
    public void setCategorySpinner(List<Category> spinnerArray) {
        categoryOptions.clear();
        categoryOptions.addAll(spinnerArray);
        adapter.notifyDataSetChanged();
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
