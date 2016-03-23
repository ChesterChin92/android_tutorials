package com.example.chins.timetable;

import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by chins on 3/22/2016.
 */
public class AddTimeActivity extends AppCompatActivity implements OnItemSelectedListener{
    private EditText EditTextModule;
    private TextView TextViewDay;
    private EditText EditTextStartTime;
    private EditText EditTextDuration;
    private TextView TextViewSession;
    private EditText EditTextRoom;
    private long rowID;

    //Spinner for Session
    private Spinner SpinnerSession;
    private Spinner SpinnerDay;

    //Time Picker
    private TimePicker timePicker1;
    private Button btnChangeTime;

    private int hour;
    private int minute;

    static final int TIME_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);
//        Log.i("TRACEEE", "AddBookAct onCreate");

        EditTextModule = (EditText) findViewById(R.id.editTextModule);
        TextViewDay = (TextView) findViewById(R.id.textViewDay);
        EditTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
        EditTextDuration = (EditText) findViewById(R.id.editTextDuration);
        TextViewSession = (TextView) findViewById(R.id.textViewSession);
        EditTextRoom = (EditText) findViewById(R.id.editTextRoom);

        //Spinner
        SpinnerSession = (Spinner) findViewById(R.id.spinner);
        SpinnerDay = (Spinner) findViewById(R.id.spinner_day);

        //Time Picker
        setCurrentTimeOnView();
        addListenerOnButton();

        // set event listener for the Save Contact Button
        Button ButtonAdd = (Button) findViewById(R.id.buttonAdd);
        ButtonAdd.setOnClickListener(ButtonAddClicked);


        //Android Spinner
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner_day = (Spinner) findViewById(R.id.spinner_day);


        //FOR SPINNER CATEGORY
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner_day.setOnItemSelectedListener(this);

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




        //FOR SPINNER DAY
        // Spinner click listener
        spinner_day.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories_day = new ArrayList<String>();
        categories_day.add("Monday");
        categories_day.add("Tuesday");
        categories_day.add("Wednesday");
        categories_day.add("Thursday");
        categories_day.add("Friday");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter_day = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories_day);

        // Drop down layout style - list view with radio button
        dataAdapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_day.setAdapter(dataAdapter_day);
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
                db.addTimeTable(EditTextModule.getText().toString(), SpinnerDay.getSelectedItem().toString().toString(), EditTextStartTime.getText().toString(),EditTextDuration.getText().toString(),SpinnerSession.getSelectedItem().toString(),EditTextRoom.getText().toString());
            }
            finish();
        }
    };


    //For Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }; //End of spinner

    // display current time
    public void setCurrentTimeOnView() {


        timePicker1 = (TimePicker) findViewById(R.id.timePicker);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        EditTextStartTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hour);
        timePicker1.setCurrentMinute(minute);

    }

    public void addListenerOnButton() {

        btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute,false);

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                    EditTextStartTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));

                    // set current time into timepicker
                    timePicker1.setCurrentHour(hour);
                    timePicker1.setCurrentMinute(minute);

                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
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
