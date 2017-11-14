package com.all.learning.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by root on 6/6/17.
 */

public class CommonAdapter<T extends Object, B extends ViewDataBinding> extends RecyclerView.Adapter<CommonAdapter.BindingHolder> {

    Context context;
    List<T> list;
    int layoutId;
    OnItemBindListener<T, B> onItemBindListener;
    public int selectedPosition;

    public CommonAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }



    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public CommonAdapter setOnItemBindListener(OnItemBindListener<T, B> onItemBindListener) {
        this.onItemBindListener = onItemBindListener;
        return this;
    }

    public CommonAdapter setLayoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {
        B binding = (B) holder.binding;
        onItemBindListener.onItemBind(list.get(position), binding,position);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemBindListener != null) {
                    onItemBindListener.onItemClick(position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

