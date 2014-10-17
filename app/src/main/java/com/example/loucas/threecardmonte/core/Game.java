package com.example.loucas.threecardmonte.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by loucas on 12/10/2014.
 */
public class Game {

    private int numWins = 0;
    private int numLosses = 0;
    private int diff = 0;
    private CardPack cardPack = null;
    private Date date = null;

    public Game() {
        cardPack = new CardPack();
        setDate();
        startNewGame();
    }

    //starting a new game, increase number of games played and shuffle the card pack
    public void startNewGame() {
        cardPack.shufflePack();
        calcDiff();
    }
    //wins - losses
    private void calcDiff() {
        diff = getWins()-getLosses();
    }

    private void setDate() {
        this.date = new Date();


    }

    //return the date/time that this instance of the game was started
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }


    public int getNumOfGamesPlayed() {
        return getWins()+getLosses();
    }

    public List<Card> getCardPack() {
        return cardPack.getCardList();
    }

    public void gameWon() {
        this.numWins++;
    }

    public void gameLost() {
        this.numLosses++;
    }

    public int getWins() {
        return this.numWins;
    }

    public int getLosses() {
        return this.numLosses;
    }

    public int getDiff(){return  this.diff;}
}
