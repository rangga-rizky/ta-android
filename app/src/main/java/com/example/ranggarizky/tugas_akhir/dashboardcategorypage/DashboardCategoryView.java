package com.example.ranggarizky.tugas_akhir.dashboardcategorypage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.List;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public interface DashboardCategoryView extends BaseView {
    void showProgresBar();
    void updateRecyclerView(List<Category> categories);
    void hideProgresBar();
    SessionManager getSessionManager();
    void showToast(String message);
}
