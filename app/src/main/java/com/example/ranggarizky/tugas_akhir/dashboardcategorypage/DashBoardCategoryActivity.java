package com.example.ranggarizky.tugas_akhir.dashboardcategorypage;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.categorypage.CategoryPresenter;
import com.example.ranggarizky.tugas_akhir.categorypage.CategoryRecyclerViewAdapter;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardCategoryActivity extends AppCompatActivity implements DashboardCategoryView {
    DashboardCategoryPresenter presenter;
    SessionManager sessionManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private List<Category> categories = new ArrayList<>();
    private HorizCategoryRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_category);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        initPresenter();
        onAttachView();
        initRecyclerVIew();
        presenter.loadData();
    }


    private void initPresenter() {
        presenter = new DashboardCategoryPresenter();
    }

    private void initRecyclerVIew(){
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new HorizCategoryRecyclerViewAdapter(this, categories);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onAttachView() {
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
    public void showProgresBar() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateRecyclerView(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgresBar() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
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
