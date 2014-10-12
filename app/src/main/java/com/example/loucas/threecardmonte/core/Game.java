package com.example.loucas.threecardmonte.core;

import java.util.List;

/**
 * Created by loucas on 12/10/2014.
 */
public class Game {
    private int numOfGames = 0;
    private int numWins = 0;
    private int numLosses = 0;
    private CardPack cardPack = null;

    public Game() {
        cardPack = new CardPack();
        startNewGame();
    }

    public void startNewGame() {
        cardPack.shufflePack();
        numOfGames++;
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

}
