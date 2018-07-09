package com.example.ranggarizky.tugas_akhir.categorypage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.Term;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ranggarizky on 1/20/2017.
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    private List<Category> datalist;
    private Context context;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtCategory)
        TextView txtCategory;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }

    }


    public CategoryRecyclerViewAdapter(Context context, List<Category> datalist) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryRecyclerViewAdapter.ViewHolder holder, final int position) {
        final Category item = datalist.get(position);
        holder.txtCategory.setText(item.getCategory());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
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

