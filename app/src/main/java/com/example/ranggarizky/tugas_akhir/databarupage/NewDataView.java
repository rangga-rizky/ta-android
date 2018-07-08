package com.example.ranggarizky.tugas_akhir.databarupage;

import com.example.ranggarizky.tugas_akhir.BaseView;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.Document;
import com.example.ranggarizky.tugas_akhir.model.DocumentMeta;
import com.example.ranggarizky.tugas_akhir.model.Term;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;

import java.util.List;

/**
 * Created by RanggaRizky on 7/6/2018.
 */

public interface NewDataView extends BaseView {
    void showProgresBar();
    void updateRecyclerView(List<Document> documents);
    void setMetaData(DocumentMeta data);
    void hideProgresBar();
    SessionManager getSessionManager();
    void showToast(String message);
}
