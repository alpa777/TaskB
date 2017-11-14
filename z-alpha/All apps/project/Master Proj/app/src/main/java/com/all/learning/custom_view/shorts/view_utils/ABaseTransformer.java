package com.all.learning.custom_view.shorts.view_utils;

import android.view.View;


/**
 * Created by hardik-gajera on 12/7/16.
 */
public abstract class ABaseTransformer implements VerticalViewPager.PageTransformer {
    protected abstract void onTransform(View view, float f);

    public void transformPage(View page, float position) {
        onPreTransform(page, position);
        onTransform(page, position);
        onPostTransform(page, position);
    }

    protected boolean hideOffscreenPages() {
        return true;
    }

    protected boolean isPagingEnabled() {
        return false;
    }

    protected void onPreTransform(View page, float position) {
        float f = 0.0f;
        float width = (float) page.getWidth();
        page.setRotationX(0.0f);
        page.setRotationY(0.0f);
        page.setRotation(0.0f);
        page.setScaleX(Constants.ENGLISH_LINE_SPACING_TITLE);
        page.setScaleY(Constants.ENGLISH_LINE_SPACING_TITLE);
        page.setPivotX(0.0f);
        page.setPivotY(0.0f);
        page.setTranslationY(0.0f);
        page.setTranslationX(isPagingEnabled() ? 0.0f : (-width) * position);
        if (hideOffscreenPages()) {
            if (position > -1.0f && position < Constants.ENGLISH_LINE_SPACING_TITLE) {
                f = Constants.ENGLISH_LINE_SPACING_TITLE;
            }
            page.setAlpha(f);
            page.setEnabled(false);
            return;
        }
        page.setEnabled(true);
        page.setAlpha(Constants.ENGLISH_LINE_SPACING_TITLE);
    }

    protected void onPostTransform(View page, float position) {
    }

    protected static final float min(float val, float min) {
        return val < min ? min : val;
    }
}