package com.all.learning.custom_view.shorts.card_master.cards;


import java.io.Serializable;

/**
 * Created by root on 26/4/17.
 */

public class Card implements Serializable {
    public int position;
    public int type;
    public String msg;
    public int TAG;

    public Card(String msg, int TAG) {
        this.msg = msg;
        this.TAG = TAG;
    }

    public Card(String msg) {
        this.msg = msg;
    }

    public Card() {

    }
}
