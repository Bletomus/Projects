package com.bob.homework3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MultipleChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
    }
    public void onNext(View view)
    {
        Intent intent = new Intent(this,fruityList.class);
        startActivity(intent);
    }
}
