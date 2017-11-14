package com.all.learning.custom_view.text;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by root on 15/2/17.
 */

public class CMBTextView extends AppCompatTextView {
    public CMBTextView(Context context) {
        super(context);

        init();
    }

    public CMBTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CMBTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeue-Medium.otf");
            setTypeface(tf);
        }
    }
}
