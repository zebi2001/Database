package com.zuhaib.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editUserID;
    EditText editUserName;
    EditText editUserPassword;
    DatabaseManager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUserID = (EditText) findViewById(R.id.editTextID);
        editUserName = (EditText) findViewById(R.id.editTextUsername);
        editUserPassword = (EditText) findViewById(R.id.editTextPassword);

        dbmanager = new DatabaseManager(this);
        try {
            dbmanager.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void btnInsertPressed (View v){
        dbmanager.insert(editUserName.getText().toString(), editUserPassword.getText().toString());

    }
    public void btnFetchPressed (View v){
        Cursor cursor = dbmanager.fetch();
        if (cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_ID));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME));
                @SuppressLint("Range")String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD));

                Log.i("DATABASE_TAG", "I have read ID: " + ID + " Username " + username + "Password" + password);
            }while (cursor.moveToNext());
        }

    }
    public void btnFUpdatePressed(View v){

        dbmanager.update(Long.parseLong(editUserID.getText().toString()),editUserName.getText().toString(), editUserPassword.getText().toString());
    }
    public void btnDeletePressed(View v){

        dbmanager.delete(Long.parseLong(editUserID.getText().toString()));

    }
}