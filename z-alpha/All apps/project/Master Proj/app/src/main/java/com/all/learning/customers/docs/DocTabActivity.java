package com.all.learning.customers.docs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.all.learning.R;
import com.all.learning.base.BaseActivity;
import com.all.learning.base.BaseFragment;
import com.all.learning.custom_view.tab_pager.HoriozontalAdapter;
import com.all.learning.databinding.ActivityDocTabBinding;


public class DocTabActivity extends BaseActivity {

    ActivityDocTabBinding mBinding;

    String[] list = new String[]{
            "Docs",
            "PPT",
            "Video",
            "Tips"
    };

    public static Intent prepareIntent(Context context) {
        Intent intent = new Intent(context, DocTabActivity.class);
        return intent;
    }


//    Integer[] icon = new Integer[]{
//            R.drawable.ic_action_db,
//            R.drawable.ic_action_add,
//            R.drawable.ic_action_up,
//            R.drawable.ic_action_down
//    };

    Fragment[] fragments = new Fragment[]{
            new DocFragment(),
            new DocFragment(),
            new DocFragment(),
            new DocFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityDocTabBinding) create(R.layout.activity_doc_tab);

        mBinding.pagerLayout.with(getSupportFragmentManager()).
                setPagerFragmentListener(new HoriozontalAdapter.PagerFragmentListener() {
                    @Override
                    public int getcount() {
                        return 4;
                    }

                    @Override
                    public String getTitle(int position) {
                        return list[position];
                    }

                    @Override
                    public Fragment getItem(int position) {
                        return fragments[position];
                    }

                    @Override
                    public void toolbar(Toolbar toolbar) {
                        initToolbar(toolbar, "Android Notes");
                    }


                    @Override
                    public int getOffset() {
                        return 0;
                    }

                    @Override
                    public void selectedPage(int position) {

                        if (position == 1) {
                            mBinding.pagerLayout.setBeh(false);
                        } else {
                            mBinding.pagerLayout.setBeh(true);
                        }
                        ((BaseFragment) fragments[position]).onScreen();
                    }

                    @Override
                    public void tabAt(TabLayout.Tab tab, int position) {
                        //tab.setIcon(icon[position]);
                    }
                }).
                start();
    }
}
