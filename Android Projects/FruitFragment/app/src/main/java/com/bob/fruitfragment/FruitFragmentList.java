package com.bob.fruitfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FruitFragmentList extends ListFragment
{
    
    static interface Listener {
        void itemClicked(long id);
    };
    public FruitFragmentList()
    {
        // Required empty public constructor
    }
    private Listener listener;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Fruits[] fruit = Fruits.fruits;
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 8; i++)
        {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", fruit[i].getName());
            hm.put("listview_discription", fruit[i].getPrice());
            hm.put("listview_image", Integer.toString(fruit[i].getImageResourceId()));
            aList.add(hm);
        }
    
        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
        SimpleAdapter simpleAdapter = new SimpleAdapter(inflater.getContext(), aList, R.layout.customlayout, from, to);
        setListAdapter(simpleAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.listener = (Listener)context;
    }
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }
}
