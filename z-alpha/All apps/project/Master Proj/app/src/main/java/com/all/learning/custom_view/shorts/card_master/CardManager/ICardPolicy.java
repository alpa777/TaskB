package com.all.learning.custom_view.shorts.card_master.CardManager;


import com.all.learning.custom_view.shorts.card_master.cards.Card;

/**
 * Created by root on 31/5/17.
 */

public interface ICardPolicy<T extends Card> {

//    void onBoard();
//
//    void progress();
//
//    void data();
//
//    void error();
//
//    void over();
//
//    void nodata();

    void add(T card);
    void remove();
    void removeAll();

    void clear();

    boolean isEmpty();

}
