package com.example.chins.timetable;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private ListView timeListView;
    private ArrayAdapter<String> timeListViewAdapter;
    private GridView timeGridView;
    private ArrayAdapter<String> timeGridViewAdapter;
    private ArrayList<String> listviewarray;
    private ArrayList<String> gridviewarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        Slot in code here

        listviewarray = new ArrayList<String>();
        gridviewarray = new ArrayList<String>();
        for (int i=0; i<20; i++) {
            gridviewarray.add("");
        }

        timeListViewAdapter = new ArrayAdapter<String>(
                MainActivity.this, R.layout.time_list_item,  listviewarray);
        timeListView = (ListView)findViewById(R.id.listView1);
        timeListView.setOnItemClickListener(viewTimeListListener);
        timeListView.setAdapter(timeListViewAdapter); // set adapter

        timeGridViewAdapter = new ArrayAdapter<String>(
                MainActivity.this, R.layout.time_list_item,  gridviewarray);
        timeGridView = (GridView)findViewById(R.id.gridView1);
        timeGridView.setOnItemClickListener(viewTimeGridListener);
        timeGridView.setAdapter(timeGridViewAdapter); // set adapter


        int display_mode = getResources().getConfiguration().orientation;
        if(display_mode== Configuration.ORIENTATION_PORTRAIT){
            timeGridView.setVisibility(View.GONE);
        } else if(display_mode==Configuration.ORIENTATION_LANDSCAPE){
            timeGridView.setVisibility(View.GONE);
        }
//        Slot in code here


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }// end of onCreate

//Code slot in here
    @Override
    protected void onResume() {
        super.onResume();
        TimeTableSQL timesql = new TimeTableSQL(MainActivity.this);
        timesql.open();
        Cursor timeList =	timesql.getAllTimeTable();
        //bookListViewAdapter.changeCursor(booklist); // set the adapter's Cursor
        int display_mode = getResources().getConfiguration().orientation;
        if(display_mode==Configuration.ORIENTATION_PORTRAIT) {
            updateBookListViewAdapter(timeList);
            timeListViewAdapter.notifyDataSetChanged();

        } else if(display_mode==Configuration.ORIENTATION_LANDSCAPE){
            updateTimeListViewAdapter(timeList);
            timeGridViewAdapter.notifyDataSetChanged();
        }
        timesql.close();
    }


    private void updateBookListViewAdapter(Cursor timelist) {
        timeListViewAdapter.clear();
        String Result ="";
        while (timelist.moveToNext()){
            String slot = timelist.getString(0);
            String title = timelist.getString(1);
            Result = slot+" = " + title;
            timeListViewAdapter.add(Result);
        }
        if(Result.length()!=0){

        }
        timelist.close();
    }

    private void updateTimeListViewAdapter(Cursor timelist) {
        String Result ="";
        for (int i=0; i<20; i++) {
            gridviewarray.set(i,new String(""));
        }
        while (timelist.moveToNext()){
            String slot = timelist.getString(0);
            String title = timelist.getString(1);
            Result = slot+"\n" + title;
            int pos = Integer.parseInt(slot);
//	        bookGridViewAdapter.insert(Result, pos );
            gridviewarray.set(pos-1,new String(Result));
        }
        if(Result.length()!=0){

        }
        timelist.close();
    }



    AdapterView.OnItemClickListener viewTimeListListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            Intent viewBookDetails  =
                    new Intent(MainActivity.this, ViewTimeDetails.class);
            String s = timeListView.getItemAtPosition(position).toString();
            StringTokenizer st = new StringTokenizer( s, " " );
            String slotid=st.nextToken();
            viewBookDetails.putExtra("_id", slotid);
            startActivity(viewBookDetails);
        }
    };

    AdapterView.OnItemClickListener viewTimeGridListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            Intent viewTimeDetails  =
                    new Intent(MainActivity.this, ViewTimeDetails.class);
            String s = timeGridView.getItemAtPosition(position).toString();
            StringTokenizer st = new StringTokenizer( s, " \n" );
            String slot_id = st.nextToken();
            viewTimeDetails.putExtra("_id", slot_id);

            startActivity(viewTimeDetails);
        }
    };

//Code slot in end here
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
        switch (item.getItemId()) {
            case R.id.menuAdd:
                Log.i("TRACEEE", "main menuAdd");
                Intent addTime =
                        new Intent(MainActivity.this, AddTimeActivity.class);
                startActivity(addTime);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}