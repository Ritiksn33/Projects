package com.ckd.CollegeKaDost;
import androidx.appcompat.app.AppCompatActivity;


import java.io.File;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Downloads extends AppCompatActivity {

    public static String pdf_name = "";

    TextView nofile;

    ListView listView3;
    ArrayList<String> list3;
    ArrayAdapter<String > adapter3;
    static public Integer count = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_downloads);

        listView3 = findViewById(R.id.lv3);
        nofile = findViewById(R.id.NoFile);

        nofile.setVisibility(View.INVISIBLE);

        list3 = new ArrayList<>();



        @SuppressLint("SdCardPath") File dir = new File("/sdcard/Android/data/com.ckd.CollegeKaDost/files");
        for (File f : dir.listFiles()) {
            if (f.isFile()) {

                String name = f.getName();
                list3.add(name);
                count++;


            }
        }

        if(count==0){
            listView3.setVisibility(View.GONE);
            nofile.setVisibility(View.VISIBLE);
        }
        else{
            nofile.setVisibility(View.GONE);
        }

        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list3);
        listView3.setAdapter(adapter3);

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter3.getItem(position), Toast.LENGTH_SHORT).show();
                pdf_name = adapter3.getItem(position);
                show();

            }
        });



    }

    public void show()
    {

        Intent q = new Intent(this , pdfs.class);
        startActivity(q);
    }
}
