package com.example.poems.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.poems.Idiom;
import com.example.poems.IdiomDetailActivity;
import com.example.poems.IdiomListActivity;
import com.example.poems.R;
import com.example.poems.poem.PoemDetailActivity;
import com.example.poems.poem.PoemListActivity;

import static java.lang.System.out;

public class IdiomFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_idiom, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = getView().findViewById(R.id.idiom_list);


        Idiom[] idioms = Idiom.IDIOMS;

        ArrayAdapter<Idiom> adapter = new ArrayAdapter<Idiom>(this.getContext(), android.R.layout.simple_list_item_1, idioms);
//        ListView listView = getView().findViewById(R.id.idiom_list);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                Intent intent = new Intent(getActivity(), IdiomDetailActivity.class);
                intent.putExtra(IdiomListActivity.IDIOM_ID, (int)id);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);

    }

}
