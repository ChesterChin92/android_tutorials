package com.example.bcooi.book8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BookSQL			 
{
   SQLiteDatabase db; // database object
   String cmd;
   private DatabaseOpenHelper xHelper; // database helper
   Context _context;

   public BookSQL(Context context) 
   {
	      	_context = context;
	   		xHelper = 
	    	         new DatabaseOpenHelper(context, "bookdb", null, 1);
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

   
   public void addBook(String slot, String title, String year) 
   {
      open();
      cmd = new String ("INSERT INTO book VALUES ("
    			+"'"+slot+"', '"+title+"', '"+year+"');");      
      db.execSQL(cmd);
      close();
   }
   
   public Cursor getAllBooks() 
   {
	   return db.query("book", new String[] {"slot", "title", "year"}, 
		         null, null, null, null, "slot");     
   }

   public Cursor getOneBook(String id) 
   {
      return db.query(
         "book", null, "slot=" + id, null, null, null, null);
   }

   public void deleteBook(String id) 
   {
      open();
      cmd = new String ("DELETE FROM book WHERE (slot=" + id + ");");
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
    	  db.execSQL("CREATE TABLE book ( slot TEXT, title TEXT, year TEXT);");
    	}

    	@Override
    	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	  db.execSQL("DROP TABLE IF EXISTS dict");
    	  onCreate(db);
   	  	}
   }
   
}

