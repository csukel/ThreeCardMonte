package com.example.loucas.threecardmonte.core;

/**
 * Created by loucas on 14/10/2014.
 */
public class Player {
    private String nickname=null;
    private String password=null;

    public Player(String name,String pass){
        this.nickname=name;
        this.password=pass;
    }

    public String getNickname(){
        return this.nickname;
    }

}
