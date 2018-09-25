package com.yue_health.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yue_health.dao.UserDao;
import com.yue_health.entity.User;

public abstract class BaseFragment extends Fragment {
    public User user;

    public UserDao userDao;

    public View view;

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
}
