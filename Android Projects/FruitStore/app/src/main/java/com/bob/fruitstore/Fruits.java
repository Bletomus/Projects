package com.bob.fruitstore;

public class Fruits
{
    private int imageResourceId;
    private String name;
    private String price;
    private int sPrice;
    public static final Fruits[] fruits =
            {
            new Fruits(R.drawable.apple, "Apple", "$2.00/kg", 2),
            new Fruits(R.drawable.banana, "Banana", "$3.00/kg", 3),
            new Fruits(R.drawable.orange, "Orange", "$2.00/kg", 2),
            new Fruits(R.drawable.watermelon, "Watermelon", "$3.00/kg", 3),
            new Fruits(R.drawable.pear, "Pear", "$5.00/kg", 5),
            new Fruits(R.drawable.grape, "Grape", "$4.00/kg", 4),
            new Fruits(R.drawable.pineapple, "Pineapple", "$5.00/kg", 5),
            new Fruits(R.drawable.strawberry, "Strawberry", "$6.00/kg", 6)
    };
    public Fruits(int imageResourceId, String name, String price, int sPrice)
    {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
        this.sPrice = sPrice;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public int getsPrice()
    {
        return sPrice;
    }

    public String getName()
    {
        return name;
    }

    public String getPrice()
    {
        return price;
    }
    public String toString()
    {
        return this.name;
    }
}

