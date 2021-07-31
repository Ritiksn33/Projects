package com.ckd.CollegeKaDost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class NewAccount extends AppCompatActivity {

    Button signup;

    EditText Email, password;

    EditText name, Gender, College, year;



    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_account);

        signup  = (Button) findViewById(R.id.signup);
        Email = findViewById(R.id.Email);
        password = findViewById(R.id.password);


        name = findViewById(R.id.name);
        Gender = findViewById(R.id.Gender);
        College = findViewById(R.id.College);
        year = findViewById(R.id.year);


        mAuth = FirebaseAuth.getInstance();




    }






    public void userInfo(){
        String Name = name.getText().toString();
        String gender = Gender.getText().toString();
        String college = College.getText().toString();
        String Year = year.getText().toString();

        if(Name.isEmpty() ){
            name.setError("Email is required");
            name.requestFocus();
            userInfo();
        }
        if(gender.isEmpty() ){
            Gender.setError("Gender is required");
            Gender.requestFocus();
            userInfo();
        }
        if(college.isEmpty() ){
            College.setError("College name is required");
            College.requestFocus();
            userInfo();
        }
        if(Year.isEmpty() ){
            year.setError("Year is required");
            year.requestFocus();
            userInfo();
        }

        FirebaseUser user =mAuth.getCurrentUser();

        if(user!=null){
            UserProfileChangeRequest  profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(Name)
                    .build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }







    }






    public void register(){
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

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User registered", Toast.LENGTH_SHORT).show();
                            Intent o = new Intent(NewAccount.this, home.class);
                            startActivity(o);

                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "User Already Present", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                });

    }


    public void signup(View view)
    {
        userInfo();
        register();

    }
}
