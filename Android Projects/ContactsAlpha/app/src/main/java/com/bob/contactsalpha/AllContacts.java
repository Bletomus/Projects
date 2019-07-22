package com.bob.contactsalpha;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.bob.contactsalpha.ContactListDatabaseHelper.DB_CONTACTS_TABLE;
import static com.bob.contactsalpha.ContactListDatabaseHelper.TABLE_NAME;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllContacts.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllContacts#newInstance} factory method to
 * create an instance of this fragment.
 */


//This fragment will display the list of all the users regardless of the type
public class AllContacts extends Fragment
{
    
    
    public AllContacts()
    {
        // Required empty public constructor
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //instead of using ListFragement recycle view is used for a different aesthetic and greater flexibility
        RecyclerView rec = (RecyclerView)inflater.inflate(R.layout.fragment_all_contacts, container, false);
        
        //this query shall be used to open all the names and it will sort everything based on the name
        final String selectQuery = "SELECT * FROM " +  DB_CONTACTS_TABLE +" order by " + TABLE_NAME + ";";
        
        //Inorder to display the names and faces of the users, we call functions in the database helper that will return the names and faces values in the database
        ContactListDatabaseHelper dbHandler = new ContactListDatabaseHelper(inflater.getContext());
        List<String> namesString = dbHandler.readNamesOnlyFromContacts(selectQuery);
        List<String> faceString = dbHandler.readFacesOnly(selectQuery);
    
        //this array is the list of the names
        String contacto[]=namesString.toArray(new String[namesString.size()]);
        //array with the uri addressees of the faces
        String faces[]=faceString.toArray(new String[faceString.size()]);
        
        //CaptionedImagesAdapter will design the interface and how the items will be displayed for the user
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(contacto, faces);
        rec.setHasFixedSize(true);
        rec.setAdapter(adapter);
    
        //This will design the layout for the recycle view and it will set up the listener.
        //The listener will send the query and position to allow the user to select an item and have the create activity pick up the item based on the query sent
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rec.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), Create_Contact.class);
                intent.putExtra(Create_Contact.EXTRA_RECORD_ID, position);
                intent.putExtra(Create_Contact.EXTRA_QUERY, selectQuery);
                getActivity().startActivity(intent);
            }
        });
        return rec;
    }
    
}
