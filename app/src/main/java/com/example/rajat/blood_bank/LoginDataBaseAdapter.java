package com.example.rajat.blood_bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Rajat on 11-Apr-17.
 */
public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    public static final String TABLE_NAME = "LOGIN";
    public static final String ID = "ID";
    public static final String USER_NAME = "USERNAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String CONTACT = "CONTACT";
    public static final String PASSWORD = "PASSWORD";
    public static final String BLOOD_GROUP = "BLOOD";
    public static final String Email = "Email";
    public static final String Name = "Name";

    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT UNIQUE, " + ADDRESS + " TEXT, "+CONTACT + " TEXT, "+BLOOD_GROUP + " TEXT, "+Email + " TEXT, "+Name + " TEXT, " + PASSWORD + " TEXT)";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public long insertEntry(UserData objUserData) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(USER_NAME, objUserData.getUserName());
        newValues.put(PASSWORD, objUserData.getPassword());
        newValues.put(ADDRESS, objUserData.getAddress());
        newValues.put(CONTACT,objUserData.getContact());
        newValues.put(BLOOD_GROUP,objUserData.getBgroup());
        newValues.put(Name,objUserData.getName());
        newValues.put(Email,objUserData.getEmail());

        // Insert the row into your table
        long row = db.insert(TABLE_NAME, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
        return row;
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query(TABLE_NAME, null, USER_NAME + " = ?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
        cursor.close();
        return password;
    }
    public boolean checkUsername(String userName) {
        Cursor cursor = db.query(TABLE_NAME, null, USER_NAME + " = ?", new String[]{userName}, null, null, null);
        if (cursor.getCount() >0) {// UserName Not Exist
            return true;
        }
        return false;

    }
    public String getName(String bgroup)
    {
        Cursor cursor = db.query(TABLE_NAME, null, BLOOD_GROUP + " = ?", new String[]{bgroup}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String name= cursor.getString(cursor.getColumnIndex(USER_NAME));
        cursor.close();
        return name;
    }

    public UserData getUserData(String userName) {

        UserData objUserData = null;
        Cursor cursor = null;

        db = dbHelper.getReadableDatabase();
        try {
            cursor = db.query(TABLE_NAME, null, USER_NAME + " = ?", new String[]{userName}, null, null, null);
            if (cursor.getCount() < 1) // UserName Not Exist
            {
                cursor.close();
                return objUserData;
            }
            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
            String contact=cursor.getString(cursor.getColumnIndex(CONTACT));
            String blood=cursor.getString(cursor.getColumnIndex(BLOOD_GROUP));
            String name=cursor.getString(cursor.getColumnIndex(Name));
            String email=cursor.getString(cursor.getColumnIndex(Email));

            objUserData = new UserData(name,userName, password, address,contact,blood,email);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        return objUserData;
    }
    public Cursor getsome(String bGroup)
    {
        Cursor res=db.query(TABLE_NAME,new String[]{USER_NAME,CONTACT},BLOOD_GROUP+" =?",new String[]{bGroup},null,null,null);
        return res;
    }

    public void updateEntry(String userName, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }
}



