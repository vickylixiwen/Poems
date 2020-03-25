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

import static java.lang.System.out;

public class RecitingFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reciting, container, false);
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        ListView listView = getView().findViewById(R.id.reciting_list);
//
//
//        try {
//            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(getContext());
//            db = poemDatabaseHelper.getReadableDatabase();
//            cursor = db.query("POEM",
//                    new String[] {"_id", "TITLE", "POEM_ID"}, "grade = ?", new String[] {Integer.toString(grade)},
//                    null, null, "_id");
//            while (cursor.moveToNext()) {
//                poemId = cursor.getInt(0);
//                poemIdList.add(poemId);
//            }
//            CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.poem_item,
//                    cursor, new String[]{"TITLE"}, new int[]{R.id.poem_name}, 0);
////            gradeOneList.setAdapter(cursorAdapter);
//            listView.setAdapter(cursorAdapter);
//
//        } catch(SQLiteException e) {
//            System.out.print(e);
//            Toast toast  = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
//            toast.show();
//
//        }
//
//
//
//
//        ArrayAdapter<Idiom> adapter = new ArrayAdapter<Idiom>(this.getContext(), android.R.layout.simple_list_item_1, idioms);
////        ListView listView = getView().findViewById(R.id.idiom_list);
//        listView.setAdapter(adapter);
//
//        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
//                Intent intent = new Intent(getActivity(), IdiomDetailActivity.class);
//                intent.putExtra(IdiomListActivity.IDIOM_ID, (int)id);
//                startActivity(intent);
//            }
//        };
//
//        listView.setOnItemClickListener(itemClickListener);
//
//    }
}
