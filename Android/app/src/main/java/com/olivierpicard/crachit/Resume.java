package com.olivierpicard.crachit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Resume extends AppCompatActivity {
    private ListView listView;
    private MyArrayAdapter arrayAdapter;
    private List<CellStruct> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        setTitle(R.string.reprendre);
        tasks = DataBaseHandler.reference.getAll_game();
        listView = findViewById(R.id.listView_resume);
        arrayAdapter = new MyArrayAdapter(this, (ArrayList<CellStruct>) tasks, MyArrayAdapter.TypeOfData.GameSave);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("score", String.valueOf(arrayAdapter.getItem(i).score));
                setResult(RESULT_OK, intent);
            }
        });
    }
}
