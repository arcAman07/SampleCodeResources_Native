package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Basic Mysql
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR,age INT(3))");
//        myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Aman',18)");
//        myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Tejas',29)");
//        myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Deepak',45)");

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM users", null);
        int nameIndex = cursor.getColumnIndex("name");
        int ageIndex = cursor.getColumnIndex("age");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("name", cursor.getString(nameIndex));
            Log.i("age", cursor.getString(ageIndex));
            cursor.moveToNext();
        }
        // Advanced mySql
//        SQLiteDatabase newDatabase = this.openOrCreateDatabase("Events",MODE_PRIVATE,null);
//        newDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY,event VARCHAR,duration INT(4))");
//        newDatabase.execSQL("INSERT INTO events (event,duration) VALUES ('JEE',2)");
//        newDatabase.execSQL("INSERT INTO events (event,duration) VALUES ('CSE',4)");
//        newDatabase.execSQL("INSERT INTO events (event,duration) VALUES ('JEE',2)");
//        newDatabase.execSQL("INSERT INTO events (event,duration) VALUES ('CSE',4)");
//        newDatabase.execSQL("INSERT INTO events (event,duration) VALUES ('JEE',2)");
//        newDatabase.execSQL("INSERT INTO events (event,duration) VALUES ('CSE',4)");
////        Cursor newCursor = newDatabase.rawQuery("SELECT * FROM events WHERE duration>2",null);
//        Cursor newCursor = newDatabase.rawQuery("SELECT * FROM events WHERE event LIKE '%S'",null);
//        int eventIndex = newCursor.getColumnIndex("event");
//        int durationIndex = newCursor.getColumnIndex("duration");
//        int idIndex = newCursor.getColumnIndex("id");
//        newCursor.moveToFirst();
//        while(!newCursor.isAfterLast()) {
//            Log.i("required event",newCursor.getString(eventIndex));
//            Log.i("required id",newCursor.getString(durationIndex));
//            Log.i("required duration",newCursor.getString(idIndex));
//            newCursor.moveToNext();
//        }
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS fruits (colour VARCHAR,flavour INT)");
//        myDatabase.execSQL("INSERT INTO fruits (colour,flavour) VALUES ('Apple',0) ");
//        myDatabase.execSQL("INSERT INTO fruits (colour,flavour) VALUES ('Banana',1) ");
//        myDatabase.execSQL("INSERT INTO fruits (colour,flavour) VALUES ('Strawberry',2) ");
//        myDatabase.execSQL("INSERT INTO fruits (colour,flavour) VALUES ('Mango',3) ");
        Cursor newCursor = myDatabase.rawQuery("SELECT * FROM fruits WHERE colour LIKE '%a%' LIMIT 1", null);
        int colourIndex = newCursor.getColumnIndex("colour");
        int flavourIndex = newCursor.getColumnIndex("flavour");
        newCursor.moveToFirst();
        while (!newCursor.isAfterLast()) {
            Log.i("flavour", newCursor.getString(flavourIndex));
            Log.i("colour", newCursor.getString(colourIndex));
            newCursor.moveToNext();


        }
    }
}