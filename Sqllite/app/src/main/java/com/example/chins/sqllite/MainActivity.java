package com.example.chins.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;


public class MainActivity extends AppCompatActivity {


    abcDBHelper xHelper;
    TextView xEdit;

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



        xHelper = new abcDBHelper(this);
        xEdit = (TextView) findViewById(R.id.results);
        xEdit.setMovementMethod(new ScrollingMovementMethod());
    }


    public void onClickInsert(View v) {
        SQLiteDatabase db;
        String cmd;
        db = xHelper.getWritableDatabase();
        //insert
        cmd = new String("INSERT INTO dict VALUES ('b', 'boys','test');");
        db.execSQL(cmd);
        cmd = new String("INSERT INTO dict VALUES ('g', 'girl','test');");
        db.execSQL(cmd);
        xHelper.close();
        xEdit.setText("Insert Success");
    }

    public void onClickDelete(View v) {
        SQLiteDatabase db;
        String cmd;
        db = xHelper.getWritableDatabase();
        cmd = new String("DELETE FROM dict;");
        db.execSQL(cmd);
        xHelper.close();
        xEdit.setText("Delete Success");
    }

    public void onClickUpdate(View v) {
        SQLiteDatabase db;
        String cmd;
        db = xHelper.getWritableDatabase();
        cmd = new String("UPDATE dict SET word = 'ball' WHERE abc = 'b';");
        db.execSQL(cmd);
        xHelper.close();
        xEdit.setText("Update Success");
    }


    public void onClickSelect(View v) {
        SQLiteDatabase db;
        String cmd;
        db = xHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT abc, word, def FROM dict", null);
        String Result = "";
        while (cursor.moveToNext()) {
            String abc = cursor.getString(0);
            String word = cursor.getString(1);
            String def = cursor.getString(2);
            Result += (abc + " - " + word + " - " + def + "\n");
        }
        if (Result.length() == 0) {
            xEdit.setText("Empty Set");
        } else {
            xEdit.setText(Result);
        }
        cursor.close();
        xHelper.close();
    }


    //SQLiteOpenHelper
    class abcDBHelper extends SQLiteOpenHelper {
        public abcDBHelper(Context context) {
            super(context, "xWord.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
            db.execSQL("CREATE TABLE dict (abc TEXT, word TEXT, def TEXT );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS dict");
            onCreate(db);
        }
    }


}
