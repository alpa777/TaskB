package com.all.learning.customers.latest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.all.learning.R;
import com.all.learning.base.BaseActivity;
import com.all.learning.custom_view.shorts.ShortLayout;
import com.all.learning.custom_view.shorts.card_master.BasePresenter;
import com.all.learning.custom_view.shorts.card_master.cards.Card;
import com.all.learning.customers.card_all.presenter.SplashPresenter;
import com.all.learning.databinding.ActivityLatestBinding;

import java.util.ArrayList;
import java.util.List;


public class LatestActivity extends BaseActivity {
    ActivityLatestBinding mBinding;
    List<Card> mList = new ArrayList<>();
    BasePresenter presenter = new SplashPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityLatestBinding) create(R.layout.activity_latest);
        init();
    }

    private void init() {
        mList.clear();
        mList.add(new Card());
        mList.add(new Card());
        mList.add(new Card());
        mList.add(new Card());

        mBinding.shortLayout.bind(mList, new ShortLayout.VertPageListener() {
            @Override
            public View getVertPage(int position, Card card) {
                View view = presenter.with(mActivity).draw(card, position);
                return view;
            }
        });
    }

    public static Intent prepareIntent(Context context) {
        Intent intent = new Intent(context, LatestActivity.class);
        return intent;
    }

}
