package com.ckd.CollegeKaDost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {


    Button profile, downloads, rateus, contribute, logout;
    FirebaseAuth  mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();


        profile = (Button) findViewById(R.id.profile);
        downloads = (Button) findViewById(R.id.downloads);
        rateus = (Button) findViewById(R.id.rateus);
        contribute = (Button) findViewById(R.id.contribute);
        logout = (Button) findViewById(R.id.logout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent o = new Intent(this, home.class);
        startActivity(o);
    }

    public void logout(View view)
    {

        mAuth.signOut();
        Intent o = new Intent(this, MainActivity.class);
        startActivity(o);
    }

    public void Contribute(View view)
    {
        Intent o = new Intent(this, Contribute.class);
        startActivity(o);
    }


    public void profile(View view)
    {
        Intent q = new Intent(this, profile.class);
        startActivity(q);

    }
    public void download(View view)
    {
        Intent q = new Intent(this , Downloads.class);
        startActivity(q);
    }

}
