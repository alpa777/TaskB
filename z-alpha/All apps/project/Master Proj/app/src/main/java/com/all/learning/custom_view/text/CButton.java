package com.all.learning.custom_view.text;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;


/**
 * Created by Mihir on 29-11-2016.
 * Button - set custom font here if required.
 */


public class CButton extends AppCompatButton {

    Context mContext;

    public CButton(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }


    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeue.otf");
            setTypeface(tf);
        }
    }
}
