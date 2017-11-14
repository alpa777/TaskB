package com.all.learning.custom_view.shorts.view_utils;

import android.view.View;


/**
 * Created by hardik-gajera on 12/7/16.
 */
public class VerticalDepthPageTransformer extends ABaseTransformer {
    private static final float MIN_SCALE = 0.90f;

    @Override
    protected void onTransform(View view, float position) {
        if (position <= 0f) {
            view.setTranslationY(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);
        } else if (position <= 1f) {
            final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setAlpha(1 - position);
            view.setPivotX(0.5f * view.getWidth());
            view.setTranslationY(view.getHeight() * -position);
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }
    }

    @Override
    protected boolean isPagingEnabled() {
        return true;
    }
}