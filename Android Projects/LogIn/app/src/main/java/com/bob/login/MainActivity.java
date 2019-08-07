package com.bob.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
    SharedPreferences sharedpreferences;
    private String nama;
    private String passa;
    public static final String MYPREFERENCES = "mypref";
    public static final String NAME = "nameKey";
    public static final String PASSWORD = "passKey";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h();
    }


    public void saves(String names,String passw)
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(NAME, names);
        editor.putString(PASSWORD, passw);
        editor.commit();
    }
    public void h()
    {
        EditText nameBox = findViewById(R.id.ns);
        EditText passwordBox = findViewById(R.id.ps);
        sharedpreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(NAME))
        {
            nameBox.setText(sharedpreferences.getString(NAME, ""));
        }
        if (sharedpreferences.contains(PASSWORD))
        {
            passwordBox.setText(sharedpreferences.getString(PASSWORD, ""));

        }

    }

    public void onLogos(View view)
    {

        EditText box =(EditText)findViewById(R.id.ns);
        String names = box.getText().toString();
        EditText boc =(EditText)findViewById(R.id.ps);
        String passw =boc.getText().toString();
        TextView t = (TextView)findViewById(R.id.textView3);

        Intent intent = new Intent(this,Site.class);
        Intent intent2 = new Intent(this,error.class);
        if(box.getText().toString().equals("lee") && boc.getText().toString().equals("love") )
        {

            saves(names,passw);
            startActivity(intent);

        }
       else if(box.getText().toString().equals("John") && boc.getText().toString().equals("Panda"))
        {
            saves(names,passw);
            startActivity(intent);
        }
        else
        {
            saves(names,passw);
            startActivity(intent2);
        }
    }
    public void onBackPressed(View view)
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
