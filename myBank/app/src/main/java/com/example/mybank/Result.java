package com.example.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    Context context;
    TextView result = findViewById(R.id.resultView);
    Button homeButton = findViewById(R.id.homeButton);
    Button historyButton = findViewById(R.id.TransactHist);
    public void onResult(View view) {}

    public void onHistory(View view){
        Intent historyPage = new Intent(getApplicationContext(),History.class);
        historyPage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(historyPage);
        finish();
    }

    public void onHome(View view){
        Intent homepage = new Intent(getApplicationContext(),MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homepage);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        context = this;
        }
}