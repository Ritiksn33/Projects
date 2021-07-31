package com.example.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    String custID,custName;
    TextView name,email,phn,bank,bal,emailLabel,contactLabel,bankLabel,accLabel;
    ImageButton back ,custButton, home;
    ImageView userImg;
    public void onHome(View view){
        Intent homepage = new Intent(getApplicationContext(),MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homepage);
        finish();
    }
    public void onTransfer(View view){
        Intent customerList = new Intent(getApplicationContext(),CustomerListActivity.class);
        customerList.putExtra("SenderID",custID);
        customerList.putExtra("SenderName",custName);
        startActivity(customerList);
    }
    public void onBack(View view){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent parent = this.getIntent();
        String customerID = parent.getStringExtra("customerID");
        name = findViewById(R.id.nameview);
        email = findViewById(R.id.emailview);
        phn = findViewById(R.id.phnview);
        bank = findViewById(R.id.bankview);
        bal = findViewById(R.id.balview);
        userImg = findViewById(R.id.userImg);
        emailLabel=findViewById(R.id.emailID);
        contactLabel=findViewById(R.id.contactNo);
        bankLabel=findViewById(R.id.bankName);
        accLabel=findViewById(R.id.accBal);
        back = findViewById(R.id.backButton1);
        home = findViewById(R.id.homeButton2);
        custButton = findViewById(R.id.button);
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
        Cursor c = myDatabase.rawQuery("SELECT * FROM customers WHERE custid='"+customerID+"'",null);
        int nameInd = c.getColumnIndex("name");
        int emailInd = c.getColumnIndex("email");
        int phnInd = c.getColumnIndex("phn");
        int bankInd = c.getColumnIndex("bank");
        int balInd = c.getColumnIndex("balance");
        c.moveToFirst();
        while(!c.isAfterLast()){
            name.setText(c.getString(nameInd));
            custName = c.getString(nameInd);
            email.setText(c.getString(emailInd));
            phn.setText(c.getString(phnInd));
            bank.setText(c.getString(bankInd));
            bal.setText("Rs."+c.getString(balInd));
            c.moveToNext();
        }
        custID=customerID;
    }
}