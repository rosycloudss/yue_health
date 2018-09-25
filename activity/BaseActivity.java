package com.yue_health.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yue_health.dao.UserDao;
import com.yue_health.entity.User;
import com.yue_health.my_view.CircleImageView;

public abstract class BaseActivity extends AppCompatActivity {


    public User user;
    public UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化当前界面,获取界面上的组件
     */
    public abstract void initView();

    /**
     * 初始化事件
     */
    public abstract void initEvents();

    /**
     * 初始化数据
     */
    public abstract void initDatas();


    /**
     * 在当前界面广播
     *
     * @param text
     * @param context
     */
    public void toast(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 页面弹窗
     *
     * @param context
     * @param message
     * @param clickListener
     */
    public void showDialog(Context context, String message, DialogInterface.OnClickListener clickListener) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(message);
        dialog.setPositiveButton("确认", clickListener);
        dialog.show();
    }

    public void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
