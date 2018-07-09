package com.example.ranggarizky.tugas_akhir.createcategorypage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.List;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public interface CreateCategoryView extends BaseView {
    void showToast(String message);
    void showProgressdialog();
    void hideProgressdialog();
    void finishActiivity();
    SessionManager getSessionManager();
}