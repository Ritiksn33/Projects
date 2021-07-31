package com.ckd.CollegeKaDost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import static com.ckd.CollegeKaDost.home.selection;

public class search_subjects extends AppCompatActivity {

    TextView select;
    public static String subject_selected = "Java";

    SearchView searchView;
    ListView listView2;
    ArrayList<String> list2;
    ArrayAdapter<String > adapter2;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_subjects);

        select = findViewById(R.id.selection);
        select.setText(selection);
        listView2 = findViewById(R.id.Lv2);
        loading = findViewById(R.id.loading);


        listView2.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.VISIBLE);


        list2 = new ArrayList<>();
        list2.add("Chemistry");
        list2.add("Math");
        list2.add("Java");
        list2.add("OOPS");
        list2.add("DBMS");


        loading.setVisibility(View.GONE);
        listView2.setVisibility(View.VISIBLE);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list2);
        listView2.setAdapter(adapter2);

        searchView = findViewById(R.id.searchView2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter2.getItem(position), Toast.LENGTH_SHORT).show();
                subject_selected = adapter2.getItem(position);
                gotopdf();

            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list2.contains(query)){
                    adapter2.getFilter().filter(query);
                }else{
                    Toast.makeText(getApplicationContext(), "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter2.getFilter().filter(newText);
                return false;
            }
        });


    }

    public void gotopdf()
    {

        Intent o = new Intent(this, search_pdf.class);
        startActivity(o);
    }
}
