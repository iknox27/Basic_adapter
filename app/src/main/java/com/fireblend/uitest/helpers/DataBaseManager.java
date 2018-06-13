package com.fireblend.uitest.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fireblend.uitest.bd.DataBaseHelper;
import com.fireblend.uitest.entities.Contact;
import com.fireblend.uitest.ui.MainActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseManager {

    DataBaseHelper bdHelper;
    private static DataBaseManager singleton = new DataBaseManager( );

    private DataBaseManager() { }

    public static DataBaseManager getInstance( ) {
        if(singleton == null) {
            singleton = new DataBaseManager();
        }
        return singleton;
    }

    public void startHelper(Context context){
        if (bdHelper == null) {
            bdHelper = new DataBaseHelper(context);
        }
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try {
            Dao<Contact, Integer> userDao = bdHelper.getUserDao();
            contacts = (ArrayList<Contact>) userDao.queryForAll();
            //Si no se encontro ningun usuario, es porque no existe
            if(contacts.size() == 0){
                //Toast.makeText(MainActivity.this, "Ese usuario no existe!", Toast.LENGTH_SHORT).show();
                return contacts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  contacts;
    }

    public boolean saveContact(Contact c){
        try {
            Dao<Contact, Integer> userDao = bdHelper.getUserDao();
            userDao.create(c);
            return true;
        } catch (SQLException e) {
            Log.e("Create", e.getMessage().toString());
            e.printStackTrace();
            return false;
        }
    }
}