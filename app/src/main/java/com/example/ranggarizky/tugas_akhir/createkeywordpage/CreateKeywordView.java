package com.example.ranggarizky.tugas_akhir.createkeywordpage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.List;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public interface CreateKeywordView extends BaseView {
    void showToast(String message);
    void showProgressdialog();
    void hideProgressdialog();
    void finishActiivity();
    void setCategorySpinner(List<Category> data);
    SessionManager getSessionManager();
}