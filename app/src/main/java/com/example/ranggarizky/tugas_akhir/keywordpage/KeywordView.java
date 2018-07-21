package com.example.ranggarizky.tugas_akhir.keywordpage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.MostCategorized;
import com.example.ranggarizky.tugas_akhir.model.Paginator;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;
import com.example.ranggarizky.tugas_akhir.model.Term;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.List;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public interface KeywordView extends BaseView {
    void showProgresBar();
    void showEmptyResult();
    void hideEmptyResult();
    void setPending(Boolean isPending);
    void setTotalPage(Paginator paginator);
    void setRecyclerView(List<Term> terms);
    void updateRecyclerView(List<Term> terms);
    void setCategoriesSpinner(List<Category> categories);
    void deleteReyclerViewItem(int position);
    void hideProgresBar();
    SessionManager getSessionManager();
    void showToast(String message);
}
