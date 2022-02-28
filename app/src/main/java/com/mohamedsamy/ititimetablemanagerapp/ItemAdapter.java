package com.mohamedsamy.ititimetablemanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    @NonNull
    private ArrayList<Item> viewItems;

    public ItemAdapter(ArrayList<Item> items ) {
        viewItems = items;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView uid;
        private TextView desc;
        private TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            uid = itemView.findViewById(R.id.uid);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            date = itemView.findViewById(R.id.date);
        }
    }

    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item ,parent ,false);
        ItemViewHolder vh = new ItemViewHolder(v1);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Item currentItem = viewItems.get(position);
        holder.date.setText(currentItem.getTime());
        holder.uid.setText(currentItem.getUid());
        holder.desc.setText(currentItem.getDi());
        holder.name.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }
}
