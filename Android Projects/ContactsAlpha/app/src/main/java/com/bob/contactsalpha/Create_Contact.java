package com.bob.contactsalpha;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.List;

import static com.bob.contactsalpha.makeCall.PNUM;


public class Create_Contact extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    //To allow communication of information between the functions I will use the the varibales bellow
    private String realPath ="";//this will save the path uri that will locate the image of the user from the phone
    ArrayAdapter<CharSequence> adapter;//this is an array adapter for the list of countries
    ArrayAdapter<CharSequence> adapter2; //this is an array adapter for the list of area codes
    private long contactId;//this will save the value of the contactId
    private String query;//saves the query that will be sent by the main activity
    private List<ContactUnit> names; //creates a list of the ContactUnit class
    private ContactUnit name = new ContactUnit();//creates an instance of ContactUnit class
    private int spinnerId = 0; //saves the spinner address
    
    //constants to identify what value was sent through the intent
    public static final String EXTRA_RECORD_ID= "ID";
    public static final String EXTRA_QUERY= "query";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_PHONENUMBER = "phoneNumber";
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRA_BIRTHDAY = "birthday";
    public static final String EXTRA_FACE= "face";
    public static final String EXTRA_POSITION = "pos";
    public static final String EXTRA_BOOL = "maybe";
    private Boolean isChecked = false; //
    
    // this will set up a listener for the label on the screen for when the user to select a date
   private DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            month = month + 1;
            Log.d("Create_Contact", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
            TextView datee = findViewById(R.id.editBith);
            String sm;
            String dm;
            if(month < 10 )
            {
                sm = "0" + Integer.toString(month);
            }
            else
            {
                sm =Integer.toString(month);
            }
            
            if(day < 10)
            {
                dm = "0" + Integer.toString(day);
            }
            else
            {
                dm = Integer.toString(day);
            }
            
            
            
            String date = year + "-" + sm + "-" + dm;
            datee.setText(date);
        }
    };
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__contact);
        Intent intent = getIntent();
        query = intent.getStringExtra(EXTRA_QUERY);
        contactId = intent.getIntExtra(EXTRA_RECORD_ID,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logosmall);
        toolbar.setTitleTextColor(Color.BLACK);
        setSpinner();
        
        TextView datee = findViewById(R.id.editBith);
        
        //sets up the text box to display dates and adds the listener made above
        datee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                
                DatePickerDialog dialog = new DatePickerDialog(
                        Create_Contact.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    
        
        //restores the values entered when the screen is titled
        if(savedInstanceState != null)
        {
            name.setName(savedInstanceState.getString(EXTRA_NAME));
            name.setEmail(savedInstanceState.getString(EXTRA_EMAIL));
            name.setAddress(savedInstanceState.getString(EXTRA_ADDRESS));
            name.setBirthday(savedInstanceState.getString(EXTRA_BIRTHDAY));
            name.setPhoneNumber(savedInstanceState.getString(EXTRA_PHONENUMBER));
            name.setFace(savedInstanceState.getString(EXTRA_FACE));
            name.setFave(savedInstanceState.getBoolean(EXTRA_BOOL));
            spinnerId = savedInstanceState.getInt(EXTRA_POSITION);
            isChecked = savedInstanceState.getBoolean(EXTRA_BOOL);
            reeni(name);
        }
        else
        {
            ini();
        }
        
    }
    //future functions to freeze the views as and when required
    /*private void setUnIce(Boolean ya)
    {
        EditText name = findViewById(R.id.editName);
        EditText email = findViewById(R.id.editAlpha);
        EditText address = findViewById(R.id.editAddress);
        TextView birthday = findViewById(R.id.editBith);
        EditText phone = findViewById(R.id.editContact);
    
        name.setFocusable(ya);
        email.setFocusable(ya);
        address.setFocusable(ya);
        birthday.setFocusable(ya);
        phone.setFocusable(ya);
    }*/
    private void getRecords()
    {
        //makes a list of the all the contacts on the phone and plants the list
        //not very safe as all the contacts have been called to memory and may be changed in later updates
        ContactListDatabaseHelper dbHandler = new ContactListDatabaseHelper(this);
        names = dbHandler.readBulkNamesFromContacts(query);
    }
    
    //this function will input the information in the ContactUnit into the items on the screen
    private void reeni(ContactUnit contact)
    {
        //makes objects of the items on the screen
        EditText name = findViewById(R.id.editName);
        EditText email = findViewById(R.id.editAlpha);
        EditText address = findViewById(R.id.editAddress);
        TextView birthday = findViewById(R.id.editBith);
        EditText phone = findViewById(R.id.editContact);
        Spinner spin = findViewById(R.id.country);
        Spinner spin2 = findViewById(R.id.code);
        
        
        spin.setSelection(spinnerId);
        spin2.setSelection(spinnerId);
        
        name.setText(contact.getName());
        email.setText(contact.getEmail());
        address.setText(contact.getAddress());
        birthday.setText(contact.getBirthday());
        phone.setText(contact.getPhoneNumber());
        isChecked = contact.getFave();
        set(isChecked);
        
        realPath = contact.getFace();
        setImage(realPath);
        
    }
    
    public void set(Boolean isChecked)
    {
        //changes the imagebutton image depending on what has been selected by the user
        ImageButton button = findViewById(R.id.imageButton);
        if(isChecked)
        {
            button.setImageResource(R.drawable.heart_full);
        }
        else
        {
            button.setImageResource(R.drawable.heart_empty);
        }
    }
    
    public void onPick(View view)
    {
        isChecked = !isChecked ? true : false;
        set(isChecked);
    }
    //this initializes the layout items based on what has been sent in the intent in contactId. -1 for empty and anything else for available
    private void ini()
    {
        
        ContactUnit contact;
        getRecords();
        if(contactId >= 0)
            contact = names.get((int) contactId);
        else
            contact = new ContactUnit();
        
        
            
        Button save = findViewById(R.id.saver);
        Button update = findViewById(R.id.Update);
        Button edit = findViewById(R.id.editter);
        Button delete = findViewById(R.id.delete);
       
        
        update.setVisibility(View.INVISIBLE);
        update.setEnabled(false);
        //sets up which buttons must be shown when the view is opened. Will change in the future to have view on top of each other
        if(contactId < 0)
        {
            
            save.setVisibility(View.VISIBLE);
            save.setEnabled(true);
            edit.setVisibility(View.INVISIBLE);
            edit.setEnabled(false);
            delete.setVisibility(View.INVISIBLE);
            delete.setEnabled(false);
            //setUnIce(true);
        }
        else
        {
            save.setVisibility(View.INVISIBLE);
            save.setEnabled(false);
            edit.setVisibility(View.VISIBLE);
            edit.setEnabled(true);
            delete.setVisibility(View.VISIBLE);
            delete.setEnabled(true);
           // setUnIce(false);
        }
        
        spinnerId = adapter2.getPosition(contact.getAreaCode());//sets up the values of the spinner based on what has been entere
        if(spinnerId == -1)
            spinnerId = 0;
        reeni(contact);
    }
    
    //Saves the information in the
    public void onSaver(View view)
    {
        ContactListDatabaseHelper contactsHelper = new ContactListDatabaseHelper(this);
        ContactUnit contact = getDetails();
        contactsHelper.insertContact(contact);
        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
    //takes all the information in the views on the screen and returns a ContactUnit class instance
    public ContactUnit getDetails()
    {
        EditText name = findViewById(R.id.editName);
        EditText email = findViewById(R.id.editAlpha);
        EditText address = findViewById(R.id.editAddress);
        TextView birthday = findViewById(R.id.editBith);
        EditText phone = findViewById(R.id.editContact);
        Spinner spinner = findViewById(R.id.code);
        
        ContactUnit contact = new ContactUnit();
        
        contact.setName(name.getText().toString());
        contact.setEmail(email.getText().toString());
        contact.setAddress(address.getText().toString());
        contact.setBirthday(birthday.getText().toString());
        contact.setPhoneNumber(phone.getText().toString());
        contact.setAreaCode(String.valueOf(spinner.getSelectedItem()));
        contact.setFace(realPath);
        contact.setFave(isChecked);
        
        return contact;
    }
    
    //based on what is the database information will taken from the database and sent into the file
    public void onUpdater(View view)
    {
       
        List<ContactUnit> list=names;
        ContactUnit temp = list.get((int) contactId);
    
        ContactUnit contact = getDetails();
        contact.set_id(temp.get_id());
        
        ContactListDatabaseHelper contactsHelper = new ContactListDatabaseHelper(this);
        int i = contactsHelper.updateContacts(contact);
        Toast.makeText(Create_Contact.this,"Done",Toast.LENGTH_LONG).show();
        ini();
    }
    //changes the views that are visible to invisible and likewise for invisible
    public void onEdittor(View view)
    {
        
        Button save = findViewById(R.id.saver);
        Button update = findViewById(R.id.Update);
        Button edit = findViewById(R.id.editter);
        Button delete = findViewById(R.id.delete);
        
        save.setVisibility(View.INVISIBLE);
        save.setEnabled(false);
        update.setVisibility(View.VISIBLE);
        update.setEnabled(true);
        edit.setVisibility(View.INVISIBLE);
        edit.setEnabled(false);
        delete.setVisibility(View.INVISIBLE);
        delete.setEnabled(false);

        
    }
    //deletes the item that may be opened and then opens the main activity
    public void onDeleter(View view)
    {
        
        List<ContactUnit> list=names;
        ContactUnit temp = list.get((int) contactId);
        
        ContactListDatabaseHelper contactsHelper = new ContactListDatabaseHelper(this);
        contactsHelper.deleteContact(temp.get_id());
        Toast.makeText(Create_Contact.this,"Deleted",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
    //saves the information for when teh screen titles
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong(EXTRA_RECORD_ID, contactId);
        ContactUnit contact = getDetails();
        savedInstanceState.putString(EXTRA_NAME, contact.getName());
        savedInstanceState.putString(EXTRA_EMAIL, contact.getEmail());
        savedInstanceState.putString(EXTRA_ADDRESS, contact.getAddress());
        savedInstanceState.putString(EXTRA_PHONENUMBER, contact.getPhoneNumber());
        savedInstanceState.putString(EXTRA_BIRTHDAY,contact.getBirthday());
        savedInstanceState.putString(EXTRA_FACE,contact.getFace());
        savedInstanceState.putInt(EXTRA_POSITION,spinnerId);
        savedInstanceState.putBoolean(EXTRA_BOOL,contact.getFave());
    }
    
    
    public void onSelector(View view)
    {
        
        makeCheck();
    }
    
    private void makeCheck()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //This will check whether the phone has a permission to look at the images that are within the phone
        if (ActivityCompat.checkSelfPermission(Create_Contact.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Create_Contact.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);//calls a special function that will ask for the permission to view images
            return;
        }
        startActivityForResult(intent, 0);//if the permission is available then the iamge manager will be opened
    }
    
    //sets up the menu for the app using the given layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.create_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makeCheck();//if the permission is granted then the activity for file manager will be called
            }
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            //This will choose an action based on the view of the menu that is selected
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);//goes back to the previous activity
                return true;
                
            case R.id.call:
                //will call the make a call activity and then it will start a new call based on the area code and the phone number entered
                ContactUnit contact;
                ContactListDatabaseHelper contactsHelper = new ContactListDatabaseHelper(this);
                if(contactId == -1)
                {
                    contact = getDetails();
                    if(contact.getName() == "") //if the name field is empty the unknown value is set as the name item
                    {
                        contact.setName("Unknown");
                    }
                }
                else
                {
                    contact = names.get((int) contactId);//if the user is not new then the id of the contact will be added to the new instance of ContactUnit
                }
                
                RecentUnit recent = new RecentUnit();
                recent.setCon_id(contact.get_id());
                recent.setAreaCode(contact.getAreaCode());
                recent.setName(contact.getName());
                recent.setPhoneNumber(contact.getPhoneNumber());
                contactsHelper.insertRecent(recent); //this function will save the value of the new recent call that was about to be made
                //will be adusted to only be updated when the call is made in later updates
    
                String number=recent.getAreaCode() + recent.getPhoneNumber();
                Intent intent = new Intent(this, makeCall.class);
                intent.putExtra(PNUM,number);//sends phone number including the area code in a single string
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        
    }
    
    
    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data)
    {
        String filePath;
        if(resCode == Activity.RESULT_OK && data != null)
        {
        //    realPath = RealPath.getRealPathFromURI(this, data.getData());
            realPath = RealPath.getPathFromGooglePhotosUri(this, data.getData());//uses google as a file manager...yeah this will definitely be changed to all for other phones
            setImage(realPath);
        }
    }
    
    private void setImage(String real)
    {
        //sets up the image based on the uri that has been sent to it
        Uri uriFromPath = Uri.fromFile(new File(real));
        int i = Build.VERSION.SDK_INT;
        Bitmap bitmap = null;
        ImageButton image= findViewById(R.id.editFace);
        try
        {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriFromPath));
            image.setImageBitmap(bitmap);
        } catch (FileNotFoundException e)
        {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.unknown);
            image.setImageDrawable(drawable);
        }
        image.setBackgroundResource(android.R.color.transparent);
    }
    
    //This function will set up the spinner with the values from the string array
    
    private void setSpinner()
    {
        Spinner spinner1 = findViewById(R.id.country);
        adapter = ArrayAdapter.createFromResource(this, R.array.country_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
    
    
        Spinner spinner2 = findViewById(R.id.code);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.country_code, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        
        spinner2.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
    }
    //in Case one spinner is changed the other will change with it to match the selected item
    private void loadContentOnSpinner2(int i, Boolean who)
    {
        if(who)
        {
            Spinner spinner2 = findViewById(R.id.code);
            spinner2.setSelection(i, true);
        }
        else
        {
            Spinner spinner2 = findViewById(R.id.country);
            spinner2.setSelection(i,true);
        }
    }
    //listener for the spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        int i;
        i =parent.getId();
        final int j= R.id.country;
        final int k = R.id.code;
        switch( i)
        {
            case j:
                loadContentOnSpinner2(position,true);
                break;
            case k:
                loadContentOnSpinner2(position, false);
                break;
        }
       
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
    
    }
}
