package com.example.poems.OUTOFDATE;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VocabularyActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vocabulary);
        ListView listView = getListView();
        ArrayAdapter<Vocabulary> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Vocabulary.vocabularies);
        listView.setAdapter(arrayAdapter);
    }
}
