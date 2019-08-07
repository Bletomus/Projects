package com.bob.mymessenger;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity
{
    public static final String EXTRA_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSend(View view)
    {
        EditText box = (EditText)findViewById(R.id.msgU);
        String messageToRecieve = box.getText().toString();
        Intent intent = new Intent(this,Receive_Message.class);
        intent.putExtra(Receive_Message.EXTRA_MESSAGE,messageToRecieve);
        startActivity(intent);

    }
    public void onSendImplicit(View view)
    {
        EditText box = (EditText)findViewById(R.id.msgU);
        String msg = box.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        String choose = getString(R.string.chooser);
        Intent intentf = Intent.createChooser(intent,choose);
        startActivity(intentf);
    }
}
