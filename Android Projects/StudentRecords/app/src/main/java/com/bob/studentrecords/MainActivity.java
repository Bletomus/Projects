package com.bob.studentrecords;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSaveIng(View view)
    {
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.editName)).getText().toString());
        values.put(StudentsProvider._ID, ((EditText)findViewById(R.id.editiD)).getText().toString());
        values.put(StudentsProvider.DEPARTMENT, ((EditText)findViewById(R.id.editDepartment)).getText().toString());
        values.put(StudentsProvider.AGE, ((EditText)findViewById(R.id.editAge)).getText().toString());
        values.put(StudentsProvider.PHONENUMBER, ((EditText)findViewById(R.id.editPhone)).getText().toString());
    
        Toast.makeText(this,"SAVED", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StudentView.class);
        startActivity(intent);
        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);
    }
}
