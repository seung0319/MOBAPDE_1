package com.example.hwan.layout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Francis on 15/11/2017.
 */

public class RegisterActivity extends Info{
    EditText USERNAME,PASSWORD,NUM,EMAIL;
    String Tusername, Tpassword, Tnum,Temail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_layout);
        USERNAME = (EditText) findViewById(R.id.editUserName);
        PASSWORD = (EditText) findViewById(R.id.editPassWord);
        NUM = (EditText) findViewById(R.id.editPhoneNumber);
        EMAIL = (EditText) findViewById(R.id.editEmail);


        Button signup = (Button) findViewById(R.id.signUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tusername = USERNAME.getText().toString();
                Tpassword = PASSWORD.getText().toString();
                Tnum = NUM.getText().toString();
                Temail = EMAIL.getText().toString();
                Cursor cursor = database.rawQuery("SELECT username, num, email FROM " + tableName, null);
                int count = cursor.getCount();
                for(int i=0;i<count;i++) {
                    cursor.moveToNext();

                    Cusername = cursor.getString(0);
                    Cnum = cursor.getString(1);
                    Cemail = cursor.getString(2);

                }

                if (Tpassword.length() <8) {
                    Toast.makeText(getApplicationContext(), "Password should be more than 8 digits",
                            Toast.LENGTH_SHORT).show();
                } else if (Tnum.length() <11 || Tusername.equals(Cnum)) {
                    Toast.makeText(getApplicationContext(), "Phone number should be in 11 digits",
                            Toast.LENGTH_SHORT).show();
                }/* else if (Temail.length() <3) {
                    Toast.makeText(getApplicationContext(), "incorrect email",
                            Toast.LENGTH_SHORT).show();
                } else if (Tusername.length()<2) {
                    Toast.makeText(getApplicationContext(), "incorrect username",
                            Toast.LENGTH_SHORT).show();
                }*/ else {
                    try{
                        if (database != null) {
                            database.execSQL("INSERT INTO " + tableName + "(username, password, num, email) VALUES" +
                                    "(" + "'" + Tusername + "'" + "," + "'" + Tpassword + "'" + "," + Tnum + "," + "'" + Temail + "'" + ")");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent login = new Intent(getApplication(), LoginActivity.class);
                    login.putExtra("splash", "splash");
                    startActivity(login);
                    finish();
                    Toast.makeText(getApplication(), Tusername + " registered!", Toast.LENGTH_SHORT).show();


                }


            }
        });
    }
}
