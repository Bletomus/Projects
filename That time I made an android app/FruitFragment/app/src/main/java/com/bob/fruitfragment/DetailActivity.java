package com.bob.fruitfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity
{
    public static final String EXTRA_FRUIT = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        FruitDetailFragment frag = (FruitDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int workoutId = (int) getIntent().getExtras().get(EXTRA_FRUIT);
        frag.setFruit(workoutId);
    }
}
