package com.bob.fruitstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.bob.fruitstore.Fruits.fruits;

public class FruitOrderActivity extends Activity
{
    private int totalprice = 1;
    private Fruits fruit;
    public static final String EXTRA_FRUITNO = "fruitno";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_order);
        int fruitid = (Integer)getIntent().getExtras().get(EXTRA_FRUITNO);
        fruit = fruits[fruitid];
        ImageView photo = (ImageView)findViewById(R.id.fruitImage);
        photo.setImageResource(fruit.getImageResourceId());
        TextView name = (TextView)findViewById(R.id.fruitname);
        name.setText(fruit.getName());
        TextView desc = (TextView)findViewById(R.id.unitPrice);
        desc.setText("Amount: " + fruit.getPrice());
        setTotalPrice(fruit);
    }
    private void setTotalPrice(Fruits fruits)
    {
        TextView tot = (TextView)findViewById(R.id.total);
        tot.setText("" + totalprice);
        TextView price = (TextView)findViewById(R.id.price);
        price.setText("Total price: $" + (fruits.getsPrice() * totalprice) + ".00");
    }
    public void onSubb(View view)
    {
        if(totalprice > 1)
        {
            totalprice--;
        }
        setTotalPrice(fruit);
    }
    public void onAddd(View view)
    {
        totalprice++;
        setTotalPrice(fruit);
    }

    public void onBec(View view)
    {
        Intent intent = new Intent(this,FruitCategory.class);
        startActivity(intent);
    }

}
