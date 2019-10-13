package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

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
        int grade = (Integer) getIntent().getExtras().get(GRADE);
        switch (grade) {
            case 1:
                poem = Poem.poems1[poemId];
                break;
            case 3:
                poem = Poem.poems3[poemId];
                break;
            default:
                poem = Poem.poems1[poemId];
                break;
        }

        TextView title = findViewById(R.id.titleDetail);
        title.setText(poem.getTitle());
        TextView author = findViewById(R.id.authorDetail);
        author.setText(poem.getAuthor());
        TextView content = findViewById(R.id.contentDetail);
        content.setText(poem.getContent());
        TextView desc = findViewById(R.id.contentDesc);
        desc.setText(poem.getDescription());
        desc.setMovementMethod(new ScrollingMovementMethod());
    }
}
