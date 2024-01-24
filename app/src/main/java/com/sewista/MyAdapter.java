package com.sewista;

import android.content.Context;
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patternImage = itemView.findViewById(R.id.patternImage);
            patternTitle = itemView.findViewById(R.id.patternTitle);
            patternDesc = itemView.findViewById(R.id.patternDesc);
        }
    }

    private Context context;
    private List<Pattern> patterns;

    public MyAdapter(Context context, List<Pattern> patterns) {
        this.context = context;
        this.patterns = patterns;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pattern_card, parent, false);
        return new MyViewHolder(v);
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
