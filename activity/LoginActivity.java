package com.yue_health.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.j256.ormlite.field.DatabaseField;
import com.yue_health.R;
import com.yue_health.dao.UserDao;
import com.yue_health.entity.User;
import com.yue_health.util.AppInit;
import com.yue_health.util.DatabaseHelper;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    //界面组件
    private EditText mInputAccount;
    private EditText mInputPassword;
    private Button mLoginButton;
    private Button mRegistButton;
    private TextView mForgetPassword;


    private String mAccount;//用户账号
    private String mPassword;//用户密码

    private UserDao userDao;

    private Context context = LoginActivity.this;//当前界面的上下文

    private AppInit appInit;//app信息

    private boolean clickButton = false; //记录是否点击登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDao = UserDao.getInstance(getApplicationContext());

        //当app 第一次运行时,会添加 体检项目信息到数据库中,如果不是第一次运行,则会增加运行次数
        appInit = new AppInit(getApplicationContext());
        appInit.updateAppInfo();

        initView();
        initEvents();
        initDatas();
    }

    @Override
    public void initView() {
        //初始化登录页的组件
        mInputAccount = findViewById(R.id.id_input_account);
        mInputPassword = findViewById(R.id.id_input_password);
        mLoginButton = findViewById(R.id.id_button_login);
        mRegistButton = findViewById(R.id.id_button_regist);
        mForgetPassword = findViewById(R.id.id_forget_password);
    }

    @Override
    public void initEvents() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clickButton) {
                    clickButton = true;
                    atemptLogin();
                }
            }
        });
        mRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistActivity.actionStart(context);
            }
        });
    }

    @Override
    public void initDatas() {

    }

    //手机号
    static final String PHONE = "^1[3|4|5|7|8][0-9]{9}$";
    //邮箱
    static final String EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    //帐号
    static final String ACCCOUNT = "[0-9]{9}";

    //登录方式
    private static final int LOGIN_TYPE_ACCOUNT = 1; //以帐号密码形式登录
    private static final int LOGIN_TYPE_PHONE = 2;//以电话号码密码形式登录
    private static final int LOGIN_TYPE_EMAIL = 3;//以邮箱密码形式登录

    /**
     * 登录
     *
     * @return
     */
    private void atemptLogin() {
        //设置输入框的错误提示为空
        mInputAccount.setError(null);
        mInputPassword.setError(null);
        mAccount = mInputAccount.getText().toString().trim();
        mPassword = mInputPassword.getText().toString();

        boolean cancel = false; //取消登录
        View focusView = null;

        Log.d(TAG, "atemptLogin: " + mAccount + "===" + mPassword);
        if (mPassword == null || TextUtils.isEmpty(mPassword)) {
            focusView = mInputPassword;
            mInputPassword.setError("密码格式错误!");
            cancel = true;
        }

        focusView = mInputAccount;
        int loginType = 0; //登录类型
        if (mAccount != null) {
            if (mAccount.matches(ACCCOUNT)) {
                loginType = LOGIN_TYPE_ACCOUNT;
            } else if (mAccount.matches(PHONE)) {
                loginType = LOGIN_TYPE_PHONE;
            } else if (mAccount.matches(EMAIL)) {
                loginType = LOGIN_TYPE_EMAIL;
            } else {
                mInputAccount.setError("账号格式错误!");
                cancel = true;
            }
        } else {
            mInputAccount.setError("账号格式错误!");
            cancel = true;
        }


        if (cancel) {
            //如果格式错误，输入框重新获得输入焦点
            focusView.requestFocus();
        } else {
            if (login(loginType)) {
                Log.i(TAG, "atemptLogin: " + "登录成功,跳转到主界面");
                MainActivity.actionStart(context, 0);
                finish();
            } else {
                showDialog(context, "账号或者密码错误!", null);
            }
        }
        clickButton = false;
    }

    /**
     * 用户登录
     *
     * @return
     */
    public boolean login(int loginType) {
        int passwd = mPassword.hashCode();
        User user = userDao.queryByAccountAndPassword(mAccount, passwd);

        switch (loginType) {
            case LOGIN_TYPE_ACCOUNT: {
                user = userDao.queryByAccountAndPasswd(mAccount, passwd);
                break;
            }
            case LOGIN_TYPE_EMAIL: {
                user = userDao.queryByEmailAndPasswd(mAccount, passwd);
                break;
            }
            case LOGIN_TYPE_PHONE: {
                user = userDao.queryByPhoneAndPasswd(mAccount, passwd);
                break;
            }
        }
        if (user != null) {
            userDao.saveLoginUser(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAccount = null;
        mPassword = null;
        userDao = null;
    }

    @Override
    public void onBackPressed() {
        onDestroy();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
