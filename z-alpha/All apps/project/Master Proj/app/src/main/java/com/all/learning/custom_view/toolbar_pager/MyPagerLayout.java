package com.all.learning.custom_view.toolbar_pager;

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
import com.all.learning.databinding.ViewToolbarPagerBinding;

/**
 * Created by root on 6/11/17.
 */

public class MyPagerLayout extends LinearLayout {
    private Context context;
    ViewToolbarPagerBinding mBinding;
    FragmentManager fragmentManager;
    HoriozontalPagerAdapter.PagerListener pagerListener;

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
                R.layout.view_toolbar_pager, this, false);
        addView(mBinding.getRoot());
    }

    public MyPagerLayout with(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public MyPagerLayout setPagerListener(HoriozontalPagerAdapter.PagerListener pagerListener) {
        this.pagerListener = pagerListener;
        return this;
    }

    public void setBeh(boolean isBehave) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBinding.pager.setNestedScrollingEnabled(isBehave);
        }
    }

    public void start() {
        if (pagerListener == null) {
            return;
        }
        if (fragmentManager == null) {
            return;
        }
        pagerListener.toolbar(mBinding.toolbar);
        mBinding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerListener.selectedPage(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setPager();
    }

    public void setPager() {
        mBinding.pager.setAdapter(new HoriozontalPagerAdapter(pagerListener));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pagerListener.selectedPage(mBinding.pager.getCurrentItem());
            }
        }, 800);

    }
}
