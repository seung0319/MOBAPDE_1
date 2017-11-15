package com.example.hwan.mobapde;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText editUserName, editPassWord, editEmail, editPhoneNumber;
    TextView textView;
    String databaseName;
    SQLiteDatabase database;
    String tableName;
    String UserName;
    String Password;
    String PhoneNumber;
    String Email;

    EditText editText;
    EditText editText2;
    String Text;
    String Text2;
    CustomerDatabaseHelper databaseHelper;
    String Cusername;
    String Cpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editUserName = (EditText)findViewById(R.id.editUserName);
        editPassWord = (EditText)findViewById(R.id.editPassWord);
        editPhoneNumber = (EditText)findViewById(R.id.editPhoneNumber);
        editEmail = (EditText)findViewById(R.id.editEmail);

        textView = (TextView)findViewById(R.id.textView);
        databaseName = "member";
        try{
            if(database==null){

                //database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE,null);

                databaseHelper = new CustomerDatabaseHelper(getApplicationContext(),databaseName,null,1);
                database = databaseHelper.getWritableDatabase();
                Toast.makeText(this,"DB :"+databaseName+"Created",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        tableName = "JOIN";
        try{
            //   tableName = "join";
            if (database != null) {
                database.execSQL("CREATE TABLE if not exists " + "MOBAPDE" + "(" +
                        "_id integer PRIMARY KEY autoincrement," +
                        "username text," +
                        "password text," +
                        "phonenumber integer," +
                        "email text" +
                        ")");
                Toast.makeText(this,"table : " + "MOBAPDE" + " Created",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public void SignUp(View v){
        try {
            if(tableName==null){
                //       tableName = "join";

            }
            if(database!=null){
                UserName = editUserName.getText().toString();
                Password = editPassWord.getText().toString();
                PhoneNumber = editPhoneNumber.getText().toString();
                Email = editEmail.getText().toString();

                database.execSQL("INSERT INTO " + "MOBAPDE" + "(username, password, phonenumber, email) VALUES" +
                        "("+"'"+UserName+"'"+","+"'"+Password+"'"+","+PhoneNumber+","+"'"+Email+"'"+");");

                println("added data");
                Toast.makeText(this,"Signed Up",Toast.LENGTH_SHORT).show();
            }

            else {
                println("you have to open your db first");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void println(String data){

        textView.append(data+"\n");

    }
    class CustomerDatabaseHelper extends SQLiteOpenHelper {

        CustomerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onOpen(SQLiteDatabase database) {
            super.onOpen(database);

            //Toast.makeText(getApplicationContext(),"Helper의 onOpen()호출됨",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            //Toast.makeText(getApplicationContext(),"Helper의 onCreate()호출됨",Toast.LENGTH_LONG).show();

            //createTable(database);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            //Toast.makeText(getApplicationContext(),"Helper의 onUpgrade()호출됨 :"+oldVersion+" => "+newVersion,Toast.LENGTH_LONG).show();

            //    changeTable(database);
        }
    }

    public void login(View v) {
        if (database != null) {

            Cursor cursor = database.rawQuery("SELECT username, password FROM " + "MOBAPDE", null);

            int count = cursor.getCount(); // 이 코드가 있어야 등록된 정보들을 전부 불러올 수
            for(int i=0;i<count;i++) {       // 있습니다. 이 코드만 추가해주시면 가입한 모든 아이디
                cursor.moveToNext();     // 로 로그인이 가능해요!!
                Cusername = cursor.getString(0);
                Cpassword = cursor.getString(1);
            }

            Text = editText.getText().toString();
            Text2 = editText2.getText().toString();
            if (Text.equals(Cusername) && Text2.equals(Cpassword)) {//내장디비인 SQLite에서 가져온 이름, 전공과 입력한 이름, 전공을 비교하여 같으면 로그인이 되게한다.
                Intent intent = new Intent(getApplication(), NewPage.class);

                startActivity(intent);
                Toast.makeText(getApplication(),"Welcome " + Text +"!",Toast.LENGTH_SHORT).show();
                finish();

            }
            else{
                Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show();
            }

            cursor.close();

        }
    }
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

        return super.onOptionsItemSelected(item);
    }
}