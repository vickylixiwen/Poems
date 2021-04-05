package com.example.poems.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.poems.DatabaseHelper;
import com.example.poems.R;
import com.example.poems.poem.PoemDetailActivity;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;
    private int poemId;
    private ArrayList<Integer> searchResultIdList = new ArrayList<Integer>();
    private int pId;
    private boolean poemIsRecited;
    EditText searchText;
    private ArrayAdapter<String> adapter;
    String searchTextValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchText = getView().findViewById(R.id.search_text_field);
        Button searchButton = getView().findViewById(R.id.search_button);
        Button feihualingButton = getView().findViewById(R.id.fhl_search_button);
        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                searchTextValue = searchText.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                Toast.makeText(getContext(),"before text change",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                Toast.makeText(getContext(),"after text change",Toast.LENGTH_LONG).show();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!searchTextValue.isEmpty() || searchTextValue != "") {
                        //close the soft keyboard
                        searchText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        Toast.makeText(getActivity(), "you have searched " + searchText.getText().toString(), Toast.LENGTH_LONG).show();
                        startSearch(searchTextValue);
                    }
                } catch(Exception e){
                    searchText.setError("请输入古诗/词名");
                }
            }
        });

        feihualingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!searchTextValue.isEmpty() || searchTextValue != "") {
                        //close the soft keyboard
                        searchText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        Toast.makeText(getActivity(), "you have searched " + searchText.getText().toString(), Toast.LENGTH_LONG).show();
                        startFHLSearch(searchTextValue);
                    }
                } catch (Exception e) {
                    searchText.setError("请输入飞花令");
                    throw e;
                }
            }
        });
    }

    public void startSearch(String title) {
        ListView listView = getView().findViewById(R.id.result_list);
        try {
            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(getContext());
            db = poemDatabaseHelper.getReadableDatabase();
            System.out.println(title);
            cursor = db.query("POEM",
                    new String[] {"_id", "TITLE", "POEM_ID", "IS_PASS"}, "TITLE LIKE ?", new String[] {"%" + title + "%"},
                    null, null, "_id");
            System.out.println(cursor);
            while (cursor.moveToNext()) {
                poemId = cursor.getInt(0);
                System.out.println(poemId);
                searchResultIdList.add(poemId);
            }
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.reciting_item,
                    cursor, new String[]{"TITLE"}, new int[]{R.id.poem_name}, 0);
            listView.setAdapter(cursorAdapter);

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
//                    poemIsRecited = (cursor.getInt(3) == 1);// 0 - false; 1 - true
                    pId = cursor.getInt(2);
                }
                String pageSource = "searchResultList";
                intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
                intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
                intent.putExtra(PoemDetailActivity.P_ID, pId);
                intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, searchResultIdList);
                intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
                intent.putExtra(PoemDetailActivity.PAGE_SOURCE, pageSource);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }

    public void startFHLSearch(String word) {
        ListView listView = getView().findViewById(R.id.result_list);
        try {
            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(getContext());
            db = poemDatabaseHelper.getReadableDatabase();
            System.out.println(word);
            String titleContent = "";
            cursor = db.query("POEM",
                    new String[] {"_id", "TITLE", "CONTENT", "POEM_ID", "IS_PASS"}, "CONTENT LIKE ?", new String[] {"%" + word + "%"},
                    null, null, "_id");
            System.out.println(cursor);
            while (cursor.moveToNext()) {
                poemId = cursor.getInt(0);
                System.out.println(poemId);
                searchResultIdList.add(poemId);
                titleContent = cursor.getString(1) + "\n" + cursor.getString(2);
                System.out.println(titleContent);
            }
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.feihualing_item,
                    cursor, new String[]{"TITLE", "CONTENT"}, new int[]{R.id.poem_name, R.id.poem_content}, 0);
            listView.setAdapter(cursorAdapter);

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
//                    poemIsRecited = (cursor.getInt(3) == 1);// 0 - false; 1 - true
                    pId = cursor.getInt(2);
                }
                String pageSource = "searchResultList";
                intent.putExtra(PoemDetailActivity.POEM_ID, poemId);
                intent.putExtra(PoemDetailActivity.POEM_INDEX, position);
                intent.putExtra(PoemDetailActivity.P_ID, pId);
                intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, searchResultIdList);
                intent.putExtra(PoemDetailActivity.POEM_IS_RECITED, poemIsRecited);
                intent.putExtra(PoemDetailActivity.PAGE_SOURCE, pageSource);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }

}
