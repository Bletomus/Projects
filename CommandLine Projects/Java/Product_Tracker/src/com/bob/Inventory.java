package com.bob;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class Inventory
{
    //private static Inventory instance;
    private HashMap<String, Product> products = new HashMap<>();
    private ProductLock productLock;


    public void setProductLock(ProductLock productLock)
    {
        this.productLock = productLock;
    }

    public boolean hasProduct(String name)
    {
        return products.containsKey(name);
    }

    public String displayInfo(String name)
    {
        if(productLock.check(name))
        {
            Product temp = products.get(name);
            return name + "\n" + temp.getId() + "\n" + temp.getPrice() + "\n" + temp.getQuantity();
        }
        else
        {
            return "Product not found";
        }
    }

    public int getTotalAmount()
    {
        return getTotalAmount(products);
    }

    private int getTotalAmount(Map mp)
    {
        int total = 0;
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry item = (Map.Entry)it.next();
            Product product = (Product) item.getValue();
            total += product.getQuantity();
            it.remove();
        }
        return total;
    }

}
