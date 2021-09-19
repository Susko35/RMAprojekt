package com.susnjara.rmaprojekt;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList id, title, def;

    Adapter(Activity activity, Context context, ArrayList id, ArrayList title, ArrayList def) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.def = def;
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
        holder.itemTitle.setText(String.valueOf(title.get(position)));
        holder.itemDef.setText(String.valueOf(def.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }
}
