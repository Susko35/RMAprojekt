package com.susnjara.rmaprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private Button buttonSwitchBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ArrayList<HistoryItem> items = new ArrayList<>();
        items.add(new HistoryItem("lelelel", "lalalala"));
        items.add(new HistoryItem("dawdaw", "bnwaujd bnwauod bnauwioq1alala"));
        items.add(new HistoryItem("scysad", "rdhgfj"));

        recyclerView = findViewById(R.id.searches);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(items);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);

        buttonSwitchBack.findViewById(R.id.buttonSwitchBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
