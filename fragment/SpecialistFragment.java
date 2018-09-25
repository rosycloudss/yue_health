package com.yue_health.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yue_health.R;
import com.yue_health.activity.BaseActivity;

public class SpecialistFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.specialist_fragment, container, false);
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
