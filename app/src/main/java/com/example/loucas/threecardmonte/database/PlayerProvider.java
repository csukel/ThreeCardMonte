package com.example.loucas.threecardmonte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by loucas on 14/10/2014.
 */
public class PlayerProvider {
    private DBHelper dbHelper = null;
    private static final String LOG_TAG = "PlayerProvider";
    public static final String TABLE_NAME = "PLAYER_INFO";
    public static final String KEY_NICKNAME = "NICKNAME";
    public static final String KEY_PASSWORD = "PASSWORD";

    /** Constructor */
    public PlayerProvider(Context context) {
        Log.v(LOG_TAG, "context=" + context.toString());
        dbHelper = new DBHelper(context);
    }

    /**
     * Get the database connection.
     */
    public SQLiteDatabase getConnection() {
        return dbHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void closeConnection() {
        dbHelper.close();
    }

    /**
     * Insert data to player info table when a new player registers
     */
    public void addPlayerInfo(String nickname,String password) {
        if (null != nickname && null != password) {
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES" + "(" + "'" + nickname + "'"
                    + ", " + "'" + password + "');";
            dbHelper.insert(sql);
        } else {
            Log.v(LOG_TAG, "Cannot add user information, "
                    + "because user has null value!");
        }
    }

    public boolean isNicknameReserved(String nick){
        String[] whereArgs = new String[]{KEY_NICKNAME +" = "+ "'"+nick+"'"+";"};
        return dbHelper.isDataExist(TABLE_NAME,whereArgs);
    }

    public boolean isPlayerAuthCorrect(String nick, String pass){
        String[] whereArgs = new String[]{KEY_NICKNAME +" = "+ "'"+nick+"'", KEY_PASSWORD + "="+"'"+pass+"';"};
        return dbHelper.isDataExist(TABLE_NAME,whereArgs);
    }

    /*public Account getUserInfo(){
        Account account = null;
        String sql = "select " + KEY_USERNAME +","+ KEY_PASSWORD +
                " from " + TABLE_NAME + ";";

        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(sql, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                String username = cursor.getString(cursor.getColumnIndex(KEY_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
                account = new Account(username,password);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return account;

    }*/
}
