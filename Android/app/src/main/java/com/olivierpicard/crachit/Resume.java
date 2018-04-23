package com.olivierpicard.crachit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resume extends AppCompatActivity {
    public static Resume reference;
    public List<DataBaseHandler.ItemRestaurationTable> lastSavedItems;
    private ListView listView;
    private MyArrayAdapter arrayAdapter;
    private List<CellStruct> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        setTitle(R.string.reprendre);
        Resume.reference = this;
        tasks = DataBaseHandler.reference.getAll_game();
        listView = findViewById(R.id.listView_resume);
        arrayAdapter = new MyArrayAdapter(this, (ArrayList<CellStruct>) tasks, MyArrayAdapter.TypeOfData.GameSave);
        listView.setAdapter(arrayAdapter);
    }

    public void returnResult(int score, final List<DataBaseHandler.ItemRestaurationTable> items) {
        Intent intent = new Intent();
        intent.putExtra("score", score);
        this.lastSavedItems = items;
        setResult(RESULT_OK, intent);
        finish();
    }
}
