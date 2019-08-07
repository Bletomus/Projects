package com.bob.fruitfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FruitDetailFragment extends Fragment implements View.OnClickListener
{
    private int totalprice = 1;
    private Fruits fruit;
    private long fruitId;
    public FruitDetailFragment()
    {
        // Required empty public constructor
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_fruit_detail, container, false);
    
        Button plus = (Button) v.findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onAddd();
            }
        });
        Button neg = (Button) v.findViewById(R.id.negative);
        neg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onSubb();
            }
        });
        Button order = (Button) v.findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                totalprice = 1;
                setTotalPrice(fruit);
            }
        });
        return v;
        
        
    }
   
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            fruitId = savedInstanceState.getLong("fruitId");
            totalprice = savedInstanceState.getInt("tots");
        }
        
        
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putLong("fruitId", fruitId);
        savedInstanceState.putInt("tots", totalprice);
    }
    
    @Override
    public void onStart()
    {
        super.onStart();
        View view = getView();
        if (view != null)
        {
            
            fruit = Fruits.fruits[(int) fruitId];
            ImageView photo = view.findViewById(R.id.fruitImage);
            photo.setImageResource(fruit.getImageResourceId());
            TextView name = view.findViewById(R.id.fruitname);
            name.setText(fruit.getName());
            TextView desc =view.findViewById(R.id.unitPrice);
            desc.setText("Amount: " + fruit.getPrice());
            setTotalPrice(fruit);
        }
    }
    
    private void setTotalPrice(Fruits fruits)
    {
        View view = getView();
        TextView tot = view.findViewById(R.id.total);
        tot.setText("" + totalprice);
        TextView price = view.findViewById(R.id.price);
        price.setText("Total price: $" + (fruits.getsPrice() * totalprice) + ".00");
    }
    private void onSubb()
    {
        if(totalprice > 1)
        {
            totalprice--;
        }
        setTotalPrice(fruit);
    }
    private void onAddd()
    {
        totalprice++;
        setTotalPrice(fruit);
    }
    
    public void setFruit(long id) {
        this.fruitId = id;
    }
    
    @Override
    public void onClick(View v)
    {
    
    }
}
