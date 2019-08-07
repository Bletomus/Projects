package com.bob;

public abstract class ProductLock
{
    private ProductLock next;

    public ProductLock linkWith(ProductLock next)
    {
        this.next = next;
        return next;
    }
    public abstract boolean check(String name);


    protected boolean checkNext(String name)
    {
        if(next == null)
        {
            return true;
        }
        return next.check(name);
    }
}
