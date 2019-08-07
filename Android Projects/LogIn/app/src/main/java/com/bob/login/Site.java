package com.bob.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Site extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
    }
    public void onBec(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        /*Intent i = getIntent();
        String name = i.getStringExtra("nss");
        String passw = i.getStringExtra("pss");
        intent.putExtra("nsd",i.getStringExtra("nss"));
        intent.putExtra("nsp",i.getStringExtra("pss"));
        */
        startActivity(intent);
    }
}
