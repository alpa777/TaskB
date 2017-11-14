package com.all.learning.customers.basic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.all.learning.R;
import com.all.learning.base.BaseActivity;
import com.all.learning.base.CommonAdapter;
import com.all.learning.base.OnItemBindListener;
import com.all.learning.base.Utils;
import com.all.learning.databinding.ActivityBasicBinding;
import com.all.learning.databinding.RowBasicItemBinding;
import com.all.learning.my_bs.dashboard.ResAll;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class BasicActivity extends BaseActivity {
    private static final String KEY_BASIC = "KEY_BASIC";
    private static final String KEY_ID = "KEY_ID";
    ActivityBasicBinding mBinding;

    List<ResAll.ClBItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityBasicBinding) create(R.layout.activity_basic);
        init();
        preparRecyclerview();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public static Intent prepareIntent(Context context, String basic, String id) {
        Intent intent = new Intent(context, BasicActivity.class);
        intent.putExtra(KEY_BASIC, basic);
        intent.putExtra(KEY_ID, id);
        return intent;
    }

    CommonAdapter commonAdapter;

    private void preparRecyclerview() {
        commonAdapter = new CommonAdapter(mContext, mList);
        commonAdapter.setLayoutId(R.layout.row_basic_item);
        commonAdapter.setOnItemBindListener
                (new OnItemBindListener<ResAll.ClBItem, RowBasicItemBinding>() {
                    @Override
                    public void onItemBind(ResAll.ClBItem item, RowBasicItemBinding rBinding
                            , int position) {

                        rBinding.text.setText(item.text);
                        rBinding.other.setText("Topic " + (position + 1));
                        setIcon(id + "_" + item.icon, rBinding.icon);

                        rBinding.cardRow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mActivity, "Progress", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onItemClick(int position) {

                    }
                });
        Utils.linear(mContext, mBinding.rcLayout.rcv, commonAdapter, 2);
    }

    ResAll.ClB clB;
    String id;

    private void init() {

        buildAds(mBinding.layoutBanner.adView);
        try {
            String s = (String) getIntent().getExtras().get(KEY_BASIC);
            id = (String) getIntent().getExtras().get(KEY_ID);
            clB = new Gson().fromJson(s, ResAll.ClB.class);
            initToolbar(mBinding.toolbarLayout.toolbar, clB.title);
            mList.clear();
            mList.addAll(clB.items);
        } catch (Exception e) {
            e.printStackTrace();
        }


        mBinding.toolbarLayout.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(mContext, OtherActivity.class));
            }
        });
    }

}
