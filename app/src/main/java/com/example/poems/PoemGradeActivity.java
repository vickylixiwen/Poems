package com.example.poems;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

//public class PoemGradeActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_poem_grade);
//
//        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
//                Intent intent = new Intent(PoemGradeActivity.this, PoemDetailActivity.class);
//                startActivity(intent);
//
//            }
//        };
//        ListView listView = findViewById(R.id.grade_one_list);
//        listView.setOnItemClickListener(itemClickListener);
//    }
//}

/*
This is the list of poems of the specific grade
 */
public class PoemGradeActivity extends ListActivity {
    public static final String GRADE = "grade";
    private int grade;
    private Poem[] poems;
    private Cursor cursor;
    private SQLiteDatabase db;
    private int poemId;
    private int poemTotal;
    private ArrayList<Integer> poemIdList = new ArrayList<Integer>();
    private boolean poemIsRecited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView gradeOneList = getListView();
        grade = (Integer) getIntent().getExtras().get(GRADE);

        try {
            SQLiteOpenHelper poemDatabaseHelper = new PoemDatabaseHelper(this);
            db = poemDatabaseHelper.getReadableDatabase();
            cursor = db.query("POEM",
                    new String[] {"_id", "TITLE", "IS_PASS"}, "grade = ?", new String[] {Integer.toString(grade)},
                    null, null, "_id");
            while (cursor.moveToNext()) {
                poemId = cursor.getInt(0);
                poemIdList.add(poemId);
            }
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                    cursor, new String[]{"TITLE"}, new int[]{android.R.id.text1}, 0);
            gradeOneList.setAdapter(cursorAdapter);
        } catch(SQLiteException e) {
            System.out.print(e);
            Toast toast  = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Intent intent = new Intent(PoemGradeActivity.this, PoemDetailActivity.class);
        if (cursor.moveToPosition(position)) {
            poemId = cursor.getInt(0);
            poemIsRecited = (cursor.getInt(2) == 1);// 0 - false; 1 - true
        }
        intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
        intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
        intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, poemIdList);
        intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        if (db!=null) {
            super.onDestroy();
            cursor.close();
            db.close();
        }
    }
}
