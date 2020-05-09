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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.poems.DatabaseHelper;
import com.example.poems.R;
import com.example.poems.poem.PoemListActivity;
import com.example.poems.poem.PoemListByAuthorActivity;

import java.util.ArrayList;

public class GradeListFragment extends Fragment {
    String[] gradeList = new String[]{"一年级", "二年级", "三年级", "四年级", "五年级", "六年级" , "七年级"};
    TextView textView;
//    ThingsAdapter adapter;
    FragmentActivity listener;
    ListView listView;
    View view;
    Adapter adapter;
    AdapterView.OnItemClickListener gradeClickListener;
    AdapterView.OnItemClickListener authorClickListener;
    private Cursor cursor;
    private SQLiteDatabase db;
    private ArrayList<String> authorList = new ArrayList<String>();
    private int poemId;

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
//        setContentView(R.layout.activity_main);
//
//

    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    // it is called when android needs the fragment layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gradelist, container, false);




        return view;
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
        RadioGroup radioGroup = (RadioGroup) view .findViewById(R.id.radio_group);
        listView = getView().findViewById(R.id.main_list);

        gradeClickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                System.out.print((int)id);
                int grade;
                switch ((int)id) {
                    case 0:
                        grade = 1;
                        break;
                    case 1:
                        grade = 2;
                        break;
                    case 2:
                        grade = 3;
                        break;
                    case 3:
                        grade = 4;
                        break;
                    case 4:
                        grade = 5;
                        break;
                    case 5:
//                    default:
                        grade = 6;
                        break;
                    default:
                        grade = 7;
                        break;
                }
                Intent intent = new Intent(getActivity(), PoemListActivity.class);
                intent.putExtra(PoemListActivity.GRADE, grade);
                startActivity(intent);
            }
        };
        authorClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                String author;
                String authorDynasty = authorList.get(position);
                if (authorDynasty.split(" ").length > 1)
                    author = authorDynasty.split(" ")[1];
                else
                    author = authorDynasty;
                Intent intent = new Intent(getActivity(), PoemListByAuthorActivity.class);
                intent.putExtra(PoemListByAuthorActivity.AUTHOR, author);
                startActivity(intent);
            }
        };
        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, gradeList);
        listView.setAdapter((ListAdapter) adapter);
        listView.setOnItemClickListener(gradeClickListener);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch (checkedId) {
                    case R.id.grade_button:
                        adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, gradeList);
                        listView.setAdapter((ListAdapter) adapter);
                        listView.setOnItemClickListener(gradeClickListener);
                        break;
                    case R.id.poet_button:
                        try {
                            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(getContext());
                            db = poemDatabaseHelper.getReadableDatabase();
                            cursor = db.query(true, "POEM",
                                    new String[]{"_id", "AUTHOR"}, null, null,  "AUTHOR",
                                    null, null, null);
                            while (cursor.moveToNext()) {
                                String author = cursor.getString(1);
                                authorList.add(author);
                            }
                            adapter = new android.widget.SimpleCursorAdapter(getContext(), R.layout.poem_item,
                                    cursor, new String[]{"AUTHOR"}, new int[]{R.id.poem_name}, 0);
                        } catch (SQLiteException e) {
                            System.out.print(e);
                            Toast toast = Toast.makeText(getContext(), "Database unavailable", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        listView.setAdapter((ListAdapter) adapter);
                        listView.setOnItemClickListener(authorClickListener);
                        break;
                }
            }
        });
//
    }

}
