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
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView timeListView;
    private ArrayAdapter<String> timeListViewAdapter;
    private GridView timeGridView;
    private ArrayAdapter<String> timeGridViewAdapter;
    private ArrayList<String> listviewarray;
    private ArrayList<String> gridviewarray;

    private TableRow tr0;
    private TableRow tr1;
    private TableRow tr2;
    private TableRow tr3;
    private TableRow tr4;
    private TableRow tr5;


    int[] val_day_int = {1, 2, 3, 4, 5, 6};
    String[] val_day = {"Monday","Tuesday","Wednesday","Thursday","Friday"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         tr0 = (TableRow) findViewById(R.id.tableRow0);
         tr1 = (TableRow) findViewById(R.id.tableRow1);
         tr2 = (TableRow) findViewById(R.id.tableRow2);
         tr3 = (TableRow) findViewById(R.id.tableRow3);
         tr4 = (TableRow) findViewById(R.id.tableRow4);
         tr5 = (TableRow) findViewById(R.id.tableRow5);

//        Slot in code here

        listviewarray = new ArrayList<String>();
        gridviewarray = new ArrayList<String>();
        for (int i=0; i<20; i++) {
            gridviewarray.add("");
        }

        //List view adapter
        timeListViewAdapter = new ArrayAdapter<String>(
                MainActivity.this, R.layout.time_list_item,  listviewarray);
        timeListView = (ListView)findViewById(R.id.listView1);
        timeListView.setOnItemClickListener(viewTimeListListener);
        timeListView.setAdapter(timeListViewAdapter); // set adapter

        //Grid view adapter
        timeGridViewAdapter = new ArrayAdapter<String>(
                MainActivity.this, R.layout.time_list_item,  gridviewarray);
        timeGridView = (GridView)findViewById(R.id.gridView1);
        timeGridView.setOnItemClickListener(viewTimeGridListener);
        timeGridView.setAdapter(timeGridViewAdapter); // set adapter


        //Orientation show hide controller for grid or list
        int display_mode = getResources().getConfiguration().orientation;
        if(display_mode== Configuration.ORIENTATION_PORTRAIT){
            timeGridView.setVisibility(View.GONE);
            Log.d("myTag", "DISPLAY IS POTRAIT");
        } else if(display_mode==Configuration.ORIENTATION_LANDSCAPE){

            Log.d("myTag", "DISPLAY IS LANDSCAPE");
            timeListView.setVisibility(View.GONE);
        }
//        Slot in code here


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developer Contact : chinschian@hotmail.com", Snackbar.LENGTH_LONG)
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
            updateTimeListViewAdapter(timeList); //Original updateTimeListViewAdapter
            timeListViewAdapter.notifyDataSetChanged();

        } else if(display_mode==Configuration.ORIENTATION_LANDSCAPE){
            updateTimeGridViewAdapter(timeList); //Original timelistviewadapter

            timeGridViewAdapter.notifyDataSetChanged();
        }
        timesql.close();
    }

//List View Adapter Updater
    private void updateTimeListViewAdapter(Cursor timelist) {
        timeListViewAdapter.clear();
        String Result ="";
        while (timelist.moveToNext()){
            String moduleCode = timelist.getString(0);
            String day = timelist.getString(1);

            String startTime = timelist.getString(2);
            String rowid = timelist.getString(3);
            Result = rowid+" ."+" "+moduleCode+" " + convertDays(day) + " " + startTime; //Take note cannot have space here, else gonna mess up the token
            timeListViewAdapter.add(Result);
        }
        if(Result.length()!=0){

        }
        timelist.close();
    }

//Grid View Adapter Updater
    private void updateTimeGridViewAdapter(Cursor timelist)  {
        //Not a scalable solution, figure out new stuff soon.
        String Result ="";

        //Idea, do some position processing here and render the array
        for (int i=0; i<9; i++) {
            gridviewarray.set(i,new String("data"));
            //Rendering Button
            Button btnTag = new Button(this);
            tr1.setWeightSum((10 * (i + 1)));
            btnTag.setLayoutParams(new TableRow.LayoutParams(350, TableRow.LayoutParams.WRAP_CONTENT));
            btnTag.setText("ID:"+ i +" "+" W: " + (10 * (i * 10 + 10)));
            btnTag.setId(i);
            Log.d("myTag", "My Id:" + (i));
//            Button p1_button = (Button)findViewById(1);
//            p1_button.setText("Modified Text");
//            p1_button.setId('1');
//            btnTag.setOnClickListener(this); //Enable on click
//            int id = j+1;
//            Log.d("myTag", "ID is" +id ); //Added for debug purpose
            //btnTag.setMinWidth((10 * (j + 1)));
            //btnTag.setHeight((10 * (j + 1)));
            tr1.addView(btnTag);




            Button btnTag2 = new Button(this);
            tr2.setWeightSum((10 * (i + 1)));
            btnTag2.setLayoutParams(new TableRow.LayoutParams(350, TableRow.LayoutParams.WRAP_CONTENT));
            btnTag2.setText(" Width: " + (10 * (i * 10 + 10)));
            btnTag.setId('T' + i);
            tr2.addView(btnTag2);


            Button btnTag3 = new Button(this);
            tr3.setWeightSum((10 * (i + 1)));
            btnTag3.setLayoutParams(new TableRow.LayoutParams(350, TableRow.LayoutParams.WRAP_CONTENT));
            btnTag3.setText(" Width: " + (10 * (i * 10 + 10)));
            btnTag.setId('W' + i);
            tr3.addView(btnTag3);

            Button btnTag4 = new Button(this);
            tr4.setWeightSum((10 * (i + 1)));
            btnTag4.setLayoutParams(new TableRow.LayoutParams(350, TableRow.LayoutParams.WRAP_CONTENT));
            btnTag4.setText(" Width: " + (10 * (i * 10 + 10)));
            tr4.addView(btnTag4);

            Button btnTag5 = new Button(this);
            tr5.setWeightSum((10 * (i + 1)));
            btnTag5.setLayoutParams(new TableRow.LayoutParams(350, TableRow.LayoutParams.WRAP_CONTENT));
            btnTag5.setText(" Width: " + (10 * (i * 10 + 10)));
            tr5.addView(btnTag5);




        }
        while (timelist.moveToNext()){
            String moduleCode = timelist.getString(0);
            String day = timelist.getString(1);
            String startTime = timelist.getString(2);
            String rowid = timelist.getString(3);

//            Button p1_button = (Button)findViewById(R.layout'2');
//            p1_button.setText("Modified Text");
//            p1_button.setId('1');

            int j = Integer.parseInt(startTime.substring(0, 2));

            Result = rowid+"\n" + moduleCode + " "+ "T:"+startTime + "D:"+day;
            Log.d("myTag", "Grid List result : " + Result.toString());

            //Rendering Button
            Button btnTag = new Button(this);
            tr1.setWeightSum((10 * (j + 1)));
            btnTag.setLayoutParams(new TableRow.LayoutParams((300 * (j + 1)), TableRow.LayoutParams.WRAP_CONTENT));
            btnTag.setText(moduleCode + " " + day +" "+ startTime + " W: " + (10 * (j + 1)));
            btnTag.setId(Integer.parseInt(rowid));
            btnTag.setPadding(600,100,100,100);

            btnTag.setOnClickListener(this); //Enable on click
            int id = j+1;
            Log.d("myTag", "ID is" +id ); //Added for debug purpose
            //btnTag.setMinWidth((10 * (j + 1)));
            //btnTag.setHeight((10 * (j + 1)));





            tr1.addView(btnTag);




            int pos = Integer.parseInt(rowid);
//	        bookGridViewAdapter.insert(Result, pos );
            gridviewarray.set(pos-1,new String(Result));
        }
        if(Result.length()!=0){
// add a log here soon
        }
        timelist.close();
    }



    AdapterView.OnItemClickListener viewTimeListListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            //Need to modify here to get rowid instead of Module Code
            Intent viewTimeDetails  =
                    new Intent(MainActivity.this, ViewTimeDetails.class);
            String s = timeListView.getItemAtPosition(position).toString();
            Log.d("myTag", "Value of itemPosition : "+s);
            StringTokenizer st = new StringTokenizer( s, " " ); //Split value to tokens with empty space as deliminator
            Log.d("myTag", "Value of st : "+ st.toString());
            String slotid=st.nextToken(); //Get the first token
            Log.d("myTag", "Value of nextToken : " + slotid.toString());

            viewTimeDetails.putExtra("_id", slotid); //PHP GET
            startActivity(viewTimeDetails);
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
            Log.d("myTag", "Value of itemPosition : "+s);
            StringTokenizer st = new StringTokenizer( s, " \n" );
            Log.d("myTag", "Value of st : "+ st.toString());
            String slot_id = st.nextToken();
            Log.d("myTag", "Value of nextToken : " + slot_id.toString());
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

    public String convertDays(String day){
        String returnday = "Day";

        switch (day){
            case "1":
                returnday = "Monday";
                break;
            case "2":
                returnday = "Tuesday";
                break;
            case "3":
                returnday = "Wednesday";
                break;
            case "4":
                returnday = "Thursday";
                break;
            case "5":
                returnday = "Friday";
                break;
            default:
                returnday = day;
                break;
        }

        return returnday;

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        Log.d("myTag", "Dynamic Button Pressed");
//        btnTag = ((Button) findViewById('1'));
//        tableMain.this.findViewById();
        final int id_ = v.getId();

        Log.d("myTag", "ID is " + id_);
//        v.findViewById(id_)= ((Button) findViewById(id_));
//        Button newPicButton = (Button)findViewById( );
//        final int id_ = tableMain.this.getApplicationContext();
        Toast.makeText(MainActivity.this.getApplicationContext(),
                "Button clicked index " + id_, Toast.LENGTH_SHORT)
                .show();

        Intent viewTimeDetails  =
                new Intent(MainActivity.this, ViewTimeDetails.class);
        //String s = timeListView.getItemAtPosition(position).toString();
        //Log.d("myTag", "Value of itemPosition : "+s);
        //StringTokenizer st = new StringTokenizer( s, " " ); //Split value to tokens with empty space as deliminator
        //Log.d("myTag", "Value of st : "+ st.toString());
        //String slotid=st.nextToken(); //Get the first token
        //Log.d("myTag", "Value of nextToken : " + slotid.toString());

        viewTimeDetails.putExtra("_id",Integer.toString(id_)); //PHP GET
        startActivity(viewTimeDetails);


//        Intent timetable  =
//                new Intent(tableMain.this, timetable.class);
//        startActivity(timetable);


    }
}
