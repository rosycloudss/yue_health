package com.yue_health.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.bin.david.form.core.SmartTable;
import com.sh.zsh.code.layout.view.FormTimeView;
import com.yue_health.R;
import com.yue_health.dao.PhysicalExaminationProjectDao;
import com.yue_health.dao.UserDao;
import com.yue_health.dao.UserPhyExamInfoDao;
import com.yue_health.entity.TableItem;
import com.yue_health.entity.UserPhysicalExaminationInfo;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * 历史体检记录的表单
 */
public class RecordsHistoryActivity extends BaseActivity {

    private static final String TAG = "RecordsHistoryActivity";
    private Context context = RecordsHistoryActivity.this;

    private SmartTable<TableItem> mSmartTable;
    private FormTimeView mStartTimeView, mEndTimeView;
    private Button mSureButon;

    private UserPhyExamInfoDao upeiDao;
    private PhysicalExaminationProjectDao pepDao;
    private List<UserPhysicalExaminationInfo> upeis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_history);
        initView();
        initEvents();
        initDatas();
    }

    @Override
    public void initView() {
        mSmartTable = (SmartTable<TableItem>) findViewById(R.id.id_records_table);
        mStartTimeView = findViewById(R.id.id_start_time);
        mEndTimeView = findViewById(R.id.id_end_time);
        mSureButon = findViewById(R.id.id_sure_button);
    }

    @Override
    public void initEvents() {
        mSureButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTableData(mSmartTable);
            }
        });
    }

    public void setTableData(SmartTable<TableItem> smartTable) {

        upeis = upeiDao.queryUPEByUserId(user.getUserId(),
                mStartTimeView.getTimeString(), mEndTimeView.getTimeString());
        List<TableItem> tableItemList = TableItem.toTableItem(upeis, pepDao);
        Log.d(TAG, "setTableData: " + tableItemList);
        smartTable.setData(tableItemList);
        smartTable.invalidate();
    }

    @Override
    public void initDatas() {
        userDao = UserDao.getInstance(getApplicationContext());
        user = userDao.getLoginUser();
        if (user == null) {
            MainActivity.actionStart(context, 1);
            finish();
        }

        upeiDao = UserPhyExamInfoDao.getInstance(getApplicationContext());
        pepDao = PhysicalExaminationProjectDao.getInstance(getApplicationContext());

        setTableData(mSmartTable);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecordsHistoryActivity.class);
        context.startActivity(intent);
    }
}
