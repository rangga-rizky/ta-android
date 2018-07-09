package com.example.ranggarizky.tugas_akhir.categorypage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.Document;
import com.example.ranggarizky.tugas_akhir.model.DocumentMeta;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.List;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public interface CategoryView extends BaseView {
    void showProgresBar();
    void updateRecyclerView(List<Category> categories);
    void hideProgresBar();
    SessionManager getSessionManager();
    void showToast(String message);
    void toCreateCategory();
    void deleteReyclerViewItem(int position);;
}
