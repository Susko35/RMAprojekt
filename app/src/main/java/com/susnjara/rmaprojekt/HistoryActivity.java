package com.susnjara.rmaprojekt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    ArrayList<String> id, title, def;
    DbHelper myDB;
    Button buttonSwitchBack, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        myDB = new DbHelper(HistoryActivity.this);
        id = new ArrayList<>();
        title = new ArrayList<>();
        def = new ArrayList<>();


        storeDataInArrays();
        recyclerView = findViewById(R.id.searches);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(HistoryActivity.this, this, id, title, def);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);

        buttonSwitchBack = findViewById(R.id.buttonSwitchBack);
        buttonSwitchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteAllData();
                finish();
                startActivity(getIntent());
            }
        });
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readT();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                def.add(cursor.getString(2));

            }
        }
    }

}
