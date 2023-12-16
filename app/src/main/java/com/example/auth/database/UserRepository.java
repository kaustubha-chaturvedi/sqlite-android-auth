package com.example.auth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserRepository {

    private DBHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_USERNAME, username);
        contentValues.put(DBHelper.COLUMN_PASSWORD, password);

        long result = db.insert(DBHelper.TABLE_NAME, null, contentValues);
        return result != -1; // Returns true if insertion was successful, false otherwise
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DBHelper.COLUMN_USERNAME};
        String selection = DBHelper.COLUMN_USERNAME + " = ? AND " + DBHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                DBHelper.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }

    public String getUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DBHelper.COLUMN_USERNAME};
        String selection = DBHelper.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                DBHelper.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String loggedInUsername = null;
        if (cursor.moveToFirst()) {
            loggedInUsername = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_USERNAME));
        }
        cursor.close();
        return loggedInUsername;
    }
}
