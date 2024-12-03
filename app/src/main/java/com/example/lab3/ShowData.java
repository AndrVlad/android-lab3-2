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
                    "FIO", "TIME"},null, null, null, null, null);


            while (cursor.moveToNext()) {
                TableRow tableRow = new TableRow(this);
                String _id = cursor.getString(0);
                String FIO = cursor.getString(1);
                String TIME = cursor.getString(2);

                TextView idTextView = new TextView(this);
                idTextView.setText(_id);
                idTextView.setPadding(8, 8, 8, 8);
                tableRow.addView(idTextView);

                TextView FIOTextView = new TextView(this);
                FIOTextView.setText(FIO);
                FIOTextView.setPadding(8, 8, 8, 8);
                tableRow.addView(FIOTextView);

                TextView TIMETextView = new TextView(this);
                TIMETextView.setText(TIME);
                TIMETextView.setPadding(8, 8, 8, 8);
                tableRow.addView(TIMETextView);

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