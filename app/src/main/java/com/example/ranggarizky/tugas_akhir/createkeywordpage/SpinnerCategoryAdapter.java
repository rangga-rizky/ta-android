package com.example.ranggarizky.tugas_akhir.createkeywordpage;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ranggarizky.tugas_akhir.model.Category;

import java.util.List;

/**
 * Created by RanggaRizky on 7/8/2018.
 */

public class SpinnerCategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> values;

    public SpinnerCategoryAdapter(Context context, int textViewResourceId, List<Category> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount() {
        return values.size();
    }

    public Category getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getCategory());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getCategory());

        return label;
    }
}
