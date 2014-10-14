package com.example.loucas.threecardmonte.core;

import java.util.List;

/**
 * Created by loucas on 12/10/2014.
 */
public class Game {
    private int numOfGames = 0;
    private int numWins = 0;
    private int numLosses = 0;
    private int diff = 0;
    private CardPack cardPack = null;

    public Game() {
        cardPack = new CardPack();
        startNewGame();
    }

    //starting a new game, increase number of games played and shuffle the card pack
    public void startNewGame() {
        cardPack.shufflePack();
        calcDiff();
        numOfGames++;
    }
    //wins - losses
    private void calcDiff() {
        diff = getWins()-getLosses();
    }


    public int getNumOfGamesPlayed() {
        return numOfGames;
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
