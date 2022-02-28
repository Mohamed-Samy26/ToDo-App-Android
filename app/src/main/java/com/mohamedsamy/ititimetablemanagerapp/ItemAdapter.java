package com.mohamedsamy.ititimetablemanagerapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    @NonNull
    private ArrayList<Item> viewItems;
    private DBhelper dbms ;
    public Runnable del;

    public ItemAdapter(ArrayList<Item> items , DBhelper dbms ,Runnable del) {
        viewItems = items;
        this.dbms = dbms;
        this.del = del;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView desc;
        private TextView date;
//        private Button dn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            date = itemView.findViewById(R.id.date);
//            dn = itemView.findViewById(R.id.btnDone);
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
        holder.desc.setText(currentItem.getDi());
        holder.name.setText(currentItem.getName());
//        holder.dn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dbms.delTask(currentItem.getUid() , currentItem.getName(),
//                        currentItem.getDi() , currentItem.getTime());
//                del.run();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }
}
