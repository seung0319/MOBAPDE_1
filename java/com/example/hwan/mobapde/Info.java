package com.example.hwan.layout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Hwan on 2017-11-16.
 */

public class Info extends AppCompatActivity {
    SQLiteDatabase database;
    CustomerDatabaseHelper databaseHelper;
    String tableName = "MOBAPDE";
    String databaseName = "member";
    String Cusername;
    String Cpassword;
    String Cnum;
    String Cemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try

        {
            if (database == null) {
                //  database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
                databaseHelper = new CustomerDatabaseHelper(getApplicationContext(), databaseName, null, 1);
                database = databaseHelper.getWritableDatabase();
                Toast.makeText(getApplication(), "DB :" + databaseName + " is created.", Toast.LENGTH_SHORT).show();
            } else if (database != null) {
                Toast.makeText(getApplication(), "db already opened", Toast.LENGTH_SHORT).show();
            }

        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }
        try {
            if (database != null) {
                database.execSQL("CREATE TABLE if not exists " + tableName + "(" +
                        "_id integer PRIMARY KEY autoincrement," +
                        "username text not null," +
                        "password text not null," +
                        "num text not null," +
                        "email text not null" +
                        ")");
                Toast.makeText(getApplication(), "Table :" + tableName + " is created.", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    class CustomerDatabaseHelper extends SQLiteOpenHelper {
        CustomerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onOpen(SQLiteDatabase database) {
            super.onOpen(database);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        }
    }
}
