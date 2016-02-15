package com.example.chins.edittext;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Refer to the slides editText.ppt from lecturer
        Button b;
        TextView t;
        final EditText e;

//      Assign b with btnSend
        b = (Button) findViewById(R.id.btnSend);

//      Assign e with editText used for GET and SET method later.
        e = (EditText) findViewById(R.id.etMsg);

//        Teachers method (Minor erros here)
//        String message = e.getText().toString();
//        Toast.makeText(getApplicationContext(), message, 1).show();
//        e = (EditText) findViewById(R.id.etMsg);
//        String message = e.getText().toString();
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//          e.setText("Kolej KDU");
//        public void btnSend(View view) {
//
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//        }
//        End of Teachers Method

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String message = e.getText().toString();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

//        public void clkbtnSend(View v)
//        {
//            message = e.getText().toString();
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//        }


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
