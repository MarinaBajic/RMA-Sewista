package com.sewista.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sewista.model.Pattern;
import com.sewista.R;

import java.util.List;

public class MyAdminAdapter extends RecyclerView.Adapter<MyAdminAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patternTitle;

        ImageButton editBtn, deleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patternTitle = itemView.findViewById(R.id.pattern_title_admin);
            editBtn = itemView.findViewById(R.id.edit_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }
    }

    private static List<Pattern> patternList;
    private final AdminAdapterListener adapterListener;

    public MyAdminAdapter(List<Pattern> patternList, AdminAdapterListener adapterListener) {
        MyAdminAdapter.patternList = patternList;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pattern_admin, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pattern pattern = patternList.get(position);

        holder.patternTitle.setText(pattern.getTitle());
        holder.editBtn.setOnClickListener(view -> adapterListener.onUpdate(pattern, position));
        holder.deleteBtn.setOnClickListener(view -> adapterListener.onDelete(pattern, position));
    }

    public void removePattern(int position) {
        patternList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return patternList.size();
    }
}