package com.example.ranggarizky.tugas_akhir.dashboardcategorypage;

import android.widget.TextView;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.FreqData;
import com.example.ranggarizky.tugas_akhir.model.TopWords;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.List;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public interface DashboardCategoryView extends BaseView {
    void showProgresBar();
    void updateRecyclerView(List<Category> categories);
    void setWordCloud(List<TopWords> topWords);
    void setLineChart(FreqData data);
    void setTitle(String string);
    void hideProgresBar();
    SessionManager getSessionManager();
    void showToast(String message);

}
