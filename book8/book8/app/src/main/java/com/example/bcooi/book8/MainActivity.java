package com.example.bcooi.book8;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    private ListView bookListView;
    private ArrayAdapter<String> bookListViewAdapter;
    private GridView bookGridView;
    private ArrayAdapter<String> bookGridViewAdapter;
    private ArrayList<String> listviewarray;
    private ArrayList<String> gridviewarray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewarray = new ArrayList<String>();
        gridviewarray = new ArrayList<String>();
        for (int i=0; i<20; i++) {
            gridviewarray.add("");
        }

        bookListViewAdapter = new ArrayAdapter<String>(
                MainActivity.this, R.layout.book_list_item,  listviewarray);
        bookListView = (ListView)findViewById(R.id.listView1);
        bookListView.setOnItemClickListener(viewBookListListener);
        bookListView.setAdapter(bookListViewAdapter); // set adapter

        bookGridViewAdapter = new ArrayAdapter<String>(
                MainActivity.this, R.layout.book_list_item,  gridviewarray);
        bookGridView = (GridView)findViewById(R.id.gridView1);
        bookGridView.setOnItemClickListener(viewBookGridListener);
        bookGridView.setAdapter(bookGridViewAdapter); // set adapter


        int display_mode = getResources().getConfiguration().orientation;
        if(display_mode== Configuration.ORIENTATION_PORTRAIT){
            bookGridView.setVisibility(View.GONE);
        } else if(display_mode==Configuration.ORIENTATION_LANDSCAPE){
            bookListView.setVisibility(View.GONE);
        }
    }    //onCreate();

    @Override
    protected void onResume() {
        super.onResume();
        BookSQL booksql = new BookSQL(MainActivity.this);
        booksql.open();
        Cursor booklist =	booksql.getAllBooks();
        //bookListViewAdapter.changeCursor(booklist); // set the adapter's Cursor
        int display_mode = getResources().getConfiguration().orientation;
        if(display_mode==Configuration.ORIENTATION_PORTRAIT){
            updateBookListViewAdapter(booklist);
            bookListViewAdapter.notifyDataSetChanged();
        } else if(display_mode==Configuration.ORIENTATION_LANDSCAPE){
            updateBookGridViewAdapter(booklist);
            bookGridViewAdapter.notifyDataSetChanged();
        }
        booksql.close();
    }


    private void updateBookListViewAdapter(Cursor booklist) {
        bookListViewAdapter.clear();
        String Result ="";
        while (booklist.moveToNext()){
            String slot = booklist.getString(0);
            String title = booklist.getString(1);
            Result = slot+" = " + title;
            bookListViewAdapter.add(Result);
        }
        if(Result.length()!=0){

        }
        booklist.close();
    }

    private void updateBookGridViewAdapter(Cursor booklist) {
        String Result ="";
        for (int i=0; i<20; i++) {
            gridviewarray.set(i,new String(""));
        }
        while (booklist.moveToNext()){
            String slot = booklist.getString(0);
            String title = booklist.getString(1);
            Result = slot+"\n" + title;
            int pos = Integer.parseInt(slot);
//	        bookGridViewAdapter.insert(Result, pos );
            gridviewarray.set(pos-1,new String(Result));
        }
        if(Result.length()!=0){

        }
        booklist.close();
    }



    OnItemClickListener viewBookListListener = new OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            Intent viewBookDetails  =
                    new Intent(MainActivity.this, ViewbookDetails.class);
            String s = bookListView.getItemAtPosition(position).toString();
            StringTokenizer st = new StringTokenizer( s, " " );
            String slotid=st.nextToken();
            viewBookDetails.putExtra("_id", slotid);
            startActivity(viewBookDetails);
        }
    };

    OnItemClickListener viewBookGridListener = new OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            Intent viewBookDetails  =
                    new Intent(MainActivity.this, ViewbookDetails.class);
            String s = bookGridView.getItemAtPosition(position).toString();
            StringTokenizer st = new StringTokenizer( s, " \n" );
            String slot_id = st.nextToken();
            viewBookDetails.putExtra("_id", slot_id);

            startActivity(viewBookDetails);
        }
    };

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
                    Intent addbook =
                            new Intent(MainActivity.this, AddBookActivity.class);
                    startActivity(addbook);
                    break;
            }

        return super.onOptionsItemSelected(item);
    }
}
