package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.Button;
import android.widget.ListView;

import com.example.poems.poem.GradeListActivity;
import com.example.poems.poem.PoemListActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Class relayClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
            int grade = 6;
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
                default:
                    grade = 6;
                    break;
            }
            Intent intent = new Intent(MainActivity.this, PoemListActivity.class);
            intent.putExtra(PoemListActivity.GRADE, grade);
            startActivity(intent);
            }
        };
        ListView listView = findViewById(R.id.main_list);
        listView.setOnItemClickListener(itemClickListener);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.home_tabs);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                                               @Override
//                                               public void onTabSelected(Tab tab) {
//                                                   switch(tab.getPosition()) {
//                                                       case 0:
//
//                                                   }
//                                               }

    }


//    // open the poem list in Grade One
//    public void openGradeOne(View view) {
//        Intent intent = new Intent(this, PoemListActivity.class);
//        intent.putExtra(PoemListActivity.GRADE, 1);
//        startActivity(intent);
//    }
//
//    public void openGradeThree(View view) {
//        Intent intent = new Intent(this, PoemListActivity.class);
//        intent.putExtra(PoemListActivity.GRADE, 3);
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
