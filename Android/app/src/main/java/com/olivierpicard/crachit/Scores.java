package com.olivierpicard.crachit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Scores extends AppCompatActivity {
    private ListView listView;
    public static DataBaseHandler dbAdapter;
    private MyArrayAdapter arrayAdapter;
    private List<CellStruct> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        tasks = dbAdapter.getAll();
        listView = findViewById(R.id.listView_scores);
        arrayAdapter = new MyArrayAdapter(this, (ArrayList<CellStruct>) tasks);
        listView.setAdapter(arrayAdapter);
    }
}
