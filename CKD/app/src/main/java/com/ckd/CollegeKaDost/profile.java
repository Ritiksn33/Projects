package com.ckd.CollegeKaDost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    //Add request for password change;

    Button save, passchange;
    EditText Name, College, Year;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        save = (Button) findViewById(R.id.save);
        Name = findViewById(R.id.name);
        College = findViewById(R.id.College);
        Year = findViewById(R.id.year);
        passchange = findViewById(R.id.passchange);




        mAuth = FirebaseAuth.getInstance();


        passchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passchange.getText().equals("Request Password change")){
                    FirebaseUser user = mAuth.getCurrentUser();

                    mAuth.sendPasswordResetEmail(user.getEmail());
                    passchange.setText("Requested");
                }



            }
        });


        FirebaseUser user = mAuth.getCurrentUser();
            System.out.println("jjjjjj      " + user.getDisplayName());
            Name.setText(user.getDisplayName());





    }







    public void sign(View view)
    {
        Intent o = new Intent(this, Menu.class);
        startActivity(o);
    }
}
