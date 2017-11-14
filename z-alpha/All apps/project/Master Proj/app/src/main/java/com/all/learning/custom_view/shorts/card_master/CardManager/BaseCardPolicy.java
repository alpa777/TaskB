package com.all.learning.custom_view.shorts.card_master.CardManager;


import com.all.learning.custom_view.shorts.card_master.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 31/5/17.
 */

public abstract class BaseCardPolicy<T extends Card> implements ICardPolicy<T> {
    public List<Card> mCards;

    public List<Card> myCards = new ArrayList<>();

    @Override
    public void add(T card) {
        mCards.add(card);
        myCards.add(card);
    }

    public void setmCards(List<Card> mCards) {
        this.mCards = mCards;
    }

    @Override
    public void removeAll() {
        if (myCards != null && myCards.size() > 0) {
            for (Card myCard : myCards) {
                mCards.remove(myCard);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return myCards == null || myCards.size() == 0 ? true : false;
    }

    @Override
    public void clear() {
        myCards.clear();
    }
}
