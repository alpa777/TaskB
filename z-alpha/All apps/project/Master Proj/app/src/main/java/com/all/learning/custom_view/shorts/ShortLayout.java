package com.all.learning.custom_view.shorts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.all.learning.R;
import com.all.learning.custom_view.shorts.adapters.VerticlePagerAdapter;
import com.all.learning.custom_view.shorts.card_master.cards.Card;
import com.all.learning.custom_view.shorts.view_utils.VerticalDepthPageTransformer;
import com.all.learning.databinding.ViewVertPagerBinding;

import java.util.List;

/**
 * Created by root on 28/9/17.
 */

public class ShortLayout extends LinearLayout {
    Context context;

    ViewVertPagerBinding mBinding;


    public ShortLayout(Context context) {
        super(context);
        init(context);
    }

    public ShortLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShortLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        inflate();
    }

    private void inflate() {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.view_vert_pager, this, false);
        addView(mBinding.getRoot());
    }


    public void bind(List<Card> cards, VertPageListener onDatsetViewListener) {
        this.onDatsetViewListener = onDatsetViewListener;
        adapter(cards);
    }

    VerticlePagerAdapter mAdapter;

    public void refresh() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void navAt(int position) {
        mBinding.pager.setCurrentItem(position);
    }

    private void adapter(final List<Card> cards) {
        mAdapter = new VerticlePagerAdapter(context, cards, new VerticlePagerAdapter.OnViewListener() {
            @Override
            public View getView(int position) {
                return onDatsetViewListener.getVertPage(position, cards.get(position));
            }
        });
        mBinding.pager.setPageTransformer(true, new VerticalDepthPageTransformer());
        mBinding.pager.setAdapter(mAdapter);
    }

    VertPageListener onDatsetViewListener;

    public interface VertPageListener {
        View getVertPage(int position, Card card);
    }

}
