package com.ckd.CollegeKaDost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView createnewaccount, withgoogle;
    AdView adView;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-1044099953003826~7790993058");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        SharedPreferences sh = getSharedPreferences("test", Context.MODE_PRIVATE);
        String name = sh.getString("test", "");
        mAuth = FirebaseAuth.getInstance();


        if(!name.equals(""))
        {
            String pathname = "/sdcard/Android/data/com.ckd.CollegeKaDost/files/" + name;
            File n = new File(pathname);
            n.delete();
        }






        createnewaccount = (TextView) findViewById(R.id.createAnAccount);
        withgoogle = (TextView) findViewById(R.id.google);

        Intent intent = new Intent(this, ExitService.class);
        startService(intent);



    }


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){

            startActivity(new Intent(this, home.class));

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void sign(View view)
    {
        Intent o = new Intent(this, NewAccount.class);
        startActivity(o);

    }

    public void log(View view)
    {
        Intent p = new Intent(this, EmailSignIn.class);
        startActivity(p);
    }

    public void gsign(View view){
        Intent g = new Intent(this,Googles.class);
        startActivity(g);
    }

}
