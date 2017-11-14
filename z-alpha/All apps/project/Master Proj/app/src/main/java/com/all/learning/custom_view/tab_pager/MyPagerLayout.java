package com.all.learning.custom_view.tab_pager;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.all.learning.R;
import com.all.learning.databinding.ViewPagerBinding;

/**
 * Created by root on 6/11/17.
 */

public class MyPagerLayout extends LinearLayout {
    private Context context;
    ViewPagerBinding mBinding;
    FragmentManager fragmentManager;
    HoriozontalAdapter.PagerFragmentListener pagerFragmentListener;

    public MyPagerLayout(Context context) {
        super(context);
        init(context);
    }

    public MyPagerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyPagerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        inflate();
    }

    private void inflate() {
        removeAllViews();
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.view_pager, this, false);
        addView(mBinding.getRoot());
    }

    public MyPagerLayout with(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public MyPagerLayout setPagerFragmentListener(HoriozontalAdapter.PagerFragmentListener pagerFragmentListener) {
        this.pagerFragmentListener = pagerFragmentListener;
        return this;
    }

    public void setBeh(boolean isBehave) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBinding.pager.setNestedScrollingEnabled(isBehave);
        }
    }

    public void start() {
        if (pagerFragmentListener == null) {
            return;
        }
        if (fragmentManager == null) {
            return;
        }
        mBinding.pager.setOffscreenPageLimit(pagerFragmentListener.getOffset());
        pagerFragmentListener.toolbar(mBinding.toolbar);
        mBinding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerFragmentListener.selectedPage(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setPager();
    }

    public void setPager() {
        mBinding.tabLayout.setupWithViewPager(mBinding.pager);
        mBinding.pager.setAdapter(new HoriozontalAdapter(fragmentManager, pagerFragmentListener));
        for (int i = 0; i < mBinding.tabLayout.getTabCount(); i++) {
            pagerFragmentListener.tabAt(mBinding.tabLayout.getTabAt(i), i);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pagerFragmentListener.selectedPage(mBinding.pager.getCurrentItem());
            }
        }, 800);

    }
}
