package com.all.learning.custom_view.text;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by root on 15/2/17.
 */

public class CLTextView extends AppCompatTextView {
    Context mContext;

    public CLTextView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CLTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CLTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }


    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeue-Light.otf");
            setTypeface(tf);
        }
    }
}
