package com.example.poems;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IdiomListActivity extends ListActivity {
    public static final String IDIOM_ID = "idiom_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        Idiom[] idioms = Idiom.IDIOMS;
        ArrayAdapter<Idiom> frameList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, idioms);
        listView.setAdapter(frameList);
    }

    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Intent intent = new Intent(IdiomListActivity.this, IdiomDetailActivity.class);
        intent.putExtra(IdiomListActivity.IDIOM_ID, (int)id);
        startActivity(intent);
    }

}
