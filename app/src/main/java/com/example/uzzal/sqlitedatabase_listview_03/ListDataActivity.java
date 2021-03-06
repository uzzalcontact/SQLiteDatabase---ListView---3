package com.example.uzzal.sqlitedatabase_listview_03;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private ListView listView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView = (ListView) findViewById(R.id.listView_id);
        databaseHelper = new DatabaseHelper(this);

        loadData();
    }
    public void loadData(){

        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = databaseHelper.showAllData();

        if(cursor.getCount()==0){
            Toast.makeText(this, "No, data is available in Database ", Toast.LENGTH_SHORT).show();
        }else {

            while (cursor.moveToNext()){

                listData.add(cursor.getString(0)+" \t "+cursor.getString(1));

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textView_id,listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

               String selectedVlaue =  adapterView.getItemAtPosition(position).toString();
                Toast.makeText(ListDataActivity.this, "Selected Value"+selectedVlaue, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
