package com.all.learning.customers.dashboard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.all.learning.R;
import com.all.learning.base.BaseActivity;
import com.all.learning.base.CommonAdapter;
import com.all.learning.base.CommonWidget;
import com.all.learning.base.OnItemBindListener;
import com.all.learning.base.Utils;
import com.all.learning.customers.basic.BasicActivity;
import com.all.learning.customers.docs.DocTabActivity;
import com.all.learning.customers.latest.LatestActivity;
import com.all.learning.databinding.ActivityConHomeBinding;
import com.all.learning.databinding.ConDashboardBinding;
import com.all.learning.my_bs.dashboard.AUploadActivity;
import com.all.learning.my_bs.dashboard.ResAll;
import com.all.learning.my_bs.dashboard.ResDboard;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ConHomeActivity extends BaseActivity {

    ActivityConHomeBinding mBinding;

    List<ResAll.ClAItem> menusList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_con_home);
        init();
        prepareMenus();
        preparePage();
    }

    private void fillData() {
//        String res = null;
//        try {
//            res = Utils.loadJsonFromAsset("app_dashboard.json", mContext);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (res == null) {
//            return;
//        }
        mBinding.pbar.setVisibility(View.VISIBLE);
        new GetDashboard().exe();
//        UploadA uploadDb = new UploadA();
//        uploadDb.setDbJson(res);
//        uploadDb.exe();
//
//        ResDboard resDboard = new Gson().fromJson(res, ResDboard.class);
//        List<ResDboard.Menus> menus = resDboard.app.dashboard.menus;
//
//        initToolbar(mBinding.toolbarLayout.toolbar, resDboard.app.dashboard.title, false);
//
//        menusList.clear();
//        menusList.addAll(menus);
//
//        commonAdapter.notifyDataSetChanged();
//        commonAdapterPage.notifyDataSetChanged();
    }

    public void parseData(String res) {
        ResAll resAll = new Gson().fromJson(res, ResAll.class);
        List<ResAll.ClAItem> menus = resAll.A.items;

        initToolbar(mBinding.toolbarLayout.toolbar,
                resAll.A.title, false);

        menusList.clear();
        menusList.addAll(menus);

        commonAdapter.notifyDataSetChanged();
        commonAdapterPage.notifyDataSetChanged();

        setUpChart(mBinding.chart, resAll.A.chart_list);

        if (resAll.A.adshow) {
            mBinding.layoutBanner.adView.setVisibility(View.VISIBLE);
        } else {
            mBinding.layoutBanner.adView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.pbar.setVisibility(View.GONE);
        if (menusList.size() == 0) {
            fillData();
        } else {
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getResDashboard(EventDashboardJson eventDbJson) {
        mBinding.pbar.setVisibility(View.GONE);
        parseData(eventDbJson.resDashboard);
    }

    CommonAdapter commonAdapter;

    private void prepareMenus() {
        commonAdapter = new CommonAdapter(mContext, menusList);
        commonAdapter.setLayoutId(R.layout.con_dashboard);
        commonAdapter.setOnItemBindListener(new OnItemBindListener<ResAll.ClAItem, ConDashboardBinding>() {
            @Override
            public void onItemBind(ResAll.ClAItem menus, ConDashboardBinding rBinding, final int position) {
                rBinding.text.setText(menus.text);
                int drawable = CommonWidget.findDrawable(mContext, menus.icon);

                if (drawable == 0) {
                    rBinding.icon.setImageResource(R.drawable.def);
                } else {
                    rBinding.icon.setImageResource(drawable);
                }

                rBinding.cardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position == 0) {
                            ResAll.ClAItem clAItem = menusList.get(position);
                            ResAll.ClB b = clAItem.B;
                            String s = new Gson().toJson(b);
                            Intent intent = BasicActivity.prepareIntent(mContext,
                                    s, "a1");
                            startActivity(intent);
                            animMove();
                        } else if (position == 1) {
                            ResAll.ClAItem clAItem = menusList.get(position);
                            ResAll.ClB b = clAItem.B;
                            String s = new Gson().toJson(b);
                            Intent intent = BasicActivity.
                                    prepareIntent(mContext, s, "a2");
                            startActivity(intent);
                            animMove();
                        } else if (position == 2) {
                            Intent intent = DocTabActivity.prepareIntent(mContext);
                            startActivity(intent);
                            animMove();
                        } else if (position == 3) {
                            Intent intent = LatestActivity.prepareIntent(mContext);
                            startActivity(intent);
                            animMove();
                        } else if (position == 4) {
                            Intent intent = LatestActivity.prepareIntent(mContext);
                            startActivity(intent);
                            animMove();
                        } else if (position == 5) {
                            final String appPackageName = getPackageName(); // package name of the app
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                        } else {
                            Toast.makeText(mActivity, "Under Progress", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onItemClick(int position) {
            }
        });
        Utils.grid(mContext, mBinding.rcLayout.rcv, commonAdapter, 3, 10);
    }

    CommonAdapter commonAdapterPage;

    private void preparePage() {
        commonAdapterPage = new CommonAdapter(mContext, menusList);
        commonAdapterPage.setLayoutId(R.layout.con_dashboard_page);
        commonAdapterPage.setOnItemBindListener(new OnItemBindListener<ResDboard.Menus, ViewDataBinding>() {
            @Override
            public void onItemBind(ResDboard.Menus menus, ViewDataBinding rBinding, int position) {
                int deviceWidth = CommonWidget.getWidth(mContext);
                int deviceHeight = CommonWidget.getHeight(mContext);

                int item_width = deviceWidth - (deviceWidth / 100 * 20);
                int item_height = deviceWidth - (deviceHeight / 100 * 50);
                rBinding.getRoot().setLayoutParams(
                        new ViewGroup.LayoutParams(
                                item_width
                                , 650)
                );
                rBinding.getRoot().requestFocus();
            }

            @Override
            public void onItemClick(int position) {

            }
        });
        // Utils.linearHorizontal(mContext, mBinding.rcLayoutPage.rcv, commonAdapterPage, 3);
    }

    private void init() {
        initToolbar(mBinding.toolbarLayout.toolbar, "Home", false);
        buildAds(mBinding.layoutBanner.adView);
        mBinding.toolbarLayout.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, AUploadActivity.class));


            }
        });


    }

    public void setUpChart(PieChart pieChart, List<ResAll.ClAChartItem> chart_list) {
        List<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < chart_list.size(); i++) {
            entries.add(new PieEntry(
                    Float.parseFloat(chart_list.get(i).text),
                    chart_list.get(i).value
            ));
        }
//        entries.add(new PieEntry(15, "Notes"));
//        entries.add(new PieEntry(25, "Class 11"));
//        entries.add(new PieEntry(25, "Class 12"));
//
//        entries.add(new PieEntry(25, "Dictionary"));
//        entries.add(new PieEntry(10, "Docs"));
//        entries.add(new PieEntry(10, "PPT"));


        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(
                ContextCompat.getColor(mContext, R.color.colorPrimary),
                ContextCompat.getColor(mContext, R.color.headerBackground),

                ContextCompat.getColor(mContext, R.color.green),
                ContextCompat.getColor(mContext, R.color.yellow),
                Color.RED,
                ContextCompat.getColor(mContext, R.color.colorAccent)
        );
        pieChart.setUsePercentValues(true);
        pieChart.setCenterTextColor(ContextCompat.getColor(mContext, android.R.color.transparent));

        pieChart.getLegend().setEnabled(false);

        pieChart.setUsePercentValues(true);


        PieData data = new PieData(set);
        //data.setValueTextSize(2f);

        data.setValueTypeface(Typeface.DEFAULT);
        data.setValueTextColor(Color.WHITE);

//        data.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return value + "";
//            }
//        });
        pieChart.getDescription().setEnabled(false);
        pieChart.setData(data);
        pieChart.getData().setValueTextSize(1f);
        pieChart.setContentDescription("App");
        pieChart.animate();

//        pieChart.getData().setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//
//                String s = String.valueOf(value);
//                // + " : " + String.valueOf((int) entry.getY());
//                return s + "%";
//            }
//        });
        pieChart.invalidate();


    }


}
