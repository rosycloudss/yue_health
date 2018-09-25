package com.yue_health.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sh.zsh.code.annotation.FormCheck;
import com.sh.zsh.code.annotation.FormInjection;
import com.sh.zsh.code.check.CheckType;
import com.sh.zsh.code.check.FormCheckInterface;
import com.sh.zsh.code.form.FormInit;
import com.sh.zsh.code.layout.model.ViewData;
import com.sh.zsh.code.layout.view.FormSpinner;
import com.sh.zsh.code.layout.view.FormTimeView;
import com.yue_health.R;

import java.util.ArrayList;

public class UserInfoActivity extends BaseActivity implements FormCheckInterface {
    private static final String TAG = "UserInfoActivity";

    @FormCheck
    @FormInjection(name = "name", message = "姓名", isNull = false)
    private EditText mEditName;

    @FormCheck
    @FormInjection(name = "gender", message = "性别", isNull = false)
    private FormSpinner mSpinnerGender;

    @FormCheck
    @FormInjection(name = "birthday", message = "出生日期", isNull = false)
    private FormTimeView mTimeViewBirthday;

    //    @FormCheck
//    @FormInjection(name = "age", message = "年龄",isNull = false)
//    private EditText mEditAge;
    @FormCheck
    @FormInjection(name = "nickname", message = "昵称", isNull = false)
    private EditText mEditTextNickname;

    @FormCheck(type = CheckType.PHONE)
    @FormInjection(name = "phone", message = "电话号码", isNull = false)
    private EditText mEditTextTel;

    @FormCheck(type = CheckType.EMAIL)
    @FormInjection(name = "email", message = "邮箱", isNull = false)
    private EditText mEditEmail;

    @FormInjection(name = "occupation", message = "职业", isNull = false)
    private EditText mEditOccupation;

    @FormCheck(type = CheckType.PASSWORD)
    @FormInjection(name = "passwd", message = "密码", isNull = false)
    private EditText mEditPasswd;

    @FormCheck
    @FormInjection(name = "confirm_passwd", message = "确认密码", isNull = false)
    private EditText mEditConfirmPasswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public void initView() {

    }


    @Override
    public void initEvents() {

    }

    @Override
    public void initDatas() {
        ArrayList<ViewData> viewDatas = new ArrayList<>();
        ViewData viewDataMan = new ViewData("男", 1, "男");
        viewDatas.add(viewDataMan);
        ViewData viewDataWoman = new ViewData("女", 2, "女");
        viewDatas.add(viewDataWoman);
        mSpinnerGender.setpvOptionsList(viewDatas);
    }

    @Override
    public boolean formCheck(View v) {
        return false;
    }

    @Override
    public void formCheckParamCall(View v, String message) {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        FormInit.deleteInjection(this);
        Log.i(TAG, "onBackPressed: 销毁表单");
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        FormInit.deleteInjection(this);
        Log.i(TAG, "onDestroy: 销毁表单");
        super.onDestroy();
    }
}
