package com.susnjara.rmaprojekt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<HistoryItem> items;

    public Adapter(ArrayList<HistoryItem> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTitle;
        public TextView itemDef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDef = itemView.findViewById(R.id.itemDef);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryItem item = items.get(position);
        holder.itemTitle.setText(item.getTitle());
        holder.itemDef.setText(item.getDefinition());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
