package com.bob;

public class ProductExistenceHandler extends ProductLock
{
    private Inventory inventory;

    public ProductExistenceHandler(Inventory inventory)
    {
        this.inventory = inventory;
    }

    @Override
    public boolean check(String name)
    {
        if(!inventory.hasProduct(name))
        {
            System.out.println("The product is not registered!");
            return false;
        }
        return checkNext(name);
    }

}
