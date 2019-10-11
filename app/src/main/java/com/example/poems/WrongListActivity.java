package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WrongListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        ArrayAdapter<WrongList> wrongListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, WrongList.wrongLists);
        listView.setAdapter(wrongListAdapter);

    }
}
