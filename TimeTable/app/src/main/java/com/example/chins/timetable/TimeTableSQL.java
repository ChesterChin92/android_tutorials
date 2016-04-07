package com.example.chins.timetable;

/**
 * Created by chins on 3/22/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class TimeTableSQL {
    SQLiteDatabase db; // database object
    String cmd;
    private DatabaseOpenHelper xHelper; // database helper
    Context _context;

    public TimeTableSQL(Context context)
    {
        _context = context;
        xHelper =
                new DatabaseOpenHelper(context, "timetabledb", null, 1);
    }

    public void open()
    {
        db = xHelper.getWritableDatabase();
    }

    // close the database connection
    public void close()
    {
        if (db != null)
            db.close();
    }


    public void addTimeTable(String moduleCode, String day, String startTime,String duration, String session,String room)
    {
        open();
        cmd = new String ("INSERT INTO timetable VALUES ("+"'"+moduleCode+"', '"+day+"', '"+startTime+"','"+duration+"','"+session+"','"+room+"','1');");
        db.execSQL(cmd);
        close();
    }

    //Used in List and Grid View in Content Main
    public Cursor getAllTimeTable()
    {
        return db.query("timetable", new String[] {"moduleCode", "day", "startTime","rowid"},
                null, null, null, null, "day"); //Sort by day
    }

    public Cursor getOneTime(String id)
    {
        return db.query(
                "timetable", null, "rowid=" + id, null, null, null, null);
    }


    //Inprogress, NOT COMPLETE
    public Cursor getID(String id)
    {
        return db.rawQuery("SELECT rowid,moduleCode,day,startTime FROM timetable WHERE rowid="+id+"",null);
    }

    public void deleteTimeTable(String id)
    {
        open();
        cmd = new String ("DELETE FROM timetable WHERE (rowid=" + id + ");");
        db.execSQL(cmd);
        close();
    }

    public class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE timetable ( moduleCode TEXT,day TEXT, startTime TEXT, duration TEXT, sessionType TEXT, room TEXT, slot TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS timetable");
            onCreate(db);
        }
    }
}
