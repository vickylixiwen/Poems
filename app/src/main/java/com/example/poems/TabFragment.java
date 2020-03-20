package com.example.poems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TabFragment extends Fragment {
    private static final int NUM_PAGES = 3;
    private Class relayClass;
    private ViewPager vPager;
    private PagerAdapter pagerAdapter;
    private FragmentManager getSupportFragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_tab, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        vPager = vPager.findViewById(R.id.pager_tabs);

//        TabLayout tabs = findViewById(R.id.home_tabs);
//
//        pagerAdapter = new TabFragmentAdapter(getSupportFragmentManager);
//        vPager.setAdapter(pagerAdapter);
//        home_tabs.setupWithViewPager(vPager);
//
//        val length = homeTabs.tabCount
//        for (i in 0 until length) {
//            homeTabs.getTabAt(i)?.customView = pagerAdapter.getTabView(i)
//        }
//        updateSelectedTabStatus(0)
//        homeTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val tabPosition = tab?.position ?: -1
//                when (tabPosition) {
//                    HomeTabs.TODAY.ordinal -> Blaster.log(LoggingDataV2.BTN_CLK_HOME_TAB_TODAY)
//                    HomeTabs.ANALYSIS.ordinal -> {
//                        analysisTabModel.clearUnread()
//                        Blaster.log(LoggingDataV2.BTN_CLK_HOME_TAB_ANALYSIS)
//                    }
//                    HomeTabs.ARTICLES.ordinal -> Blaster.log(LoggingDataV2.BTN_CLK_HOME_TAB_ARTICLES)
//                }
//
//                // update tab selected status
//                updateSelectedTabStatus(tabPosition)
//            }
//
//        })
    }



}
