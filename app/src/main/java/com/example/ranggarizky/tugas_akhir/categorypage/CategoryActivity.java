package com.example.ranggarizky.tugas_akhir.categorypage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.createcategorypage.CreateCategoryActivity;
import com.example.ranggarizky.tugas_akhir.createkeywordpage.SpinnerCategoryAdapter;
import com.example.ranggarizky.tugas_akhir.keywordpage.KeywordPresenter;
import com.example.ranggarizky.tugas_akhir.keywordpage.TermRecyclerViewAdapter;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.Term;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity implements CategoryView,CategoryRecyclerViewAdapter.OnItemClicked{
    private final int SUCCESS = 200;
    CategoryPresenter presenter;
    SessionManager sessionManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private List<Category> categories = new ArrayList<>();
    private CategoryRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        initPresenter();
        onAttachView();
        initRecyclerVIew();
        presenter.loadData();
    }

    private void initPresenter() {
        presenter = new CategoryPresenter();
    }

    private void initRecyclerVIew(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    fab.hide();
                } else{
                    fab.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new CategoryRecyclerViewAdapter(this, categories);
        mAdapter.setOnClick(this);
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
    public void toCreateCategory() {
        Intent intent = new Intent(this, CreateCategoryActivity.class);
        startActivityForResult(intent,SUCCESS);
    }

    @Override
    public void deleteReyclerViewItem(int position) {
        this.categories.remove(position);
        mAdapter.notifyDataSetChanged();
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

    @OnClick(R.id.fab)
    public void onClickFab(){
        this.toCreateCategory();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == SUCCESS){
            showToast("Kategori Baru berhasil ditambahkan");
            presenter.loadData();
        }
    }


    @Override
    public void onItemClick(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Kata kunci")
                .setMessage("Apakah anda yakin?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Category category = categories.get(position);
                        presenter.deleteCategory(category.getId(),position);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}
