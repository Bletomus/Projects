package com.bob.fruitstore;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FruitCategory extends ListActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Fruits[] fruit = Fruits.fruits;
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
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

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.customlayout, from, to);
        listView.setAdapter(simpleAdapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(FruitCategory.this,FruitOrderActivity.class);
        intent.putExtra(FruitOrderActivity.EXTRA_FRUITNO,(int)id);
        startActivity(intent);
    }

}


