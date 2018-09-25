package com.yue_health.fragment.add.records;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sh.zsh.code.annotation.FormCheck;
import com.sh.zsh.code.annotation.FormInjection;
import com.sh.zsh.code.check.FormCheckInterface;
import com.sh.zsh.code.form.FormInit;
import com.sh.zsh.code.form.FormUtls;
import com.sh.zsh.code.layout.view.FormTimeView;
import com.yue_health.R;
import com.yue_health.dao.PhysicalExaminationProjectDao;
import com.yue_health.dao.UserDao;
import com.yue_health.dao.UserPhyExamInfoDao;
import com.yue_health.entity.PhysicalExaminationProject;
import com.yue_health.entity.User;
import com.yue_health.entity.UserPhysicalExaminationInfo;
import com.yue_health.form.BaseInfoForm;
import com.yue_health.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 添加基本信息的Fragment
 */
public class AddBaseInfoFragment extends AddRecordsBaseFragment {

    private static final String TAG = "AddBaseInfoFragment";


    private EditText mEditUserHeight;

    private EditText mEditUserWeight;

    private EditText mEditSystolicPressure;

    private EditText mEditDiastolicBloodPressure;

    private EditText mEditTG;

    private EditText mEditGLU;

    private EditText mEditCEA;


    private Button mButtonSave;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.add_base_info_fragment, container, false);
        }

        initView();
        initEvents();
        initDatas();
        if (user == null) {
            showDialog(getContext(), "用户未登录!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onDestroy();
                }
            });

        }
        return view;
    }

    @Override
    public void initView() {

        mEditUserHeight = view.findViewById(R.id.id_user_height);
        mEditUserWeight = view.findViewById(R.id.id_user_weight);
        mEditSystolicPressure = view.findViewById(R.id.id_user_systolic_pressure);
        mEditDiastolicBloodPressure = view.findViewById(R.id.id_diastolic_blood_pressure);
        mEditTG = view.findViewById(R.id.id_TG);
        mEditGLU = view.findViewById(R.id.id_user_GLU);
        mEditCEA = view.findViewById(R.id.id_user_CEA);
        examTimeView = view.findViewById(R.id.id_exam_time);
        mButtonSave = view.findViewById(R.id.id_save);
        FormInit.injection(this);
    }

    @Override
    public void initEvents() {
        mButtonSave.setOnClickListener(this);
    }

    @Override
    public void initDatas() {
        userDao = UserDao.getInstance(getContext());
        user = userDao.getLoginUser();
        upeiDao = UserPhyExamInfoDao.getInstance(getContext());
        //将当前界面的 Edit 添加到到 editText
        editTexts = new ArrayList<>();
        editTexts.add(mEditUserHeight);
        editTexts.add(mEditUserWeight);
        editTexts.add(mEditSystolicPressure);
        editTexts.add(mEditDiastolicBloodPressure);
        editTexts.add(mEditTG);
        editTexts.add(mEditGLU);
        editTexts.add(mEditCEA);

        pepDao = PhysicalExaminationProjectDao.getInstance(getContext());
        peps = pepDao.queryProjectByTypeId(1);
        Log.d(TAG, "initDatas: " + peps);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_save: {
                if (checkInput()) {
                    save();
                    Log.d(TAG, "onClick: 保存用户体检表信息" + upeInfos);
                }
                break;
            }
        }
    }

    /**
     * 获取用户输入的值
     */
    @Override
    public void getInputValue() {
        values.clear();
        values.add(mEditUserHeight.getText().toString());
        values.add(mEditUserWeight.getText().toString());
        values.add(mEditSystolicPressure.getText().toString());
        values.add(mEditDiastolicBloodPressure.getText().toString());
        values.add(mEditTG.getText().toString());
        values.add(mEditGLU.getText().toString());
        values.add(mEditCEA.getText().toString());
    }


}
