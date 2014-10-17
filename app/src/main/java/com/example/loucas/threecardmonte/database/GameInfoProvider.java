package com.example.loucas.threecardmonte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by loucas on 17/10/2014.
 */
public class GameInfoProvider {
    private DBHelper dbHelper = null;
    private static final String LOG_TAG = "GameInfoProvider";
    public static final String TABLE_NAME = "GAMES_INFO";
    public static final String KEY_NICKNAME = "PLAYER_ID";
    public static final String KEY_DATE = "DATETIME_PLAYED";
    public static final String KEY_WINS = "WINS";
    public static final String KEY_LOSSES = "LOSSES";
    public static final String KEY_DIFF = "SUM";

    /**
     * Constructor
     */
    public GameInfoProvider(Context context) {
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
    public void addGameInfo(String nickname, String date, int wins, int losses, int sum) {
        if (null != nickname && null != date) {
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES" + "(" + "'" + nickname + "'"
                    + ", " + "'" + date + "'" + "," + wins + "," + losses + "," + sum + ");";
            dbHelper.insert(sql);
        } else {
            Log.v(LOG_TAG, "Cannot add user information, "
                    + "because user has null value!");
        }
    }
}
