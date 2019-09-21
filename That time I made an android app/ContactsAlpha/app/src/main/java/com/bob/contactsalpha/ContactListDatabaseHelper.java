package com.bob.contactsalpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class ContactListDatabaseHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "PHONEBOOK";
    public static final int DB_VERSION = 2;
    public static final String DB_CONTACTS_TABLE = "CONTACTS";
    public static final String DB_Recent_TABLE__ID = "r_id";
    public static final String DB_RECENT_TABLE = "RECENT";
    public static final String CONTACTS_TABLE__ID = "_id";
    public static final String TABLE_FACE = "FACE";
    public static final String TABLE_NAME = "NAME";
    public static final String TABLE_EMAIL = "EMAIL";
    public static final String TABLE_AREA_CODE = "AREA_CODE";
    public static final String TABLE_PHONE_NUMBER = "PHONE_NUMBER";
    public static final String TABLE_BIRTHDAY = "BIRTHDAY";
    public static final String TABLE_ADDRESS = "ADDRESS";
    public static final String TABLE_FAV = "FAVORITE";
    public ContactListDatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        
       db.execSQL("CREATE TABLE " + DB_RECENT_TABLE +"("
                + DB_Recent_TABLE__ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CONTACTS_TABLE__ID + " INTEGER,"
                + TABLE_NAME + " TEXT,"
                + TABLE_AREA_CODE + " TEXT,"
                + TABLE_PHONE_NUMBER + " TEXT"
                + ");"
        );
        db.execSQL("CREATE TABLE " + DB_CONTACTS_TABLE +"("
                + CONTACTS_TABLE__ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TABLE_NAME + " TEXT,"
                + TABLE_AREA_CODE + " TEXT,"
                + TABLE_PHONE_NUMBER + " TEXT,"
                + TABLE_ADDRESS + " TEXT,"
                + TABLE_BIRTHDAY + " DATE,"
                + TABLE_EMAIL + " TEXT,"
                + TABLE_FACE + " TEXT,"
                + TABLE_FAV + " BOOLEAN"
                + ");"
        );
    }
    
    public void insertContact(ContactUnit item)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        
        ContentValues contact = new ContentValues();
        contact.put(TABLE_NAME, item.getName());
        contact.put(TABLE_EMAIL,item.getEmail());
        contact.put(TABLE_AREA_CODE,item.getAreaCode());
        contact.put(TABLE_PHONE_NUMBER,item.getPhoneNumber());
        contact.put(TABLE_ADDRESS,item.getAddress());
        contact.put(TABLE_BIRTHDAY,item.getDate());
        contact.put(TABLE_FACE,item.getFace());
        contact.put(TABLE_FAV,(item.getFave()));//? 1 : 0);
        db.insert(DB_CONTACTS_TABLE,null,contact);
        db.close();
    }
    public List<ContactUnit> readBulkNamesFromContacts(String query)
    {
        List<ContactUnit> names = new ArrayList<ContactUnit>();
        SQLiteDatabase db = this.getReadableDatabase();
        //ContactUnit temp = new ContactUnit();
        ContactUnit temp2 = new ContactUnit();
        try
        {
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst())
            {
                do
                {
                    ContactUnit temp = new ContactUnit();
                    temp.set_id(cursor.getInt(cursor.getColumnIndex(CONTACTS_TABLE__ID)));
                    temp.setName(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));
                    temp.setAreaCode(cursor.getString(cursor.getColumnIndex(TABLE_AREA_CODE)));
                    temp.setPhoneNumber(cursor.getString(cursor.getColumnIndex(TABLE_PHONE_NUMBER)));
                    temp.setAddress(cursor.getString(cursor.getColumnIndex(TABLE_ADDRESS)));
                    temp.setBirthday(cursor.getString(cursor.getColumnIndex(TABLE_BIRTHDAY)));
                    temp.setEmail(cursor.getString(cursor.getColumnIndex(TABLE_EMAIL)));
                    temp.setFace(cursor.getString(cursor.getColumnIndex(TABLE_FACE)));
                    temp.setFave(1 == cursor.getInt(cursor.getColumnIndex(TABLE_FAV)));
                    names.add(temp);
                }while (cursor.moveToNext());
            }
            
        }catch(SQLiteException e)
        {
            names.add(temp2);
        }
        return names;
    }
    
    public ContactUnit readContact(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContactUnit person = new ContactUnit();
        Cursor cursor = db.query(DB_CONTACTS_TABLE, new String[] {CONTACTS_TABLE__ID,TABLE_NAME,TABLE_AREA_CODE, TABLE_PHONE_NUMBER,TABLE_ADDRESS,TABLE_BIRTHDAY,TABLE_EMAIL,TABLE_FACE, TABLE_FAV}, CONTACTS_TABLE__ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        person.set_id(cursor.getInt(cursor.getColumnIndex(CONTACTS_TABLE__ID)));
        person.setName(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));
        person.setAreaCode(cursor.getString(cursor.getColumnIndex(TABLE_AREA_CODE)));
        person.setPhoneNumber(cursor.getString(cursor.getColumnIndex(TABLE_PHONE_NUMBER)));
        person.setAddress(cursor.getString(cursor.getColumnIndex(TABLE_ADDRESS)));
        person.setBirthday(cursor.getString(cursor.getColumnIndex(TABLE_BIRTHDAY)));
        person.setEmail(cursor.getString(cursor.getColumnIndex(TABLE_EMAIL)));
        person.setFace(cursor.getString(cursor.getColumnIndex(TABLE_FACE)));
        person.setFave(1 == cursor.getInt(cursor.getColumnIndex(TABLE_FAV)));
        return person;
    }
    public int updateContacts(ContactUnit contactItem)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contact = new ContentValues();
        contact.put(TABLE_NAME, contactItem.getName());
        contact.put(TABLE_EMAIL,contactItem.getEmail());
        contact.put(TABLE_AREA_CODE,contactItem.getAreaCode());
        contact.put(TABLE_PHONE_NUMBER,contactItem.getPhoneNumber());
        contact.put(TABLE_ADDRESS,contactItem.getAddress());
        contact.put(TABLE_BIRTHDAY,contactItem.getDate());
        contact.put(TABLE_FACE,contactItem.getFace());
        contact.put(TABLE_FAV,(contactItem.getFave()));//? 1 : 0);
        return db.update(DB_CONTACTS_TABLE, contact, CONTACTS_TABLE__ID + " = ?", new String[]{String.valueOf(contactItem.get_id())});
    }
    public void deleteContact(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_CONTACTS_TABLE, CONTACTS_TABLE__ID + " = ?", new String[] { String.valueOf(id)});
        db.close();
    }
    public int getContactCount()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CONTACTS;",null);
        cursor.close();
        return cursor.getCount();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + DB_RECENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_CONTACTS_TABLE);
        onCreate(db);
    }
    
    public List<String> readNamesOnlyFromContacts(String query)
    {
        List<ContactUnit> names =readBulkNamesFromContacts(query);
        List<String> namesString = new ArrayList<>();
        int i = 0;
        ContactUnit temp;
        while (i < names.size())
        {
            temp = names.get(i);
            namesString.add(temp.getName());
            i++;
        }
        
        return namesString;
    }
    
    public List<String> readFacesOnly(String query)
    {
        List<ContactUnit> names =readBulkNamesFromContacts(query);
        List<String> faceString = new ArrayList<>();
        int i = 0;
        ContactUnit temp;
        while (i < names.size())
        {
            temp = names.get(i);
            faceString.add(temp.getFace());
            i++;
        }
        return faceString;
    }
    public List<RecentUnit> readBulkRecent(String query)
    {
        List<RecentUnit> names = new ArrayList<RecentUnit>();
        SQLiteDatabase db = this.getReadableDatabase();
        //ContactUnit temp = new ContactUnit();
        RecentUnit temp2 = new RecentUnit();
        try
        {
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst())
            {
                do
                {
                    RecentUnit temp = new RecentUnit();
                    temp.setCon_id(cursor.getInt(cursor.getColumnIndex(CONTACTS_TABLE__ID)));
                    temp.set_id(cursor.getInt(cursor.getColumnIndex(DB_Recent_TABLE__ID)));
                    temp.setName(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));
                    temp.setAreaCode(cursor.getString(cursor.getColumnIndex(TABLE_AREA_CODE)));
                    temp.setPhoneNumber(cursor.getString(cursor.getColumnIndex(TABLE_PHONE_NUMBER)));
                    names.add(temp);
                }while (cursor.moveToNext());
            }
        
        }catch(SQLiteException e)
        {
            names.add(temp2);
        }
        return names;
    }
    
    public RecentUnit readRecentOnly(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        RecentUnit person = new RecentUnit();
        Cursor cursor = db.query(DB_RECENT_TABLE, new String[] {CONTACTS_TABLE__ID,DB_Recent_TABLE__ID,TABLE_NAME,TABLE_AREA_CODE, TABLE_PHONE_NUMBER}, DB_Recent_TABLE__ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        person.setCon_id(cursor.getInt(cursor.getColumnIndex(CONTACTS_TABLE__ID)));
        person.set_id(cursor.getInt(cursor.getColumnIndex(DB_Recent_TABLE__ID)));
        person.setName(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));
        person.setAreaCode(cursor.getString(cursor.getColumnIndex(TABLE_AREA_CODE)));
        person.setPhoneNumber(cursor.getString(cursor.getColumnIndex(TABLE_PHONE_NUMBER)));
        
        return person;
    }
    
    public void insertRecent(RecentUnit item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contact = new ContentValues();
        contact.put(TABLE_NAME, item.getName());
        contact.put(CONTACTS_TABLE__ID,item.getCon_id());
        contact.put(TABLE_AREA_CODE,item.getAreaCode());
        contact.put(TABLE_PHONE_NUMBER,item.getPhoneNumber());
        db.insert(DB_RECENT_TABLE,null,contact);
        db.close();
        
    }
    public List<String> readNamesOnlyFromRecent(String query)
    {
        List<RecentUnit> names =readBulkRecent(query);
        List<String> namesString = new ArrayList<>();
        int i = 0;
        RecentUnit temp;
        while (i < names.size())
        {
            String temppa;
            temp = names.get(i);
            temppa = temp.getName() + " " + temp.getAreaCode() + temp.getPhoneNumber();
            namesString.add(temppa);
            i++;
        }
        return namesString;
    }
}