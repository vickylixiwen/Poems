package com.example.poems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/*
this is for selecting the grade of the poems
 */
public class PoemGradeListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_grade_list);
    }

    // open the poem list in Grade One
    public void openGradeOne(View view) {
        Intent intent = new Intent(this, PoemGradeActivity.class);
        intent.putExtra(PoemGradeActivity.GRADE, 1);
        startActivity(intent);
    }

    public void openGradeThree(View view) {
        Intent intent = new Intent(this, PoemGradeActivity.class);
        intent.putExtra(PoemGradeActivity.GRADE, 3);
        startActivity(intent);
    }

    public void openGradeTwo(View view) {
        Intent intent = new Intent(this, PoemGradeActivity.class);
        intent.putExtra(PoemGradeActivity.GRADE, 2);
        startActivity(intent);
    }

    public void openGradeFour(View view) {
        Intent intent = new Intent(this, PoemGradeActivity.class);
        intent.putExtra(PoemGradeActivity.GRADE, 4);
        startActivity(intent);
    }

    public void openGradeFive(View view) {
        Intent intent = new Intent(this, PoemGradeActivity.class);
        intent.putExtra(PoemGradeActivity.GRADE, 5);
        startActivity(intent);
    }

//    public void contactUs(View view) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello!!!");
//        startActivity(intent);
//    }
}
