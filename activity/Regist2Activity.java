package com.yue_health.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yue_health.R;

public class Regist2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist2);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDatas() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, Regist2Activity.class);
        context.startActivity(intent);
    }
}
