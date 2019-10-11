package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FrameListActivity extends ListActivity {
    public static final String FRAME_ID = "frame_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        Frame[] frames = Frame.frames;
        ArrayAdapter<Frame> frameList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, frames);
        listView.setAdapter(frameList);
    }

    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Intent intent = new Intent(FrameListActivity.this, FrameDetailActivity.class);
        intent.putExtra(FrameListActivity.FRAME_ID, (int)id);
        startActivity(intent);
    }

}
