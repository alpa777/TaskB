package com.all.learning.custom_view.shorts.card_master;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;

import com.all.learning.custom_view.shorts.card_master.cards.Card;


/**
 * Created by root on 26/4/17.
 */

public abstract class BasePresenter<V extends ViewDataBinding, T extends Card> implements
        IPresenter<V, T> {



    public Context context;
    public Activity mActivity;
    public ViewDataBinding mBinding;

    public int totalLength;

    public BasePresenter<V, T> with(Context context) {
        this.context = context;
        this.mActivity = (Activity) context;
        return this;
    }


    public BasePresenter() {
    }

    @Override
    public void inflate(int id) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(layoutInflater, id, null, false);
    }


}
