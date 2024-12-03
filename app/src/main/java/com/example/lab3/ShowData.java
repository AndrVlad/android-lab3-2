package com.example.lab3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShowData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_data);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        try {
            DBHelper myDatabaseHelper = new DBHelper(this);
            SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("students", new String[] {"_id",
                    "First_Name", "Name", "Patronymic", "TIME"},null, null, null, null, null);


            while (cursor.moveToNext()) {
                TableRow tableRow = new TableRow(this);
                String _id = cursor.getString(0);
                String First_Name = cursor.getString(1);
                String Name = cursor.getString(2);
                String Patronymic = cursor.getString(3);
                String TIME = cursor.getString(4);

                TextView idTextView = new TextView(this);
                idTextView.setText(_id);
                idTextView.setPadding(8, 8, 8, 8);
                tableRow.addView(idTextView);

                TextView F_name_TextView = new TextView(this);
                F_name_TextView.setText(First_Name);
                F_name_TextView.setPadding(8, 8, 8, 8);
                tableRow.addView(F_name_TextView);

                TextView NameTextView = new TextView(this);
                NameTextView.setText(Name);
                NameTextView.setPadding(8, 8, 8, 8);
                tableRow.addView(NameTextView);

                TextView PTextView = new TextView(this);
                PTextView.setText(Patronymic);
                PTextView.setPadding(8, 8, 8, 8);
                tableRow.addView(PTextView);

                TextView TimeTextView = new TextView(this);
                TimeTextView.setText(TIME);
                TimeTextView.setPadding(8, 8, 8, 8);
                tableRow.addView(TimeTextView);

                tableLayout.addView(tableRow);
            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}