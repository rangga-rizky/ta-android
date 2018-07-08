package com.example.ranggarizky.tugas_akhir.keywordpage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.mainpage.MainActivity;
import com.example.ranggarizky.tugas_akhir.model.Term;

import java.math.RoundingMode;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ranggarizky on 1/20/2017.
 */
public class TermRecyclerViewAdapter extends RecyclerView.Adapter<TermRecyclerViewAdapter.ViewHolder> {
    private List<Term> datalist;
    private Context context;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtTerm)
        TextView txtTerm;
        @BindView(R.id.txtCategory)
        TextView txtCategory;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }

    }


    public TermRecyclerViewAdapter(Context context, List<Term> datalist) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_term, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TermRecyclerViewAdapter.ViewHolder holder, final int position) {
        final Term item = datalist.get(position);
        holder.txtTerm.setText(item.getTerm());
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

