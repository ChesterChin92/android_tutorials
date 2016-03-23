package com.example.chins.timetable;

/**
 * Created by chins on 3/22/2016.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ViewTimeDetails extends AppCompatActivity {
    private String recID;
    private TextView TextViewModule;
    private TextView TextViewDay;
    private TextView TextViewTime;

    private TextView TextViewDuration;
    private TextView TextViewSession;
    private TextView TextViewRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtime_details);
        TextViewModule = (TextView) findViewById(R.id.textViewModule);
        TextViewDay = (TextView) findViewById(R.id.textViewDay);
        TextViewTime = (TextView) findViewById(R.id.textViewTime);
        TextViewDuration = (TextView) findViewById(R.id.textViewDuration);
        TextViewSession = (TextView) findViewById(R.id.textViewSession);
        TextViewRoom = (TextView) findViewById(R.id.textViewRoom);



        Bundle extras = getIntent().getExtras();
        recID = extras.getString("_id");

        Log.d("myTag", recID.toString());
        displayTimeDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_viewtime_details, menu);
        return true;
    }


    private void displayTimeDetails()
    {
        TimeTableSQL db = new TimeTableSQL(ViewTimeDetails.this);
        db.open();
        Cursor result = db.getOneTime(recID);
        result.moveToFirst();
        TextViewModule.setText(result.getString(0));
        TextViewDay.setText(result.getString(1));
        TextViewTime.setText(result.getString(2));
        TextViewDuration.setText(result.getString(3));
        TextViewSession.setText(result.getString(4));
        TextViewRoom.setText(result.getString(5));
        result.close();
        db.close();
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
        switch (item.getItemId())
        {
            case R.id.menuAdd:
                // create an Intent to launch the AddEditContact Activity
                Intent addbook =
                        new Intent(this, AddTimeActivity.class);
                startActivity(addbook);
                return true;
            case R.id.menuDelete:
                deleteBook();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void deleteBook()
    {
        AlertDialog.Builder dgbox = new AlertDialog.Builder(this);
        dgbox.setTitle("Confirm?");
        dgbox.setMessage("Do you want to delete?");

        dgbox.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                final TimeTableSQL db = new TimeTableSQL(ViewTimeDetails.this);
                db.deleteTimeTable(recID);
                finish();
            }
        });

        // provide a button that simply dismisses the dialog
        dgbox.setNegativeButton("Cancel", null);
        dgbox.show();
    }
}