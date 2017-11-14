package com.all.learning.custom_view.shorts.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.all.learning.custom_view.shorts.card_master.cards.Card;
import com.all.learning.custom_view.shorts.view_utils.PagerAdapter;

import java.util.List;

/**
 * Created by root on 28/4/17.
 */

public class VerticlePagerAdapter extends PagerAdapter {
    Context mContext;
    OnViewListener onViewListener;
    List<Card> cards;


    public VerticlePagerAdapter(Context context, List<Card> cards, OnViewListener onViewListener) {
        this.mContext = context;
        this.cards = cards;
        this.onViewListener = onViewListener;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = onViewListener.getView(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup) object);
    }

    public interface OnViewListener {
        View getView(int position);
    }
}
