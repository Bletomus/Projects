package com.bob.studentrecords;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bob.studentrecords.StudentsProvider.STUDENTS_TABLE_NAME;

public class StudentView extends ListActivity
{
    private List<student> names = new ArrayList<>();
    public void onClickRetriveStudents(View view)
    {
    
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        String URL = StudentsProvider.URL;
        Uri students = Uri.parse(URL);
        ListView listView = getListView();
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        Cursor c = getBaseContext().getContentResolver().query(students, null, null, null,StudentsProvider.NAME);
        
        if(c.moveToFirst())
        {
            do{
                student temp = new student();
                temp.setId(c.getInt(c.getColumnIndex(StudentsProvider._ID)));
                temp.setName(c.getString(c.getColumnIndex(StudentsProvider.NAME)));
                temp.setDeparment(c.getString(c.getColumnIndex(StudentsProvider.DEPARTMENT)));
                temp.setAge(c.getInt(c.getColumnIndex(StudentsProvider.AGE)));
                temp.setPhonenumber(c.getString(c.getColumnIndex(StudentsProvider.PHONENUMBER)));
                names.add(temp);
            }while ((c.moveToNext()));
        }
        
        for (int i = 0; i < names.size(); i++)
        {
            HashMap<String, String> hm = new HashMap<String, String>();
            student temp = names.get(i);
            hm.put("listview_title", temp.getName());
            hm.put("listview_ID", String.valueOf(temp.getId()));
            hm.put("list_dept",temp.getDeparment());
            aList.add(hm);
        }
        String[] from = {"list_dept", "listview_title", "listview_ID"};
        int[] to = {R.id.list_dept, R.id.listview_item_title, R.id.listview_ID};
    
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.custom_layout, from, to);
        listView.setAdapter(simpleAdapter);
    }
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        int i = (int)id;
        student s = names.get(i);
        Intent intent = new Intent(StudentView.this,EditStudent.class);
        intent.putExtra(EditStudent.EXTRA_ID,s.getId());
        startActivity(intent);
    }
    
}
