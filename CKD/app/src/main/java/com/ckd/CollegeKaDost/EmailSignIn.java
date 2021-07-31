package com.ckd.CollegeKaDost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailSignIn extends AppCompatActivity {

    EditText Email, password;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_email_sign_in);
        Email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();




    }

    public void userLogin(){
        String email = Email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Enter a valid Email");
            Email.requestFocus();
            return;
        }
        if(pass.length()<6){
            password.setError("Minimum length should be 6 ");
            password.requestFocus();
            return;
        }


        if(pass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent o = new Intent(EmailSignIn.this, home.class);
                    o.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(o);


                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    public void getin(View view)
    {
        userLogin();

    }
}