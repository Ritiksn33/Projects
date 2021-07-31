package com.example.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton users;
    TextView usersLabel;


    public void onHome(View view){
    }
    public void onBack(View view){
    }

    public void viewCustomerList(View view) {
        Intent customerList = new Intent(getApplicationContext(), CustomerListActivity.class);
        customerList.putExtra("SenderID", "");
        startActivity(customerList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = findViewById(R.id.imageButton);

        usersLabel = findViewById(R.id.customerView);


        SQLiteDatabase myDataBase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
        //myDataBase.execSQL("DROP TABLE customers");
       // myDataBase.execSQL("DROP TABLE transitions");
        /*myDataBase.execSQL("CREATE TABLE IF NOT EXISTS customers(custid VARCHAR PRIMARY KEY, name VARCHAR, email VARCHAR, phn INT(10), bank VARCHAR, balance DOUBLE)");


        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS transitions(transitionid VARCHAR PRIMARY KEY, sender VARCHAR, receiver VARCHAR, amount VARCHAR, status VARCHAR)");
        /*myDataBase.execSQL("DROP TABLE transitions");*/
        /*myDataBase.execSQL("INSERT INTO customers VALUES('C101','Riya', 'riyakumar2709@gmail.com',8858321626,'ICICI Bank',20000.50)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C102','Jay', 'jayS@gmail.com',9012456984,'SBI Bank',19000.75)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C103','Kabir', 'kabir2020@gmail.com',8130612340,'HDFC Bank',15500.00)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C104','Priyanka', 'priyankapr@gmail.com',9154780945,'ICICI Bank',10000.75)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C105','Nisha', 'nishi@gmail.com',9064381038,'Axis Bank',25000.55)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C106','Samarth', 'samK10@gmail.com',9142630714,'Axis Bank',24001.00)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C107','Veer', 'veer1234@gmail.com',9818567890,'HDFC Bank',57000.00)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C108','Navya', 'navyaNew@gmail.com',6237438457,'SBI Bank',8005.75)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C109','Shruti', 'shruutii@gmail.com',8139452789,'PNB Bank',95000.00)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C110','Rahul', 'rahulS15@gmail.com',7940994466,'ICICI Bank',4500.60)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C111','Ravi', 'ravi1001@gmail.com',7478746340,'ICICI Bank',7899.90)");
        myDataBase.execSQL("INSERT INTO customers VALUES('C112','Sushant', 'sushhh@gmail.com',7983212256,'HDFC Bank',22222.22)");
        //myDataBase.execSQL("DROP TABLE customers");
       // myDataBase.execSQL("DROP TABLE transitions");*/

    }
}