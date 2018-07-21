package com.example.ranggarizky.tugas_akhir.dashboardcategorypage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ranggarizky on 1/20/2017.
 */
public class HorizCategoryRecyclerViewAdapter extends RecyclerView.Adapter<HorizCategoryRecyclerViewAdapter.ViewHolder> {
    private List<Category> datalist;
    private Context context;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtCategory)
        TextView txtCategory;
        @BindView(R.id.mainLayout)
        CardView mainLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }

    }


    public HorizCategoryRecyclerViewAdapter(Context context, List<Category> datalist) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_horizontal_category, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HorizCategoryRecyclerViewAdapter.ViewHolder holder, final int position) {
        final Category item = datalist.get(position);
        holder.txtCategory.setText(item.getCategory());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }
}

