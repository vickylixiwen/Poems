package com.example.poems;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//public class PoemGradeActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_poem_grade);
//
//        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
//                Intent intent = new Intent(PoemGradeActivity.this, PoemDetailActivity.class);
//                startActivity(intent);
//
//            }
//        };
//        ListView listView = findViewById(R.id.grade_one_list);
//        listView.setOnItemClickListener(itemClickListener);
//    }
//}

/*
This is the list of poems of the specific grade
 */
public class PoemGradeActivity extends ListActivity {
    public static final String GRADE = "grade";
    private int grade;
    private Poem[] poems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView gradeOneList = getListView();
        grade = (Integer) getIntent().getExtras().get(GRADE);
        switch (grade) {
            case 1:
                poems = Poem.poems1;
                break;
            case 3:
                poems = Poem.poems3;
                break;
            default:
                poems = Poem.poems3;
                break;
        }
        ArrayAdapter<Poem> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, poems);
        gradeOneList.setAdapter(listAdapter);

    }

    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Intent intent = new Intent(PoemGradeActivity.this, PoemDetailActivity.class);
        intent.putExtra(PoemDetailActivity.POEM_ID, (int)id);
        intent.putExtra(PoemDetailActivity.GRADE, grade);
        startActivity(intent);
    }
}
