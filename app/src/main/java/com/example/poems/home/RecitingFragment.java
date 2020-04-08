package com.example.poems.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.poems.DatabaseHelper;
import com.example.poems.Idiom;
import com.example.poems.IdiomDetailActivity;
import com.example.poems.IdiomListActivity;
import com.example.poems.R;
import com.example.poems.poem.PoemDetailActivity;
import com.example.poems.poem.PoemListActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RecitingFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;
    private int poemId;
    private ArrayList<Integer> recitingIdList = new ArrayList<Integer>();
    private int pId;
    private boolean poemIsRecited;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reciting, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // list the reciting poems, poems with is_reciting == true
        ListView listView = getView().findViewById(R.id.reciting_list);
        try {
            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(getContext());
            db = poemDatabaseHelper.getReadableDatabase();
            cursor = db.query("POEM",
                    new String[] {"_id", "TITLE", "POEM_ID", "IS_PASS"}, "IS_RECITING = ?", new String[] {Integer.toString(1)},
                    null, null, "_id", "1,10");
            while (cursor.moveToNext()) {
                poemId = cursor.getInt(0);
                recitingIdList.add(poemId);
            }
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.reciting_item,
                    cursor, new String[]{"TITLE"}, new int[]{R.id.poem_name}, 0);
//            gradeOneList.setAdapter(cursorAdapter);
            listView.setAdapter(cursorAdapter);

        } catch(SQLiteException e) {
            System.out.print(e);
            Toast toast  = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }




//        ArrayAdapter<Idiom> adapter = new ArrayAdapter<Idiom>(this.getContext(), android.R.layout.simple_list_item_1, idioms);
////        ListView listView = getView().findViewById(R.id.idiom_list);
//        listView.setAdapter(adapter);
//
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                Intent intent = new Intent(getActivity(), PoemDetailActivity.class);
                if (cursor.moveToPosition(position)) {
                    poemId = cursor.getInt(0);
                    poemIsRecited = (cursor.getInt(3) == 1);// 0 - false; 1 - true
                    pId = cursor.getInt(2);
                }
                String pageSource = "recitingList";
                intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
                intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
                intent.putExtra(PoemDetailActivity.P_ID, pId);
                intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, recitingIdList);
                intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
                intent.putExtra(PoemDetailActivity.PAGE_SOURCE, pageSource);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);

    }
}
