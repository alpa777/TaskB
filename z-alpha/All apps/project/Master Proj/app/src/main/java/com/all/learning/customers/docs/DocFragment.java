package com.all.learning.customers.docs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.all.learning.R;
import com.all.learning.base.BaseFragment;
import com.all.learning.databinding.FrgDashboardBinding;

/**
 * Created by root on 10/11/17.
 */

public class DocFragment extends BaseFragment {

    FrgDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frg_dashboard, container, false);
        return binding.getRoot();
    }
}
