package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/*
This is for chengyudiangu detail page
 */
public class IdiomDetailActivity extends AppCompatActivity {
    public static final String IDIOM_ID = "idiom_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom_detail);
        int idiom_id = (Integer) getIntent().getExtras().get(IDIOM_ID);
        Idiom idiom = Idiom.IDIOMS[idiom_id];
        TextView name = findViewById(R.id.idiomNameDetail);
        name.setText(idiom.getIdiomName());
        TextView source = findViewById(R.id.idiomSourceDetail);
        source.setText(idiom.getIdiomSource());
        TextView meaning = findViewById(R.id.idiomMeaningDetail);
        meaning.setText(idiom.getIdiomMeaning());

    }
}
