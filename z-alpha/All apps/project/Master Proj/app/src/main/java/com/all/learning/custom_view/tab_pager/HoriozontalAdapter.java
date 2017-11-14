package com.all.learning.custom_view.tab_pager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;

/**
 * Created by root on 6/11/17.
 */

public class HoriozontalAdapter extends FragmentPagerAdapter {

    private PagerFragmentListener pagerFragmentListener;


    public HoriozontalAdapter(FragmentManager fm, PagerFragmentListener pagerFragmentListener) {
        super(fm);
        this.pagerFragmentListener = pagerFragmentListener;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerFragmentListener.getItem(position);
    }

    @Override
    public int getCount() {
        return pagerFragmentListener.getcount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerFragmentListener.getTitle(position);
    }

    public interface PagerFragmentListener {
        int getcount();

        String getTitle(int position);

        Fragment getItem(int position);

        void toolbar(Toolbar toolbar);

        int getOffset();

        void selectedPage(int position);

        void tabAt(TabLayout.Tab tab, int position);
    }
}
