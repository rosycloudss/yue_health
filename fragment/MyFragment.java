package com.yue_health.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yue_health.R;
import com.yue_health.activity.BaseActivity;
import com.yue_health.activity.LoginActivity;
import com.yue_health.activity.RecordsHistoryActivity;
import com.yue_health.activity.UserInfoActivity;
import com.yue_health.dao.UserDao;
import com.yue_health.entity.User;
import com.yue_health.my_view.CircleImageView;

public class MyFragment extends BaseFragment implements View.OnClickListener {


    private static final String TAG = "MyFragment";


    private CircleImageView mUserHeadImg;
    private RelativeLayout mMyRecordHistory;
    private RelativeLayout mMySettings;
    private Button mMyLogout;
    private TextView mMyUserName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment, container, false);
        initView();
        initEvents();
        initDatas();
        return view;
    }

    @Override
    public void initView() {
        mUserHeadImg = view.findViewById(R.id.id_my_user_head_img);
        mMyRecordHistory = view.findViewById(R.id.id_my_records_history);
        mMySettings = view.findViewById(R.id.id_my_setting);
        mMyLogout = view.findViewById(R.id.id_my_logout);
        mMyUserName = view.findViewById(R.id.id_my_nickname);
    }

    @Override
    public void initEvents() {
        mUserHeadImg.setOnClickListener(this);
        mMyRecordHistory.setOnClickListener(this);
        mMySettings.setOnClickListener(this);
        mMyLogout.setOnClickListener(this);
    }

    @Override
    public void initDatas() {
        userDao = UserDao.getInstance(getContext());
        user = userDao.getLoginUser();
        if (user == null) {
            Log.d(TAG, "initDatas: 用户未登录!");
            onDestroy();
        }

        mMyUserName.setText(user.getNickname());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_my_user_head_img: {
                Log.i(TAG, "onClick: 跳转到用户信息界面");
                UserInfoActivity.actionStart(getContext());
                break;
            }
            case R.id.id_my_records_history: {
                Log.i(TAG, "onClick: 跳转到历史档案");
                RecordsHistoryActivity.actionStart(getContext());
                break;
            }
            case R.id.id_my_setting: {

                break;
            }
            case R.id.id_my_logout: {
                Log.i(TAG, "onClick: " + user.getAccount() + "退出登录!");
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage("确认退出?");
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDao.deleteLoginUser();
                        LoginActivity.actionStart(getContext());
                        getActivity().finish();
                    }
                });
                dialog.setNegativeButton("取消", null);
                dialog.show();
                break;
            }
        }
    }
}
