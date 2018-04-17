package com.olivierpicard.crachit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by olivierpicard on 17/04/2018.
 */

public class MyArrayAdapter extends ArrayAdapter<CellStruct> {
    private final Context context;

    public MyArrayAdapter(Context context, ArrayList<CellStruct> values) {
        super(context, R.layout.custom_cell, values);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView = convertView;

        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.custom_cell, parent, false);
        }

        TextView line1 = cellView.findViewById(R.id.line1);
        TextView line2 = cellView.findViewById(R.id.line2);
        ImageButton delete = cellView.findViewById(R.id.deleteButton);


        CellStruct sd = getItem(position);
        line1.setText("Score : " + sd.score);
        line2.setText(sd.date);

        return cellView;
    }
}
