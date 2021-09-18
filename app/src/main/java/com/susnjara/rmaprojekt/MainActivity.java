package com.susnjara.rmaprojekt;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Config;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    TextView outputText;
    Button buttonTranslate;
    String apiKeyTranslate = "AIzaSyAtcoPGQdQSMu1_RzqecXLmYB_QgNmAI4A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
        buttonTranslate = findViewById(R.id.buttonTranslate);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        //Api api = retrofit.getMyApi();


        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                Call<Translate> call = retrofit.getMyApi().getTranslation(input);
                call.enqueue(new Callback<Translate>() {
                    @Override
                    public void onResponse(Call<Translate> call, Response<Translate> response) {
                        if (!response.isSuccessful()) {
                            outputText.setText("Code: " + response.code());
                            return;
                        }
                        outputText.setText("Translation : "+response.body().getDef());
                    }
                    @Override
                    public void onFailure(Call<Translate> call, Throwable t) {
                        outputText.setText(t.getMessage());
                    }
                });

            }
        });
    }
}
