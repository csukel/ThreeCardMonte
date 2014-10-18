package com.example.loucas.threecardmonte.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.loucas.threecardmonte.core.GameInfo;

import java.util.ArrayList;

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

    //todo add a method to retrieve all data from the GameInfo table

    /**
     * Get chat rooms
     */
    public ArrayList<GameInfo> getGamesInfo() {
        ArrayList<GameInfo> list = new ArrayList<GameInfo>();

        String sql = "select " + KEY_NICKNAME + "," + KEY_DATE + ", " + KEY_WINS + ", " +
                KEY_LOSSES + ", " + KEY_DIFF + " from " + TABLE_NAME + ";";

        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String playerNick = cursor.getString(cursor.getColumnIndex(KEY_NICKNAME));
                String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                int wins = cursor.getInt((cursor.getColumnIndex(KEY_WINS)));
                int losses = cursor.getInt((cursor.getColumnIndex(KEY_LOSSES)));
                int diff = cursor.getInt(cursor.getColumnIndex(KEY_DIFF));
                GameInfo gameInfo = new GameInfo(playerNick, date, wins, losses, diff);
                list.add(gameInfo);
            } while (cursor.moveToNext());

        }
        cursor.close();

        return list;
    }
}
