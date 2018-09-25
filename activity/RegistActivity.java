package com.yue_health.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sh.zsh.code.annotation.FormCheck;
import com.sh.zsh.code.annotation.FormInjection;
import com.sh.zsh.code.check.CheckType;
import com.sh.zsh.code.check.FormCheckInterface;
import com.sh.zsh.code.form.FormInit;
import com.sh.zsh.code.form.FormUtls;
import com.sh.zsh.code.layout.model.ViewData;
import com.sh.zsh.code.layout.view.FormSpinner;
import com.sh.zsh.code.layout.view.FormTimeView;
import com.yue_health.R;
import com.yue_health.dao.UserDao;
import com.yue_health.form.RegistForm;
import com.yue_health.entity.User;

import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 用户注册界面
 */
public class RegistActivity extends BaseActivity implements FormCheckInterface {
    private static final String TAG = "RegistActivity";
    /**
     * name 对应 实体类 字段名
     * message 参数为空的时候 默认提示 的字符串
     * isNull  该字段是否可以为空  默认 false
     */

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

    private UserDao userDao;

    private Button mRegistButton;//注册按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        userDao = UserDao.getInstance(getApplicationContext()); //获取UserDao对象
        initView();
        initEvents();
        initDatas();
    }

    /**
     * 通过 实现 FormCheckInterface 接口的 formCheck
     * 自定定义 表单检查 默认要返回true
     *
     * @param v
     * @return
     */
    @Override
    public boolean formCheck(View v) {
        Log.d(TAG, "formCheck: " + v);
        switch (v.getId()) {
            case R.id.id_regist_name: {
                if (TextUtils.isEmpty(mEditName.getText().toString())) {
                    toast(this, "姓名不能为空");
                    return false;
                }
                break;
            }
            case R.id.id_regist_confirm_passwd: {
                if (!mEditConfirmPasswd.getText().toString().equals(mEditPasswd.getText().toString())) {
                    toast(this, "两次输入密码不一样");
                    return false;
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void formCheckParamCall(View v, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        mEditName = findViewById(R.id.id_regist_name);
        mSpinnerGender = findViewById(R.id.id_regitst_gender);
        mTimeViewBirthday = findViewById(R.id.id_regitst_birthday);
        mEditTextTel = findViewById(R.id.id_regist_phone);
        mEditEmail = findViewById(R.id.id_regist_email);
        mEditOccupation = findViewById(R.id.id_regist_occupation);
        mEditPasswd = findViewById(R.id.id_regist_passwd);
        mEditConfirmPasswd = findViewById(R.id.id_regist_confirm_passwd);
        mRegistButton = findViewById(R.id.id_button_regist);
        mEditTextNickname = findViewById(R.id.id_regist_nickname);
//        mEditAge = findViewById(R.id.id_regist_age);
    }

    @Override
    public void initEvents() {
        mRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                summit();
            }
        });
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

    public void summit() {
        /**
         * 表单自动生成对象
         */
        RegistForm registForm = FormUtls.formToObjectAndCheck(this, RegistForm.class);

        Log.d(TAG, "summit: " + registForm);
        if (registForm != null) {
            User user = User.createUser(registForm);
            Log.d(TAG, "summit: " + user);
            switch (userDao.createUser(user)) {
                case UserDao.PHONE_EXIST: {
                    showDialog(this, "注册失败:电话号码已存在!", null);
                    break;
                }
                case UserDao.EMAIL_EXIST: {
                    showDialog(this, "注册失败:邮箱已存在!", null);
                    break;
                }
                case UserDao.REGIST_FAIL: {
                    showDialog(this, "注册失败!", null);
                    break;
                }
                case UserDao.REGIST_SUCESS: {
                    showDialog(this, "注册成功!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginActivity.actionStart(RegistActivity.this);
                            finish();
                        }
                    });
                    break;
                }
            }

        }

    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegistActivity.class);
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
