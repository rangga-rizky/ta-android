package com.example.ranggarizky.tugas_akhir.databarupage;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.keywordpage.KeywordPresenter;
import com.example.ranggarizky.tugas_akhir.keywordpage.TermRecyclerViewAdapter;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.Document;
import com.example.ranggarizky.tugas_akhir.model.DocumentMeta;
import com.example.ranggarizky.tugas_akhir.model.Paginator;
import com.example.ranggarizky.tugas_akhir.model.Term;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewDataActivity extends AppCompatActivity implements  NewDataView{
    NewDataPresenter presenter;
    SessionManager sessionManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtJumlahData)
    TextView txtjumlahData;
    @BindView(R.id.txtlastData)
    TextView txtlastData;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    LinearLayoutManager layoutManager;
    private boolean isPending;
    private int maxItemsPerRequest = 35,currentPage = 1,totalPage = 2;
    private List<Document> documents = new ArrayList<>();
    private DocumentRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        initPresenter();
        onAttachView();
        initRecyclerVIew();
        presenter.loadData("1");
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
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(createInfiniteScrollListener());
        mAdapter = new DocumentRecyclerViewAdapter(this, documents);
        recyclerView.setAdapter(mAdapter);
    }

    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(maxItemsPerRequest, layoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                if (currentPage < totalPage && !isPending) {
                    presenter.loadData(String.valueOf(currentPage));
                }
            }
        };
    }

    @Override
    public void setPending(Boolean isPending) {
        this.isPending = isPending;
    }

    @Override
    public void setTotalPage(Paginator paginator) {
        totalPage = paginator.getTotalPages();
        currentPage++;
    }

    private void initPresenter() {
        presenter = new NewDataPresenter();
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
    public void updateRecyclerView(List<Document> documents) {
        this.documents.addAll(documents);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void setRecyclerView(List<Document> documents) {
        this.documents.clear();
        this.documents.addAll(documents);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void setMetaData(DocumentMeta data) {
        txtjumlahData.setText(data.getNumberOfData().toString()+" Data");
        txtlastData.setText(data.getLatestData());
    }

    @Override
    public void hideProgresBar() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgresBar() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
