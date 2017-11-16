package com.example.hwan.layout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Francis on 15/11/2017.
 */

public class LoginActivity extends Info {

    EditText usernameText;
    EditText passwordText;
    String Username;
    String Password;

    protected void onCreate(Bundle savedInstanceState) {
        /*if (getIntent().getExtras() == null) {
            startActivity(new Intent(this, StartView.class));
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        usernameText = (EditText) findViewById(R.id.userNameLogin);
        passwordText = (EditText) findViewById(R.id.passwordLogin);
    }

    public void Login(View v) {

        if (database != null) {
            Cursor cursor = database.rawQuery("SELECT username, password FROM " + tableName, null);
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();

                Cusername = cursor.getString(0);
                Cpassword = cursor.getString(1);

            }

            Username = usernameText.getText().toString();
            Password = passwordText.getText().toString();

            if (Username.equals(Cusername) && Password.equals(Cpassword)) {
                Intent main = new Intent(getApplication(), MainPage.class);
                main.putExtra("splash", "splash");
                startActivity(main);
                finish();
                Toast.makeText(getApplicationContext(), Cusername + " welcome!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), " Incorrect username/password",
                        Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }

    public void Register(View view){

        Intent register = new Intent(getApplication(), RegisterActivity.class);
        register.putExtra("splash", "splash");
        startActivity(register);

    }
}
