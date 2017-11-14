package com.all.learning.customers.card_all.presenter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.all.learning.R;
import com.all.learning.custom_view.shorts.card_master.BasePresenter;
import com.all.learning.custom_view.shorts.card_master.cards.Card;


/**
 * Created by root on 25/10/17.
 */

public class SplashPresenter extends BasePresenter<ViewDataBinding, Card> {

    @Override
    public void bind(ViewDataBinding rBinding, Card card, int position) {
    }

    @Override
    public View draw(Card card, int position) {
        inflate(R.layout.row_short_notes);
        bind((ViewDataBinding) mBinding, card, position);
        return mBinding.getRoot();
    }
}
