package com.example.loucas.threecardmonte.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by loucas on 12/10/2014.
 */
public class CardPack {
    private Card card1 = null;
    private Card card2 = null;
    private Card card3 = null;
    private List<Card> cardList;

    //Initialize the card pack
    public CardPack() {
        cardList = new ArrayList<Card>();
        card1 = new Card("queen");
        card2 = new Card("jack");
        card3 = new Card("king");
        fillTheList();
        //initial shuffle
        shufflePack();

    }

    //Add the three cards in the list
    private void fillTheList() {
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
    }

    public void shufflePack() {
        int randromNum = randInt(2, 50);
        for (int i = 0; i < randromNum; i++) {
            Collections.shuffle(cardList);
        }
    }

    private int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    //return the list of cards
    public List<Card> getCardList() {
        return cardList;
    }

}
