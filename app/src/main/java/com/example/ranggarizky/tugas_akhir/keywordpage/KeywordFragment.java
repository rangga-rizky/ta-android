package com.example.ranggarizky.tugas_akhir.keywordpage;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.createkeywordpage.CreateKeywordActivity;
import com.example.ranggarizky.tugas_akhir.OnSelectCategoryListener;
import com.example.ranggarizky.tugas_akhir.createkeywordpage.SpinnerCategoryAdapter;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.Paginator;
import com.example.ranggarizky.tugas_akhir.model.Term;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeywordFragment extends Fragment  implements KeywordView,TermRecyclerViewAdapter.OnItemClicked {
    private final int SUCCESS = 200;
    KeywordPresenter presenter;
    SessionManager sessionManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtWarning)
    TextView txtWarning;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.editSearch)
    EditText editSearch;
    LinearLayoutManager layoutManager;
    private boolean isPending;
    private int maxItemsPerRequest = 15,currentPage = 1,totalPage = 2;
    private List<Term> terms = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private TermRecyclerViewAdapter mAdapter;
    private SpinnerCategoryAdapter spinnerAdapter;
    private String currentCategory = "";

    public KeywordFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.fab)
    public void toCreateTerms(View view){
        Intent intent = new Intent(getActivity(),CreateKeywordActivity.class);
        startActivityForResult(intent,SUCCESS);
    }

    @OnClick(R.id.btnFilter)
    public void showFilterDialog(View view){
        CategoryDialog filterDialog =new CategoryDialog(getContext(), spinnerAdapter, new OnSelectCategoryListener() {
            @Override
            public void onSelect(Category category) {
                currentCategory = category.getId();
                currentPage = 1;
                presenter.loadData("1",currentCategory);
            }
        });
        filterDialog.show();
        presenter.loadCategory();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);
        ButterKnife.bind(this,view);
        sessionManager = new SessionManager(getContext());
        initPresenter();
        onAttachView();
        initRecyclerVIew();
        spinnerAdapter = new SpinnerCategoryAdapter(
                getContext(), android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        presenter.loadData("1",currentCategory);
        return view;
    }

    private void initPresenter() {
        presenter = new KeywordPresenter();
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
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(createInfiniteScrollListener());
        mAdapter = new TermRecyclerViewAdapter(getActivity(), terms);
        mAdapter.setOnClick(this);
        recyclerView.setAdapter(mAdapter);
    }

    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(maxItemsPerRequest, layoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                if (currentPage < totalPage && !isPending) {
                    presenter.loadData(String.valueOf(currentPage),currentCategory);
                }
            }
        };
    }

    @OnTextChanged(value = R.id.editSearch, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void editSearchChanged(CharSequence text) {
        currentCategory = "";
        if(text.length() > 2){
            presenter.searchKeyword(text.toString());
        }else if(text.length() == 0){
            hideEmptyResult();
            presenter.loadData("1",currentCategory);
        }
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgresBar() {
        recyclerView.setVisibility(View.GONE);
        editSearch.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyResult() {
        txtWarning.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyResult() {
        txtWarning.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
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

    @Override
    public void setRecyclerView(List<Term> terms) {
        this.terms.clear();
        this.terms.addAll(terms);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateRecyclerView(List<Term> terms) {
        this.terms.addAll(terms);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCategoriesSpinner(List<Category> categories) {
        this.categories.clear();
        Category allCategory = new Category();
        allCategory.setId("");
        allCategory.setCategory("Semua Kategori");
        this.categories.add(allCategory);
        this.categories.addAll(categories);
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteReyclerViewItem(int position) {
        this.terms.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgresBar() {
        editSearch.setEnabled(true);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == SUCCESS){
            showToast("Kata Baru berhasil ditambahkan");
            currentCategory = "";
            presenter.loadData("1",currentCategory);
        }
    }

    @Override
    public void onItemClick(final int position) {
        new AlertDialog.Builder(getContext())
                .setTitle("Hapus Kata kunci")
                .setMessage("Apakah anda yakin?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Term term = terms.get(position);
                        presenter.deleteTerms(term.getId(),position);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}
