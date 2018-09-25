package com.yue_health.fragment.chart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yue_health.R;
import com.yue_health.fragment.BaseFragment;

public class PieCharFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.linechart_fragment, container, false);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDatas() {

    }
}
