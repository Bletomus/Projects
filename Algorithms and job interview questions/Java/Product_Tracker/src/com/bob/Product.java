package com.bob;

public class Product
{
    private double price;
    private int id;
    private int quantity;

    public Product(double price, int id, int quantity)
    {
        this.price = price;
        this.id = id;
        this.quantity = quantity;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
