package com.sewista.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sewista.model.Pattern;
import com.sewista.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView patternImage;
        TextView patternTitle, patternDesc;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            patternImage = itemView.findViewById(R.id.pattern_image);
            patternTitle = itemView.findViewById(R.id.pattern_title);
            patternDesc = itemView.findViewById(R.id.pattern_desc);

            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int pos = getAbsoluteAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION)
                        recyclerViewInterface.onItemClick(pos);
                }
            });
        }
    }

    private final List<Pattern> patternList;

    private final RecyclerViewInterface recyclerViewInterface;

    public MyAdapter(List<Pattern> patternList, RecyclerViewInterface recyclerViewInterface) {
        this.patternList = patternList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pattern, parent, false);
        return new MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.patternTitle.setText(patternList.get(position).getTitle());
        holder.patternDesc.setText(patternList.get(position).getDesc());
        holder.patternImage.setImageResource(R.drawable.image1 + position);
    }

    @Override
    public int getItemCount() {
        return patternList.size();
    }
}
