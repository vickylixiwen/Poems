package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.Button;
import android.widget.ListView;

import com.example.poems.poem.PoemGradeListActivity;

public class MainActivity extends AppCompatActivity {
    private Class relayClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                switch ((int)id) {

                    case 0:
                        relayClass = PoemGradeListActivity.class;
                        break;
                    case 1:
                        relayClass = VocabularyActivity.class;
                        break;
                    case 2:
                        relayClass = VocabularyActivity.class;
                        break;
                    case 3:
                        relayClass = VocabularyActivity.class;
                        break;
                    case 4:
                        relayClass = IdiomListActivity.class;
                        break;
                    case 5:
                        relayClass = WrongListActivity.class;
                        break;
                    case 6:
                        relayClass = SynonymActivity.class;
                        break;
                    default:
                        relayClass = PoemGradeListActivity.class;
                        break;
                }
                Intent intent = new Intent(MainActivity.this, relayClass);
                startActivity(intent);

            }
        };
        ListView listView = findViewById(R.id.main_list);
        listView.setOnItemClickListener(itemClickListener);
    }


//    // open the poem list in Grade One
//    public void openGradeOne(View view) {
//        Intent intent = new Intent(this, PoemGradeActivity.class);
//        intent.putExtra(PoemGradeActivity.GRADE, 1);
//        startActivity(intent);
//    }
//
//    public void openGradeThree(View view) {
//        Intent intent = new Intent(this, PoemGradeActivity.class);
//        intent.putExtra(PoemGradeActivity.GRADE, 3);
//        startActivity(intent);
//    }

//    public void contactUs(View view) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello!!!");
//        startActivity(intent);
//    }
}
