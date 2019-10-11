package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/*
近义词、反义词
 */
public class SynonymActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_synonym);
        ListView listView = getListView();
        ArrayAdapter<Synonym> synonymAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Synonym.synonyms);
        listView.setAdapter(synonymAdapter);
    }
}
