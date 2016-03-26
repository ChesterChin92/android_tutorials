package com.example.chins.timetable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by chins on 3/25/2016.
 */
public class tableMain extends Activity implements View.OnClickListener{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_main);

        TableLayout tl = (TableLayout) findViewById(R.id.tableLayout1);
/* Create a new row to be added. */
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
/* Create a Button to be the row-content. */
        Button b = new Button(this);
        b.setText("Dynamic Button");
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
/* Add Button to row. */
        tr.addView(b);

        Button myButton2 = new Button(this);
        myButton2.setText("Push Me2");
//        myButton2.setBackgroundColor(205);
        myButton2.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
        myButton2.setOnClickListener(this);
        myButton2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
/* Add Button to row. */
        tr.addView(myButton2);

/* Add row to TableLayout. */
//tr.setBackgroundResource(R.drawable.sf_gradient_03);
        tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub

        Log.d("Button 0", "Dynamic Button Pressed");




    }
}
