package com.example.lab3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "students.db"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных
    DateFormat df = new SimpleDateFormat("HH:mm:ss");

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE students ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "FIO TEXT, "
                + "TIME TEXT);");

        db.execSQL("DELETE FROM students;");
        ContentValues studentValues = new ContentValues();
        String date = df.format(Calendar.getInstance().getTime());

        studentValues.put("FIO", "Андрианов Владислав Алексеевич");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Бочаров Евгений Юрьевич");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Бажинов Иван Сергеевич");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Петров Петр Петрович");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Романов Роман Романович");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);
    }

    public void resetStudentsTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM students;");

        // Вставка новых значений
        ContentValues studentValues = new ContentValues();
        String date = df.format(Calendar.getInstance().getTime());

        // Добавляйте записи
        studentValues.put("FIO", "Андрианов Владислав Алексеевич");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Бочаров Евгений Юрьевич");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Бажинов Иван Сергеевич");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Петров Петр Петрович");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);

        studentValues.put("FIO", "Романов Роман Романович");
        studentValues.put("TIME", date);
        db.insert("students", null, studentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
}
