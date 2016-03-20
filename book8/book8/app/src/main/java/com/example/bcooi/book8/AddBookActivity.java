package com.example.bcooi.book8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;




public class AddBookActivity extends AppCompatActivity {

    private EditText EditTextSlot;
    private EditText EditTextTitle;
    private EditText EditTextYear;
    private long rowID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Log.i("TRACEEE", "AddBookAct onCreate");

        EditTextTitle = (EditText) findViewById(R.id.editTextTitle);
        EditTextSlot = (EditText) findViewById(R.id.editTextAuthor);
        EditTextYear = (EditText) findViewById(R.id.editTextYear);

        // set event listener for the Save Contact Button
        Button ButtonAdd = (Button) findViewById(R.id.buttonAdd);
        ButtonAdd.setOnClickListener(ButtonAddClicked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_addbook, menu);
        return true;
    }



    // responds to event generated when user clicks the Done Button
    OnClickListener ButtonAddClicked = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (EditTextTitle.getText().length() != 0)
            {
                BookSQL db = new BookSQL(AddBookActivity.this);
                db.addBook(EditTextSlot.getText().toString(), EditTextTitle.getText().toString(), EditTextYear.getText().toString());
            }
            finish();
        }
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
