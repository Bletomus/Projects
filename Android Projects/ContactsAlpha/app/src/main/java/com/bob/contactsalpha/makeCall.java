package com.bob.contactsalpha;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.bob.contactsalpha.ContactListDatabaseHelper.DB_CONTACTS_TABLE;
import static com.bob.contactsalpha.ContactListDatabaseHelper.DB_RECENT_TABLE;
import static com.bob.contactsalpha.Create_Contact.EXTRA_RECORD_ID;

public class makeCall extends AppCompatActivity
{
    //Stores the constant for the name of what shall be sent using the intent
    public static final String PNUM = "num";
    
    //variables for storing information in the phone
    public int contactId;
    private String number = "";
    private List<RecentUnit> list;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_call);
        Intent intent = getIntent();
        if(intent != null)
        {
            number = intent.getStringExtra(PNUM);
        }
        
        if(savedInstanceState != null)
        {
            number = savedInstanceState.getString(EXTRA_RECORD_ID);
        }
    
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logosmall);
        toolbar.setTitleTextColor(Color.BLACK);
        
    //The below code will set up the listview and how it shall look
        ListView recentList = findViewById(R.id.recentList);
        
        ContactListDatabaseHelper  rec = new ContactListDatabaseHelper(this);
        
        //the query to access the table for recent contacts will be accessed
        list =rec.readBulkRecent("SELECT * FROM " + DB_RECENT_TABLE);
        List<String> namesString = rec.readNamesOnlyFromRecent("SELECT * FROM " + DB_RECENT_TABLE);
        
        
        String contacto[]= namesString.toArray(new String[namesString.size()]);
    
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacto);
        recentList.setAdapter(adapter);
        
       // Toast.makeText(this,"DAtabase is unavaible", Toast.LENGTH_SHORT).show();
    
        
        
        //Create the listener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long id) {
                        RecentUnit temp = list.get(position);
                        number = temp.getAreaCode() + temp.getPhoneNumber();
                        TextView textView = findViewById(R.id.editText);
                        textView.setText(number);
                    }
                };
    
        //Assign the listener to the list view
        recentList.setOnItemClickListener(itemClickListener);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.call_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        final String selectQuery = "SELECT * FROM " +  DB_CONTACTS_TABLE +";";
        switch (item.getItemId())
        {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.add:
                Intent intent = new Intent(this, Create_Contact.class);
                intent.putExtra(EXTRA_RECORD_ID, -1 );
                intent.putExtra(Create_Contact.EXTRA_QUERY, selectQuery);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        
    }
    //The items functions are for the views and will adjust the text box based on the selected button
    
    public void onOne(View view)
    {
        contactId = -1;
        number = number + "1" ;
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    public void onZero(View view)
    {
        contactId = -1;
        number = number + "0" ;
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    public void onTwo(View view)
    {
        contactId = -1;
        number = number + "2" ;
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    public void onThree(View view)
    {
        contactId = -1;
        number = number + "3" ;
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    public void onFour(View view)
    {
        contactId = -1;
        number = number + "4" ;
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    public void onFive(View view)
    {
        contactId = -1;
        number = number + "5";
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    
    public void onSix(View view)
    {
        contactId = -1;
        number = number + "6" ;
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    
    public void onSeven(View view)
    {
        contactId = -1;
        number = number + "7";
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    public void onEight(View view)
    {
        contactId = -1;
        number = number + "8";
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    public void onNine(View view)
    {
        contactId = -1;
        number = number + "9";
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    
    
    public void onClear(View view)
    {
        contactId = -1;
        number = "";
        TextView textView = findViewById(R.id.editText);
        textView.setText(number);
    }
    public void onCall(View view)
    {
        callPhoneNumber();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(EXTRA_RECORD_ID, number);
        
    }
    public void callPhoneNumber()
    {
        TextView textView = findViewById(R.id.editText);
        String number= textView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
        if(Build.VERSION.SDK_INT > 22)
        {
            if (ActivityCompat.checkSelfPermission(makeCall.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(makeCall.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                return;
            }
            
        }
        if (contactId == -1)
        {
            RecentUnit reces = new RecentUnit();
            reces.setPhoneNumber(number);
            reces.setCon_id(0);
            ContactListDatabaseHelper con = new ContactListDatabaseHelper(this);
            con.insertRecent(reces);
        }
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber();
            }
        }
    }
}
