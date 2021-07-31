package com.ckd.CollegeKaDost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    FirebaseAuth mAuth;


    TextView menu, notes, books, questionpaper, certifications, todo, projects;
    public static String selection = "Notes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();

        if(!mAuth.getCurrentUser().isEmailVerified()){
            mAuth.getCurrentUser().sendEmailVerification();

        }


        menu =  findViewById(R.id.Menu);
        notes = findViewById(R.id.Notes);
        questionpaper = findViewById(R.id.Paper);
        certifications = findViewById(R.id.Certifications);
        todo =  findViewById(R.id.todo);
        projects =  findViewById(R.id.Projects);
        books =  findViewById(R.id.Books);







    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }


    public void menu(View view)
    {
        Intent o = new Intent(this, Menu.class);
        startActivity(o);
    }

    public void todo(View view)
    {
        Intent o = new Intent(this, Todo.class);
        startActivity(o);
    }

    public void pdfs(View view)
    {
        TextView d = findViewById(view.getId());

        selection = (String) d.getText();
        System.out.println(selection);
        Intent o = new Intent(this, search_subjects.class);
        startActivity(o);


    }


}
