package com.susnjara.rmaprojekt;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Config;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    TextView outputText;
    TextView outputTextTitle;
    Button buttonTranslate;
    Button buttonSwitch;
    RetrofitClient retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initItems();

        retrofit = RetrofitClient.getInstance();
        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translate();
                DbHelper myDB = new DbHelper(MainActivity.this);
                myDB.addT(outputTextTitle.getText().toString().trim(), outputText.getText().toString().trim());
            }
        });

        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initItems() {
        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
        outputTextTitle = findViewById(R.id.outputTextTitle);
        buttonTranslate = findViewById(R.id.buttonTranslate);
        buttonSwitch = findViewById(R.id.buttonSwitch);
    }

    private void translate() {

        String input = inputText.getText().toString();
        if(input.isEmpty()) {
            outputTextTitle.setText("no input");
            return;
        }
        Call<Translate> call = retrofit.getMyApi().getTranslation(input);
        call.enqueue(new Callback<Translate>() {
            @Override
            public void onResponse(Call<Translate> call, Response<Translate> response) {
                if (!response.isSuccessful()) {
                    outputTextTitle.setText("Code: " + response.code());
                    return;
                }
                String error = response.body().getError();
                if(error.equals("none")) {
                    translateOutput(response);
                }
                else {
                    outputTextTitle.setText("Response\n" + error);
                }

            }
            @Override
            public void onFailure(Call<Translate> call, Throwable t) {
                outputTextTitle.setText(t.getMessage());
            }
        });

    }
    private void translateOutput(Response<Translate> response) {
        String translation = response.body().getEn();
        String definition = response.body().getDef();
        outputTextTitle.setText("\n" + translation + "\n");
        outputText.setText("\n " + definition);
    }
}
