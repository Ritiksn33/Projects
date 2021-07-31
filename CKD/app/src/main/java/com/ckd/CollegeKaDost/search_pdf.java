package com.ckd.CollegeKaDost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.ckd.CollegeKaDost.search_subjects.subject_selected;
import static com.ckd.CollegeKaDost.Downloads.pdf_name;

public class search_pdf extends AppCompatActivity {

    TextView sub;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pdf);
        sub = findViewById(R.id.subjectl);
        sub.setText(subject_selected);
        listView = findViewById(R.id.Lv1);

        list = new ArrayList<>();
        list.add("Book1\n Chemistry, Moisture test, reactions");
        list.add("Book2");
        list.add("Book3");
        list.add("Book4");
        list.add("Book5");
        list.add("Book6");
        list.add("Book7");
        list.add("Melon");


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView = findViewById(R.id.searchViewpdf);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
                pdf_name = adapter.getItem(position);
                pdf();

            }
        });




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(getApplicationContext(), "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });




    }









    public void pdf()
    {
        Intent o = new Intent(this, pdfs.class);
        startActivity(o);

    }
}
