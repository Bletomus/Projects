package com.bob.studentrecords;

public class student
{
    private int id = 0;
    private String name = "";
    private String deparment = "";
    private int age = 0;
    private String phonenumber = "";
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDeparment()
    {
        return deparment;
    }
    
    public void setDeparment(String deparment)
    {
        this.deparment = deparment;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getPhonenumber()
    {
        return phonenumber;
    }
    
    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }
    
    public student()
    {
    }
    
    public student(int id, String name, String deparment, int age, String phonenumber)
    {
        this.id = id;
        this.name = name;
        this.deparment = deparment;
        this.age = age;
        this.phonenumber = phonenumber;
    }
}
