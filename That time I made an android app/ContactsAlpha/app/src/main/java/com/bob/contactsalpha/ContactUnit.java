package com.bob.contactsalpha;



public class ContactUnit extends RecentUnit
{
    private int _id = 0;
    private String name = "Unknown";
    private String email = "";
    private String areaCode = "+0";
    private String phoneNumber = "";
    private String address = "";
    private String birthday = "2019-01-01";
    private String face = "";
    private Boolean fave = false;
    
    public Boolean getFave()
    {
        return fave;
    }
    
    public void setFave(Boolean fave)
    {
        this.fave = fave;
    }
    
    public ContactUnit(int _id, String name, String email, String areaCode, String phoneNumber, String address, String birthday, String face, Boolean fave)
    {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.face = face;
        this.fave = fave;
    }
    
    public ContactUnit(int _id, String name, String email, String areaCode, String phoneNumber, String address, String birthday, String face)
    {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.face = face;
    }
    
    public String getFace()
    {
        return face;
    }
    
    public void setFace(String face)
    {
        this.face = face;
    }
    
   
    
    /*public byte[] getFace()
    {
        return face;
    }*/
    
    /*public void setFace(byte[] face)
    {
        this.face = face;
    }*/
    
    public int get_id()
    {
        return _id;
    }
    
    public void set_id(int _id)
    {
        this._id = _id;
    }
    
    public ContactUnit(int _id, String name, String email, String areaCode, String phoneNumber, String address, String birthday)
    {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
    }
    /*public JSONObject getJSONObject()
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("_id", this._id);
            obj.put("name", this.name);
            obj.put("email", this.email);
            obj.put("areaCode", this.areaCode);
            obj.put("phoneNumber", this.phoneNumber);
            obj.put("address", this.address);
            obj.put("birthday", this.birthday);
            //obj.put("face", this.face);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return obj;
    }*/
    public ContactUnit()
    {
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getAreaCode()
    {
        return areaCode;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public String getBirthday()
    {
        return birthday;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }
    
    @Override
    public String toString()
    {
        return "ContactUnit{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
    
    public ContactUnit(String name, String email, String areaCode, String phoneNumber, String address, String birthday)
    {
        this.name = name;
        this.email = email;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
    }
    
    public String getDate()
    {
        return birthday;
    }
    
}
