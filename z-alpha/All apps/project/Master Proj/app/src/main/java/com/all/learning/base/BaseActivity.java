package com.all.learning.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.all.learning.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by root on 25/10/17.
 */

public class BaseActivity extends AppCompatActivity {
    public Activity mActivity;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
    }

    protected ViewDataBinding create(int layoutID) {
        return DataBindingUtil.setContentView(this, layoutID);
    }

    Toolbar mToolbar;

    public void initToolbar(Toolbar toolbar, String title) {
        this.mToolbar = toolbar;
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void toolbarTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public void initToolbar(Toolbar toolbar, String title, boolean backArrow) {
        this.mToolbar = toolbar;
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(backArrow);
    }


    public void onclickfab() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setIcon(String icon, ImageView view) {

        int drawable = CommonWidget.findDrawable(mContext, icon);
        if (drawable == 0) {
            view.setImageResource(R.drawable.def);
        } else {
            view.setImageResource(drawable);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void animMove() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void buildAds(AdView adView) {
        MobileAds.initialize(getApplicationContext(), Utils.APP_ID);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.v("", "onAdFailedToLoad => " + i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.v("", "onAdLoaded => ");
            }
        });
        adView.loadAd(adRequest);
    }


    public String getMyPackageName() {
        String packageName = getPackageName();
        return packageName;
    }
}
