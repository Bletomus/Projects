package com.bob.mymessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Reciever extends AppCompatActivity
{
    public static final String EXTRA_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);
        Intent intent = getIntent();
        String msgI = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView box = (TextView)findViewById(R.id.message);
        box.setText(msgI);
    }
}
