package com.bob.homework3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class fruityList extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruity_list);
    }
    public void onToast(View view)
    {
        Toast toast;
        int duration = Toast.LENGTH_SHORT;
        switch(view.getId())
        {
            case R.id.apple:
                toast = Toast.makeText(this,"You chose an apple",duration);
                toast.show();
                break;
            case R.id.banana:
                toast = Toast.makeText(this,"You chose a banana",duration);
                toast.show();
                break;
            case R.id.cherry:
                toast = Toast.makeText(this,"You chose a cherry",duration);
                toast.show();
                break;
            case R.id.grape:
                toast = Toast.makeText(this,"You chose a grape",duration);
                toast.show();
                break;
        }
    }
    public void onFini(View view)
    {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
