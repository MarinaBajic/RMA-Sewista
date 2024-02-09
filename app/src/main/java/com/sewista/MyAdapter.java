package com.sewista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView patternImage;
        TextView patternTitle, patternDesc;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            patternImage = itemView.findViewById(R.id.patternImage);
            patternTitle = itemView.findViewById(R.id.patternTitle);
            patternDesc = itemView.findViewById(R.id.patternDesc);

            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION)
                        recyclerViewInterface.onItemClick(pos);
                }
            });
        }
    }

    private List<Pattern> patterns;

    private final RecyclerViewInterface recyclerViewInterface;

    public MyAdapter(List<Pattern> patterns, RecyclerViewInterface recyclerViewInterface) {
        this.patterns = patterns;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pattern_card, parent, false);
        return new MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.patternTitle.setText(patterns.get(position).getTitle());
        holder.patternDesc.setText(patterns.get(position).getDesc());
        holder.patternImage.setImageResource(patterns.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return patterns.size();
    }
}
