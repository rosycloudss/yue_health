package com.yue_health.fragment.add.records;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sh.zsh.code.annotation.FormInjection;
import com.sh.zsh.code.check.FormCheckInterface;
import com.sh.zsh.code.form.FormInit;
import com.sh.zsh.code.layout.view.FormTimeView;
import com.yue_health.dao.PhysicalExaminationProjectDao;
import com.yue_health.dao.UserPhyExamInfoDao;
import com.yue_health.entity.PhysicalExaminationProject;
import com.yue_health.entity.UserPhysicalExaminationInfo;
import com.yue_health.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AddRecordsBaseFragment extends BaseFragment implements View.OnClickListener {

    public static String TAG;

    public PhysicalExaminationProjectDao pepDao;

    public List<PhysicalExaminationProject> peps;

    public List<EditText> editTexts;

    public FormTimeView examTimeView;

    public UserPhyExamInfoDao upeiDao;

    public List<String> values = new ArrayList<>();
    public List<UserPhysicalExaminationInfo> upeInfos;


    /**
     * 保存用户体检信息
     */
    public void save() {
        upeInfos = new ArrayList<>();//用户体检表

        for (int i = 0; i < values.size(); i++) {
            UserPhysicalExaminationInfo upeInfo = new UserPhysicalExaminationInfo();
            upeInfo.setData(Float.parseFloat(values.get(i)));
            upeInfo.setAddTime(new Date().getTime());
            upeInfo.setUserId(user.getUserId());
            upeInfo.setProjectId(peps.get(i).getProjectId());
            upeInfo.setExamTime(examTimeView.getTimeString());
            upeInfos.add(upeInfo);
        }

        Log.d(TAG, "save: 保存用户体检表信息" + upeInfos);

        if (upeiDao.add(upeInfos) == upeInfos.size()) {
            showDialog(getContext(), "保存成功", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetEdits();
                }
            });
        } else {
            showDialog(getContext(), "保存保存失败", null);
        }


    }


    /**
     * 检查用户输入
     */
    public boolean checkInput() {
        boolean cancel = false;
        EditText focusView = null;
        String message = "不能为空";
        getInputValue();
        //清空表单中输入框的错误提示
        for (int i = 0; i < editTexts.size(); i++) {
            editTexts.get(i).setError(null);
        }
        //判断每个输入框中输入数据是否为空
        for (int i = values.size() - 1; i > 0; i--) {
            if (values.get(i).length() == 0) {
                focusView = editTexts.get(i);
                cancel = true;
            }
        }
        if (cancel) {
            focusView.requestFocus();
            focusView.setError(message);
        } else {
            return true;
        }
        return false;
    }

    /**
     * 获取用户输入的值
     */
    public abstract void getInputValue();


    /**
     * 将输入框中的内容置为空
     */
    public void resetEdits() {
        for (int i = 0; i < editTexts.size(); i++) {
            editTexts.get(i).setText("");
        }
        if (!editTexts.isEmpty()) {
            editTexts.get(0).requestFocus();
        }
    }

}
