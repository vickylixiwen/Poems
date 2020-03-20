package com.example.poems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.poems.poem.GradeListActivity;
import com.example.poems.poem.PoemListActivity;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 3;
    private Class relayClass;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        if (savedInstanceState == null) {
//            PoemFragment poemFragment = new PoemFragment();
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            ft.add(R.id.fragment_poem, poemFragment);
////                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.commit();
//        }

//         Instantiate a ViewPager and a PagerAdapter.
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = (TabLayout) findViewById(R.id.home_tabs);
//        tabs.setupWithViewPager(vPager); //set the tablayout with the viewpager

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


//
//
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//            // called when tab selected
//                Fragment fragment = null;
//                switch (tab.getPosition()) {
//                    case 0:
//                        fragment = new PoemFragment();
//                        break;
//                    case 1:
//                        fragment = new RecitedFragment();
//                        break;
//
//                }
//
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.tab_frame, fragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.commit();
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            // called when tab unselected
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            // called when a tab is reselected
//            }
//        });



//        TabLayout tabLayout = (TabLayout) findViewById(R.id.home_tabs);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                                               @Override
//                                               public void onTabSelected(Tab tab) {
//                                                   switch(tab.getPosition()) {
//                                                       case 0:
//
//                                                   }
//                                               }
        viewPager.setCurrentItem(0);

    }


    public void onTabSelected(TabLayout.Tab tab, FragmentTransaction ft) {

        Fragment fragment = null;
        switch (tab.getPosition()) {
                    case 0:
                        fragment = new PoemFragment();
                        break;
                    case 1:
                        fragment = new RecitedFragment();
                        break;

                }
        ft.replace(R.id.view_pager, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
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


    private class PagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment tab = null;
            switch (position) {
                case 0:
                    return new PoemFragment();
                case 1:
                    tab = new RecitedFragment();
                    return tab;
                case 2:
                    tab = new TabFragment();
                    return tab;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}



