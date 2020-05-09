package com.example.poems.poem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poems.DatabaseHelper;
import com.example.poems.R;

import java.util.ArrayList;

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
public class PoemListByAuthorActivity extends AppCompatActivity {
    public static final String AUTHOR = "author";
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
    private String pageSource;
    private String author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_list);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
            Intent intent = new Intent(PoemListByAuthorActivity.this, PoemDetailActivity.class);
            if (cursor.moveToPosition(position)) {
                poemId = cursor.getInt(0);
                poemIsRecited = (cursor.getInt(2) == 1);// 0 - false; 1 - true
                pId = cursor.getInt(3);
            }
            pageSource = "poemListByAuthor";
            intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
            intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
            intent.putExtra(PoemDetailActivity.P_ID, pId);
            intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, poemIdList);
            intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
            intent.putExtra(PoemDetailActivity.GRADE, grade);
            intent.putExtra(PoemDetailActivity.PAGE_SOURCE, pageSource);
            startActivity(intent);
            }
        };
        ListView listView = findViewById(R.id.poem_list);
//        listView.setOnItemClickListener(itemClickListener);
//        ListView gradeOneList = getListView();
        author =  (String) getIntent().getExtras().get(AUTHOR);
        TextView gradeTextView = findViewById(R.id.grade_list_title);
        gradeTextView.setText(author + "古诗词列表");

        try {
            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(this);
            db = poemDatabaseHelper.getReadableDatabase();
            cursor = db.query("POEM",
                    new String[] {"_id", "TITLE", "IS_PASS", "POEM_ID"}, "author LIKE ?", new String[] {"%" + author},
                    null, null, "_id");
            while (cursor.moveToNext()) {
                poemId = cursor.getInt(0);
                poemIdList.add(poemId);
                String poem = cursor.getString(1);
                System.out.println(poem);
            }
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.poem_item,
                    cursor, new String[]{"TITLE"}, new int[]{R.id.poem_name}, 0);
            listView.setAdapter(cursorAdapter);
            listView.setOnItemClickListener(itemClickListener);
        } catch(SQLiteException e) {
            System.out.print(e);
            Toast toast  = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }

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
