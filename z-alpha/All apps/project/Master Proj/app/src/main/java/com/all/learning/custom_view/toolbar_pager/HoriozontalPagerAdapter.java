package com.all.learning.custom_view.toolbar_pager;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by root on 6/11/17.
 */

public class HoriozontalPagerAdapter extends PagerAdapter {
    PagerListener pagerListener;

    public HoriozontalPagerAdapter(PagerListener pagerListener) {
        this.pagerListener = pagerListener;
    }

    @Override
    public int getCount() {
        return pagerListener.getcount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View item = pagerListener.getView(position);

        container.addView(item);

        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public interface PagerListener {
        int getcount();

        String getTitle(int position);

        View getView(int position);

        void toolbar(Toolbar toolbar);

        void selectedPage(int position);
    }
}
