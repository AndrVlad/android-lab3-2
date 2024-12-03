package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            DBHelper myDatabaseHelper = new DBHelper(this);
            db = myDatabaseHelper.getWritableDatabase();
            myDatabaseHelper.resetStudentsTable();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onClickBtn1(View view) {
        Intent intent = new Intent(this, ShowData.class);
        startActivity(intent);
    }

    public void onClickBtn2(View view) {
        ContentValues studentValues = new ContentValues();
        String date = df.format(Calendar.getInstance().getTime());

        studentValues.put("First_Name", "Кашеваров");
        studentValues.put("Name", "Андрей");
        studentValues.put("Patronymic", "Евгеньевич");
        studentValues.put("TIME", date);

        if(db.insert("students", null, studentValues) == -1) {
            Toast toast = Toast.makeText(this, "Ошибка при добавлении записи", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT);
            toast.show();
        };
    }

    public void onClickBtn3(View view) {
        Integer id_key;
        Cursor cursor = db.query("students", new String[] {"MAX(_id) as max_id"},null, null, null, null, null);

        if(cursor.moveToFirst()) {
            id_key = cursor.getInt(0);
            ContentValues studentValue = new ContentValues();
            studentValue.put("First_Name", "Иванов");
            studentValue.put("Name", "Иван");
            studentValue.put("Patronymic", "Иванович");
            db.update("students",
                    studentValue,
                    "_id = ?",
                    new String[] {Integer.toString(id_key)});
            Toast toast = Toast.makeText(this, "Запись изменена", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "Ошибка при добавлении записи", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }
}