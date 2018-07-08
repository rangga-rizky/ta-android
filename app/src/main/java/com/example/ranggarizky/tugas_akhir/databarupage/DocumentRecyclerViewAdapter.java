package com.example.ranggarizky.tugas_akhir.databarupage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.model.Document;
import com.example.ranggarizky.tugas_akhir.model.Term;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ranggarizky on 1/20/2017.
 */
public class DocumentRecyclerViewAdapter extends RecyclerView.Adapter<DocumentRecyclerViewAdapter.ViewHolder> {
    private List<Document> datalist;
    private Context context;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtText)
        TextView txtText;
        @BindView(R.id.txtDate)
        TextView txtDate;
        @BindView(R.id.txtPredicted)
        TextView txtPredicted;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }

    }


    public DocumentRecyclerViewAdapter(Context context, List<Document> datalist) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DocumentRecyclerViewAdapter.ViewHolder holder, final int position) {
        final Document item = datalist.get(position);
        holder.txtDate.setText(item.getDate());
        holder.txtPredicted.setText(item.getPredicted());
        holder.txtText.setText(item.getTweet());

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

