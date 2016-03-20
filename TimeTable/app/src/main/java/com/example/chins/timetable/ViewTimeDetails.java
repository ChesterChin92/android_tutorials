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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ViewTimeDetails extends AppCompatActivity {
    private String recID;
    private TextView TextViewTitle;
    private TextView TextViewSlot;
    private TextView TextViewYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtime_details);
        TextViewTitle = (TextView) findViewById(R.id.textViewTitle);
        TextViewSlot = (TextView) findViewById(R.id.textViewAuthor);
        TextViewYear = (TextView) findViewById(R.id.textViewYear);
        Bundle extras = getIntent().getExtras();
        recID = extras.getString("_id");
        displayBookDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_viewtime_details, menu);
        return true;
    }


    private void displayBookDetails()
    {
        TimeTableSQL db = new TimeTableSQL(ViewTimeDetails.this);
        db.open();
        Cursor result = db.getOneBook(recID);
        result.moveToFirst();
        TextViewTitle.setText(result.getString(0));
        TextViewSlot.setText(result.getString(1));
        TextViewYear.setText(result.getString(2));
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
