package com.bob.studentrecords;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditStudent extends AppCompatActivity
{
    public static final String EXTRA_ID = "bob";
    private int iCounter = -1;
    private student stu = new student();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        Intent intent = getIntent();
        if(intent != null)
        {
            iCounter = intent.getIntExtra(EXTRA_ID,0);
        }
        h();
        set();
    }
    private void set()
    {
        EditText id = findViewById(R.id.ID);
        EditText anme = findViewById(R.id.NAME);
        EditText dept = findViewById(R.id.DEPARTMENT);
        EditText age = findViewById(R.id.AGE);
        EditText phone = findViewById(R.id.PHONE);
        
        id.setText(Integer.toString(stu.getId()));
        anme.setText(stu.getName());
        dept.setText(stu.getDeparment());
        age.setText(Integer.toString(stu.getAge()));
        phone.setText(stu.getPhonenumber());
    }
    
    private void h()
    {
        String URL = StudentsProvider.URL;
        Uri students = Uri.parse(URL);
        Cursor c = getBaseContext().getContentResolver().query(students, null, "_ID = " + Integer.toString(iCounter) , null,StudentsProvider.NAME);
    
        c.moveToFirst();
        
        stu.setId(c.getInt(c.getColumnIndex(StudentsProvider._ID)));
        stu.setName(c.getString(c.getColumnIndex(StudentsProvider.NAME)));
        stu.setDeparment(c.getString(c.getColumnIndex(StudentsProvider.DEPARTMENT)));
        stu.setAge(c.getInt(c.getColumnIndex(StudentsProvider.AGE)));
        stu.setPhonenumber(c.getString(c.getColumnIndex(StudentsProvider.PHONENUMBER)));
        
    }
    
    public void onUpdator(View view)
    {
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.NAME)).getText().toString());
        values.put(StudentsProvider.DEPARTMENT, ((EditText)findViewById(R.id.DEPARTMENT)).getText().toString());
        values.put(StudentsProvider.AGE, ((EditText)findViewById(R.id.AGE)).getText().toString());
        values.put(StudentsProvider.PHONENUMBER, ((EditText)findViewById(R.id.PHONE)).getText().toString());
    
        getContentResolver().update(StudentsProvider.CONTENT_URI, values, StudentsProvider._ID+"="+iCounter, null);
    }
    
    public void onDeleter(View view)
    {
        getContentResolver().delete(StudentsProvider.CONTENT_URI, StudentsProvider._ID+"="+iCounter, null);
    }
    
    
}
