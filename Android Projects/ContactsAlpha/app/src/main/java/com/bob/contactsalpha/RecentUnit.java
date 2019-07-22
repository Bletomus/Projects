package com.bob.contactsalpha;

public class RecentUnit
{
    private int _id;
    private String name = "Unknown";
    private String areaCode = "00";
    private String phoneNumber = "";
    private int con_id;
    
    public RecentUnit(int _id, String name, String areaCode, String phoneNumber, int con_id)
    {
        this._id = _id;
        this.name = name;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.con_id = con_id;
    }
    
    public int getCon_id()
    {
        return con_id;
    }
    
    public void setCon_id(int con_id)
    {
        this.con_id = con_id;
    }
    
    public int get_id()
    {
        return _id;
    }
    
    public void set_id(int _id)
    {
        this._id = _id;
    }
    
    public RecentUnit()
    {
    }
    
    public String getName()
    {
        return name;
    }
    
    
    public String getAreaCode()
    {
        return areaCode;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
}
