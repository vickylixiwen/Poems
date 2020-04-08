package com.example.poems.home;

import android.app.Activity;
import android.content.Context;
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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.poems.DatabaseHelper;
import com.example.poems.R;
import com.example.poems.poem.PoemDetailActivity;

import java.util.ArrayList;

public class RecitedFragment extends Fragment {
    TextView textView;
//    ThingsAdapter adapter;
    FragmentActivity listener;
    private Cursor cursor;
    private SQLiteDatabase db;
    private int poemId;
    private ArrayList<Integer> recitedIdList = new ArrayList<Integer>();
    private int pId;
    private boolean poemIsRecited;

    public RecitedFragment() {

    }

    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ArrayList<Thing> things = new ArrayList<Thing>();
//        adapter = new ThingsAdapter(getActivity(), things);
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recited, container, false);
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = getView().findViewById(R.id.recited_list);
        try {
            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(getContext());
            db = poemDatabaseHelper.getReadableDatabase();
            cursor = db.query("POEM",
                    new String[] {"_id", "TITLE", "POEM_ID", "IS_PASS"}, "IS_PASS = ?", new String[] {Integer.toString(1)},
                    null, null, "_id desc");
            while (cursor.moveToNext()) {
                poemId = cursor.getInt(0);
                recitedIdList.add(poemId);
            }
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.recited_item,
                    cursor, new String[]{"TITLE"}, new int[]{R.id.poem_name}, 0);
            listView.setAdapter(cursorAdapter);
            int totalRecitedPoem = cursor.getCount();
            TextView totalView = getView().findViewById(R.id.recited_count);
            totalView.setText("一共背出来" + totalRecitedPoem + "首古诗。");

        } catch(SQLiteException e) {
            System.out.print(e);
            Toast toast  = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                Intent intent = new Intent(getActivity(), PoemDetailActivity.class);
                if (cursor.moveToPosition(position)) {
                    poemId = cursor.getInt(0);
                    poemIsRecited = (cursor.getInt(3) == 1);// 0 - false; 1 - true
                    pId = cursor.getInt(2);

                }
                String pageSource = "recitedList";
                intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
                intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
                intent.putExtra(PoemDetailActivity.P_ID, pId);
                intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, recitedIdList);
                intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
                intent.putExtra(PoemDetailActivity.PAGE_SOURCE, pageSource);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }
}
