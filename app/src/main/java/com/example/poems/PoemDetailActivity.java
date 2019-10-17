package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/*
This is the detail page of the poem with the title, author, content, explanation
 */

public class PoemDetailActivity extends AppCompatActivity {

    public static final String POEM_ID = "poemId";
    public static final String GRADE = "grade";
    private Poem poem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_detail);
        int poemId = (Integer) getIntent().getExtras().get(POEM_ID);
//        int grade = (Integer) getIntent().getExtras().get(GRADE);
//        switch (grade) {
//            case 1:
//                poem = Poem.poems1[poemId];
//                break;
//            case 3:
//                poem = Poem.poems3[poemId];
//                break;
//            default:
//                poem = Poem.poems1[poemId];
//                break;
//        }

//        TextView title = findViewById(R.id.titleDetail);
//        title.setText(poem.getTitle());
//        TextView author = findViewById(R.id.authorDetail);
//        author.setText(poem.getAuthor());
//        TextView content = findViewById(R.id.contentDetail);
//        content.setText(poem.getContent());
//        TextView desc = findViewById(R.id.contentDesc);
//        desc.setText(poem.getDescription());
//        desc.setMovementMethod(new ScrollingMovementMethod());

        try {
            SQLiteOpenHelper poemDatabaseHelper = new PoemDatabaseHelper(this);
            SQLiteDatabase db = poemDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("POEMS",
                    new String[] {"TITLE", "AUTHOR", "CONTENT", "DESCRIPTION"}, "_id = ?", new String[] {Integer.toString(poemId)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                String title = cursor.getString(1);
                String author = cursor.getString(2);
                String content = cursor.getString(3);
                String desc = cursor.getString(4);

                TextView titleView = findViewById(R.id.titleDetail);
                titleView.setText(title);
                TextView authorView = findViewById(R.id.authorDetail);
                authorView.setText(author);
                TextView contentView = findViewById(R.id.contentDetail);
                contentView.setText(content);
                TextView descView = findViewById(R.id.contentDesc);
                descView.setText(desc);
                descView.setMovementMethod(new ScrollingMovementMethod());
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Toast toast  = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}
