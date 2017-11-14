package com.all.learning.base;

import android.databinding.ViewDataBinding;

public interface OnItemBindListener<T extends Object, B extends ViewDataBinding> {
    void onItemBind(T t, B b, int position);

    void onItemClick(int position);
}