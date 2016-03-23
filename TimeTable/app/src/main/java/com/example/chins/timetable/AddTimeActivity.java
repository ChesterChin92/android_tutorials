package com.example.chins.timetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chins on 3/22/2016.
 */
public class AddTimeActivity extends AppCompatActivity implements OnItemSelectedListener{
    private EditText EditTextModule;
    private EditText EditTextDay;
    private EditText EditTextStartTime;
    private EditText EditTextDuration;
    private EditText EditTextSession;
    private EditText EditTextRoom;
    private long rowID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);
        Log.i("TRACEEE", "AddBookAct onCreate");

        EditTextModule = (EditText) findViewById(R.id.editTextModule);
        EditTextDay = (EditText) findViewById(R.id.editTextDay);
        EditTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
        EditTextDuration = (EditText) findViewById(R.id.editTextDuration);
        EditTextSession = (EditText) findViewById(R.id.editTextSession);
        EditTextRoom = (EditText) findViewById(R.id.editTextRoom);

        // set event listener for the Save Contact Button
        Button ButtonAdd = (Button) findViewById(R.id.buttonAdd);
        ButtonAdd.setOnClickListener(ButtonAddClicked);


        //Android Spinner
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Lecture");
        categories.add("Tutorial");
        categories.add("Self Study");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_addtime, menu);
        return true;
    }



    // responds to event generated when user clicks the Done Button
    View.OnClickListener ButtonAddClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (EditTextModule.getText().length() != 0)
            {
                TimeTableSQL db = new TimeTableSQL(AddTimeActivity.this);
                db.addTimeTable(EditTextModule.getText().toString(), EditTextDay.getText().toString(), EditTextStartTime.getText().toString(),EditTextDuration.getText().toString(),EditTextSession.getText().toString(),EditTextRoom.getText().toString());
            }
            finish();
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    };




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
