package com.example.poems.poem;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poems.DatabaseHelper;
import com.example.poems.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

//public class PoemListActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_poem_list);
//
//        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
//                Intent intent = new Intent(PoemListActivity.this, PoemDetailActivity.class);
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
//public class PoemListActivity extends ListActivity {
public class PoemListActivity extends AppCompatActivity {
    public static final String GRADE = "grade";
    private int grade;
    private Poem[] poems;
    private Cursor cursor;
    private SQLiteDatabase db;
    private int poemId;
    private int pId;
    private int poemTotal;
    private ArrayList<Integer> poemIdList = new ArrayList<Integer>();
    private boolean poemIsRecited;
    private String grade_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_list);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
            Intent intent = new Intent(PoemListActivity.this, PoemDetailActivity.class);
            if (cursor.moveToPosition(position)) {
                poemId = cursor.getInt(0);
                poemIsRecited = (cursor.getInt(2) == 1);// 0 - false; 1 - true
                pId = cursor.getInt(3);
            }
            intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
            intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
            intent.putExtra(PoemDetailActivity.P_ID, pId);
            intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, poemIdList);
            intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
            intent.putExtra(PoemDetailActivity.GRADE, grade);
            startActivity(intent);
            }
        };
        ListView listView = findViewById(R.id.poem_list);
        listView.setOnItemClickListener(itemClickListener);
//        ListView gradeOneList = getListView();
        grade = (Integer) getIntent().getExtras().get(GRADE);
        switch (grade) {
            case 1:
                grade_text = "一年级";
                break;
            case 2:
                grade_text = "二年级";
                break;
            case 3:
                grade_text = "三年级";
                break;
            case 4:
                grade_text = "四年级";
                break;
            case 5:
                grade_text = "五年级";
                break;
            case 6:
            default:
                grade_text = "六年级";
                break;
        }

        TextView gradeTextView = findViewById(R.id.grade_list_title);
        gradeTextView.setText(grade_text + "古诗列表");

        try {
            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(this);
            db = poemDatabaseHelper.getReadableDatabase();
            cursor = db.query("POEM",
                    new String[] {"_id", "TITLE", "IS_PASS", "POEM_ID"}, "grade = ?", new String[] {Integer.toString(grade)},
                    null, null, "_id");
            while (cursor.moveToNext()) {
                poemId = cursor.getInt(0);
                poemIdList.add(poemId);
            }
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.poem_item,
                    cursor, new String[]{"TITLE"}, new int[]{R.id.poem_name}, 0);
//            gradeOneList.setAdapter(cursorAdapter);
            listView.setAdapter(cursorAdapter);

        } catch(SQLiteException e) {
            System.out.print(e);
            Toast toast  = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }

    }

//    public void onListItemClick(ListView listView, View itemView, int position, long id) {
//        Intent intent = new Intent(PoemListActivity.this, PoemDetailActivity.class);
//        if (cursor.moveToPosition(position)) {
//            poemId = cursor.getInt(0);
//            poemIsRecited = (cursor.getInt(2) == 1);// 0 - false; 1 - true
//            pId = cursor.getInt(3);
//        }
//        intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
//        intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
//        intent.putExtra(PoemDetailActivity.P_ID, pId);
//        intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, poemIdList);
//        intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
//        intent.putExtra(PoemDetailActivity.GRADE, grade);
//        startActivity(intent);
//    }

    @Override
    public void onDestroy() {
        if (db!=null) {
            super.onDestroy();
            cursor.close();
            db.close();
        }
    }
}
