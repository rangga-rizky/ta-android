package com.example.ranggarizky.tugas_akhir;

/**
 * Created by RanggaRizky on 6/27/2018.
 */

public interface Presenter<T extends BaseView> {
    void onAttach(T view);

    void onDetach();
}
