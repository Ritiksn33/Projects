package com.ckd.CollegeKaDost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Googles extends AppCompatActivity {

    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_googles);

        done  = (Button) findViewById(R.id.Done);

    }


    public void Done(View view)
    {
        Intent o = new Intent(this, home.class);
        startActivity(o);
    }
}
