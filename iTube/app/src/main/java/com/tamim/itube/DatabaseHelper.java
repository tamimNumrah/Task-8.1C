package com.tamim.itube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final String USER_TABLE_NAME = "user";
    private static final String PLAYLIST_TABLE_NAME = "playlist";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+USER_TABLE_NAME+" (id TEXT PRIMARY KEY, name TEXT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE "+PLAYLIST_TABLE_NAME+" (id TEXT PRIMARY KEY, url TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // handle any database schema changes in future versions of your app
    }
    public Boolean insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        long rowId = db.insert(USER_TABLE_NAME, null, values);
        db.close();
        if (rowId > -1) {
            System.out.println("Insert User successful. Row ID: " + rowId);
            return true;
        } else {
            System.out.println("Insert failed.");
            return false;
        }
    }
    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME, new String[] { "id", "name", "username", "password" },
                "username=?", new String[] { username }, null, null, null, null);
        if( cursor != null && cursor.getCount() > 0 ) {
            cursor.moveToFirst();
        } else {
            return null;
        }

        User user = new User(cursor);
        cursor.close();
        db.close();
        return user;
    }
    public Boolean deleteUser(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id" + " = ?";
        String[] whereArgs = { id };
        int numRowsDeleted = db.delete(USER_TABLE_NAME, whereClause, whereArgs);
        db.close();
        if (numRowsDeleted > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean insertPlayItem(PlayItem playItem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", playItem.getId());
        values.put("url", playItem.getUrl());
        long rowId = db.insert(PLAYLIST_TABLE_NAME, null, values);
        db.close();
        if (rowId > -1) {
            System.out.println("Insert PlayItem successful. Row ID: " + rowId);
            return true;
        } else {
            System.out.println("Insert failed.");
            return false;
        }
    }
    public List<PlayItem> getAllPlayItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PLAYLIST_TABLE_NAME, null, null, null, null, null, null);
        List<PlayItem> result = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            result.add(new PlayItem(cursor));
        }
        cursor.close();
        db.close();
        return result;
    }

    public Boolean deletePlayItem(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id" + " = ?";
        String[] whereArgs = { id };
        int numRowsDeleted = db.delete(PLAYLIST_TABLE_NAME, whereClause, whereArgs);
        db.close();
        if (numRowsDeleted > 0) {
            return true;
        } else {
            return false;
        }
    }

}
