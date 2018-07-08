package com.example.ranggarizky.tugas_akhir.mainpage;

import android.widget.TextView;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.MostCategorized;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public interface DashboardView extends BaseView {
    void setMeta(ResponseDashboard dashboard);
    void setpieChart(List<MostCategorized> entries);
    void toNewDataPage();
    void showProgresBar();
    void hideProgresBar();
    SessionManager getSessionManager();
    void showToast(String message);
}