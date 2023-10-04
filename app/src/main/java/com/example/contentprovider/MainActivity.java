package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void onClickAddDetails(View view) {
        ContentValues values = new ContentValues();
        values.put(UserProvider.name, ((EditText)findViewById(R.id.textName)).getText().toString());
        getContentResolver().insert(UserProvider.CONTENT_URI, values);
        Toast.makeText(this, "Inserted successful", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("Range")
    public void onClickShowDetails(View view) {
        TextView result = (TextView) findViewById(R.id.res);

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.contentprovider.UserProvider/users"), null, null, null, null);

        if (cursor.moveToFirst()) {
            StringBuilder stringBuilder = new StringBuilder();
            while (!cursor.isAfterLast()) {
                stringBuilder.append("\n"+cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            result.setText(stringBuilder);
        } else {
            result.setText("DB empty");
        }
    }


}