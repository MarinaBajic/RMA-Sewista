package com.sewista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemsContainer;
        ImageView item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemsContainer = itemView.findViewById(R.id.itemContainer);
            item = itemView.findViewById(R.id.item);
        }
    }

    private Context context;
    private List<Integer> items;

    public MyAdapter(Context context, List<Integer> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item.setImageResource(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
