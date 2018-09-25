package com.yue_health.fragment;

import android.os.Bundle;
import android.sax.TextElementListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yue_health.R;
import com.yue_health.activity.CreateRecordActivity;
import com.yue_health.activity.ProjectInfoActivity;
import com.yue_health.activity.RecordsHistoryActivity;

public class RecordsFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "RecordsFragment";
    private ImageButton mAddRecordsImg;
    private LinearLayout mSearcBox;
    private TextView mBaseInfo,//基本信息
            mBlood,//血常规
            mLiverAndKidney,//肝脏功能
            mUrine,//尿常规
            mGynaecology,//妇科
            mHistoryRecords;//历史档案

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.records_fragment, container, false);
        initView();
        initDatas();
        initEvents();
        return view;

    }

    @Override
    public void initView() {
        mAddRecordsImg = view.findViewById(R.id.id_add_records_img);
        mSearcBox = view.findViewById(R.id.serach_box);
        mBaseInfo = view.findViewById(R.id.id_base_info);
        mBlood = view.findViewById(R.id.id_blood);
        mLiverAndKidney = view.findViewById(R.id.id_liver_and_kidney);
        mUrine = view.findViewById(R.id.id_urine);
        mGynaecology = view.findViewById(R.id.id_gynaecology);
        mHistoryRecords = view.findViewById(R.id.id_history_records);
    }

    @Override
    public void initEvents() {
        mAddRecordsImg.setOnClickListener(this);
        mSearcBox.setOnClickListener(this);
        mBaseInfo.setOnClickListener(this);
        mBlood.setOnClickListener(this);
        mLiverAndKidney.setOnClickListener(this);
        mUrine.setOnClickListener(this);
        mGynaecology.setOnClickListener(this);
        mHistoryRecords.setOnClickListener(this);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_add_records_img: {
                Log.i(TAG, "onClick: " + "跳转到添加体检档案界面");
                CreateRecordActivity.actionStart(getContext());
                break;
            }
            case R.id.serach_box: {
                break;
            }
            case R.id.id_base_info: {
                Log.i(TAG, "onClick: " + "跳转到基本信息界面");
                ProjectInfoActivity.actionStart(getContext(), "基本信息", 1);
                break;
            }
            case R.id.id_blood: {
                Log.i(TAG, "onClick: " + "跳转到血常规界面");
                ProjectInfoActivity.actionStart(getContext(), "血常规", 2);
                break;
            }
            case R.id.id_liver_and_kidney: {
                Log.i(TAG, "onClick: " + "跳转到肝肾功能界面");
                ProjectInfoActivity.actionStart(getContext(), "肝肾功能", 3);
                break;
            }
            case R.id.id_urine: {
                Log.i(TAG, "onClick: " + "跳转到尿常规界面");
                ProjectInfoActivity.actionStart(getContext(), "尿常规", 4);
                break;
            }
            case R.id.id_gynaecology: {
                Log.i(TAG, "onClick: " + "跳转到妇科界面");
                ProjectInfoActivity.actionStart(getContext(), "妇科", 5);
                break;
            }

            case R.id.id_history_records: {
                Log.d(TAG, "onClick: 跳转到历史记录的界面");
                RecordsHistoryActivity.actionStart(getContext());
                break;
            }

        }
    }
}
