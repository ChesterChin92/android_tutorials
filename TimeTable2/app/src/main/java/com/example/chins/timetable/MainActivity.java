package com.example.chins.timetable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.StringTokenizer;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int id_button = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//          Not working, not sure where to define layout id
        Button myButton = new Button(this);
        myButton.setText("Push Me");
        myButton.setOnClickListener(this);

        Button myButton2 = new Button(this);
        myButton2.setText("Push Me2");
//        myButton2.setBackgroundColor(205);
        myButton2.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
        myButton2.setOnClickListener(this);

        Button myButton3 = new Button(this);
        myButton3.setText("Push Me3");
        myButton2.getBackground().setColorFilter(Color.parseColor("#0000ff"), PorterDuff.Mode.DARKEN);
//        myButton3.setVisibility(View.INVISIBLE);


//        ScrollView layout = (ScrollView) findViewById(R.id.scroll);
        LinearLayout ll = (LinearLayout)findViewById(R.id.internal_layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(250, 400);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(400, 600);

        ll.addView(myButton, lp);
        ll.addView(myButton2, lp2);
        ll.addView(myButton3, lp3);



//        for (int i = 1; i <= 20; i++) {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            Button btn = new Button(this);
            myButton3.setId('1');
            final int id_ = myButton3.getId();
            myButton3.setText("button " + id_);
//            btn.setBackgroundColor(Color.rgb(70, 80, 90));
//            linear.addView(btn, params);
            myButton3 = ((Button) findViewById(id_));
            myButton3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();

                }
            });
//        }


//        //add button
//        ToggleButton b = new ToggleButton(this);
//        // Setting the parameters
//        lefttextv.setLayoutParams(lleft);
//        b.setLayoutParams(bright);
//        //customize button
//        b.setId(id_button);
//        System.out.println(id_button);
//        b.setHeight(100);
//        b.setWidth(200);
//        // Adding to the RelativeLayout as a child
//        layouth.addView(lefttextv);
//        layouth.addView(b);
//        b.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void onClick(View v) {
        // TODO Auto-generated method stub

            Log.d("Button 0", "Button 0 pressed");

        Intent tableMain  =
                new Intent(MainActivity.this, tableMain.class);
        startActivity(tableMain);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
