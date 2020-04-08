package com.example.poems.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
//import android.widget.Button;

import com.example.poems.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;


public class MainActivity extends AppCompatActivity {
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        TabLayout tabs = findViewById(R.id.home_tabs);
//      Instantiate a ViewPager and a PagerAdapter.
        viewPager = findViewById(R.id.view_pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        System.out.println("++++++++++++++");

        tabs.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            // called when tab unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            // called when a tab is reselected
            }
        });

    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment tab;
            switch (position) {
                case 1:
                    tab = new RecitedFragment();
                    return tab;
                case 2:
                    tab = new RecitingFragment();
                    return tab;
                case 3:
                    tab = new IdiomFragment();
                    return tab;
                case 4:
                    tab = new SearchFragment();
                    return tab;
                case 0:
                default:
                    return new PoemFragment();
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }


}



