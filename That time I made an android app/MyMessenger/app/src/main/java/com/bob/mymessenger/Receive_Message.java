package com.bob.mymessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Receive_Message extends Activity
{
    public static final String EXTRA_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive__message);
        Intent intent = getIntent();
        String msgI = intent.getStringExtra(EXTRA_MESSAGE);
        TextView box = (TextView)findViewById(R.id.message);
        box.setText(msgI);
    }
}
