package com.example.loucas.threecardmonte.core;

/**
 * Created by loucas on 12/10/2014.
 */
public class Card {
    private String cardFace = null;

    public Card(String face) {
        this.cardFace = face;
    }

    //get the sting object of the card face
    public String getCardFace() {
        return this.cardFace;
    }
}
