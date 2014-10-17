package com.example.loucas.threecardmonte.core;

/**
 * Created by loucas on 17/10/2014.
 */
public class GameInfo {
    private String playerNick = null;
    private String datePlayed = null;
    private int diff;
    private int wins;
    private int losses;


    public GameInfo(String player, String date, int w, int l, int score) {
        this.playerNick = player;
        this.datePlayed = date;
        this.wins = w;
        this.losses = l;
        this.diff = score;
    }

    public String getPlayerNick() {
        return this.playerNick;
    }

    public String getDatePlayed() {
        return this.datePlayed;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public int getDiff() {
        return this.diff;
    }
}

