package com.example.ranggarizky.tugas_akhir.keywordpage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.OnSelectCategoryListener;
import com.example.ranggarizky.tugas_akhir.createkeywordpage.SpinnerCategoryAdapter;
import com.example.ranggarizky.tugas_akhir.model.Category;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class CategoryDialog  extends Dialog implements View.OnClickListener{

    private Context context;
    private Button btnPilih;
    private Spinner spinnerCategory;
    private SpinnerCategoryAdapter adapter;
    private OnSelectCategoryListener onSelectCategoryListener;

    public CategoryDialog(Context context,SpinnerCategoryAdapter adapter,OnSelectCategoryListener onSelectCategoryListener) {
        super(context);
        this.context = context;
        this.adapter = adapter;
        this.onSelectCategoryListener = onSelectCategoryListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_category);
        btnPilih = (Button) findViewById(R.id.btnPilih);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        btnPilih.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPilih:
                Category selected = (Category) spinnerCategory.getSelectedItem();
                onSelectCategoryListener.onSelect(selected);
                dismiss();
                break;
            default:
                break;
        }
    }


}
