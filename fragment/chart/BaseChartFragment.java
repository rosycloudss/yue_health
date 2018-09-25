package com.yue_health.fragment.chart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.yue_health.activity.MainActivity;
import com.yue_health.dao.PhysicalExaminationProjectDao;
import com.yue_health.dao.UserPhyExamInfoDao;
import com.yue_health.entity.PhysicalExaminationProject;
import com.yue_health.entity.UserPhysicalExaminationInfo;
import com.yue_health.fragment.BaseFragment;

import java.util.List;

public class BaseChartFragment extends BaseFragment {

    private static final String TAG = "BaseChartFragment";
    public int projectId;
    public int userId;
    public List<UserPhysicalExaminationInfo> upeInfos;
    public UserPhyExamInfoDao upeiDao;
    public PhysicalExaminationProjectDao pepDao;
    public PhysicalExaminationProject pep;


    @Override
    public void initView() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDatas() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            projectId = bundle.getInt("project_id", 0);
            userId = bundle.getInt("user_id", 0);
        }

        //如何项目Id 或 用户Id 为 0则跳转到 首页
        if (projectId == 0 || userId == 0) {
            MainActivity.actionStart(getContext(), 0);
            onDestroy();
        }

        upeiDao = UserPhyExamInfoDao.getInstance(getContext());
        upeInfos = upeiDao.queryUPEIByUserIdAndId(userId, projectId);
        pepDao = PhysicalExaminationProjectDao.getInstance(getContext());
        pep = pepDao.queryProjectById(projectId);

        Log.d(TAG, "initDatas: " + userId + "====" + projectId);
        Log.d(TAG, "initDatas: " + upeInfos);
        Log.d(TAG, "initDatas: " + pep);

    }
}
