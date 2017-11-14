package com.all.learning.custom_view.shorts.card_master;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.all.learning.custom_view.shorts.card_master.cards.Card;


/**
 * Created by root on 26/4/17.
 */

public interface IPresenter<V extends ViewDataBinding,T extends Card> {

    void inflate(int id);

    void bind(V v, T t, int position);

    View draw(T t, int position);
}
