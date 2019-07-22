package com.bob.contactsalpha;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import static com.bob.contactsalpha.makeCall.PNUM;


public class MainActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //The layout being used is a activity_main so all the xml objects are located there
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        //This will create the toolbar on the mobile app and set the color of the toolbar as black
        toolbar.setNavigationIcon(R.drawable.logosmall);
        toolbar.setTitleTextColor(Color.BLACK);
        
        //This will create the view pager which will be used by the system to scroll throw the adds
        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        
        //This will create a tab layout right under the toolbar that will contain a list of the fragments that will be scrolled through
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);//This will create a menu for the toolbar based on what is inside main_menu
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //This will choose an action based on the view of the menu that is selected
        switch (item.getItemId())
        {
            //this will open create contact activity and send -1 as a flag indicating
            case R.id.action_create_contact:
                Intent intent = new Intent(this, Create_Contact.class);
                intent.putExtra(Create_Contact.EXTRA_QUERY,"SELECT * FROM CONTACTS");
                intent.putExtra(Create_Contact.EXTRA_RECORD_ID, -1);
                startActivity(intent);
                return true;
                //this will open the call activity and it will send a blank number
            case R.id.call:
                Intent intent2 = new Intent(this, makeCall.class);
                intent2.putExtra(PNUM, "");
                startActivity(intent2);
                return true;
                //this will refresh the entire activity and thus the list
            case R.id.action_refresh:
                finish();
                startActivity(getIntent());
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //this class is designed to link the tab layout and the view pager so as the allowing the user to select the view based on the tabs
    public class SectionPagerAdapter extends FragmentPagerAdapter
    {
        
        public SectionPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        
        @Override
        public int getCount()
        {
            return 3;
        }
        
        //returns the fragment that must be displayed based on the tab that is selected
        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new AllContacts();
                case 1:
                    return new Favourites();
                case 2:
                    return new Services();
            }
            return null;
        }
        //This will setup the title of the tabs
        @Override
        public CharSequence getPageTitle(int position)
        {
            switch(position)
            {
                case 0:
                    return getResources().getText(R.string.all);
                case 1:
                    return getResources().getText(R.string.fav);
                case 2:
                    return getResources().getText(R.string.serv);
            }
            return null;
        }
        
    }
}
