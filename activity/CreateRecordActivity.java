package com.yue_health.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yue_health.R;
import com.yue_health.fragment.add.records.AddBaseInfoFragment;
import com.yue_health.fragment.add.records.AddBloodFragment;
import com.yue_health.fragment.add.records.AddGynaecologyFragment;
import com.yue_health.fragment.add.records.AddLiverAndKidneyFragment;
import com.yue_health.fragment.add.records.AddUrineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加体检记录页面
 */
public class CreateRecordActivity extends BaseActivity implements View.OnClickListener {


    private Context context = CreateRecordActivity.this;
    private ImageButton mBackRecords;

    private Button mBaseInfoButton;//基本信息
    private Button mBloodButton;//血常规
    private Button mLiverAnKidneyButton;//肝脏功能
    private Button mUrineButton;//尿常规
    private Button mGynaecologyButton;//妇科

    private ViewPager mViewPager;
    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recod);
        initView();
        initEvents();
        initDatas();
    }

    @Override
    public void initView() {
        //初始化界面组件
        mBackRecords = findViewById(R.id.id_back_records);
        mBaseInfoButton = findViewById(R.id.id_base_info);
        mBloodButton = findViewById(R.id.id_blood);
        mLiverAnKidneyButton = findViewById(R.id.id_liver_and_kidney);
        mUrineButton = findViewById(R.id.id_urine);
        mGynaecologyButton = findViewById(R.id.id_gynaecology);
        mViewPager = findViewById(R.id.id_viewpager);
    }

    @Override
    public void initEvents() {
        mBaseInfoButton.setOnClickListener(this);
        mBloodButton.setOnClickListener(this);
        mLiverAnKidneyButton.setOnClickListener(this);
        mUrineButton.setOnClickListener(this);
        mGynaecologyButton.setOnClickListener(this);

        mBackRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.actionStart(context, 1);
                finish();
            }
        });
    }

    @Override
    public void initDatas() {
        mFragments = new ArrayList<>();

        mFragments.add(new AddBaseInfoFragment());
        mFragments.add(new AddBloodFragment());
        mFragments.add(new AddLiverAndKidneyFragment());
        mFragments.add(new AddUrineFragment());
        mFragments.add(new AddGynaecologyFragment());


        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
                return mFragments.get(position);
            }

            @Override
            public int getCount() {//获取集合中Fragment的总数
                return mFragments.size();
            }

        };

        //设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment
                mViewPager.setCurrentItem(position);
                resetButton();
                selectTab(position);
            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
        selectTab(0);

    }

    @Override
    public void onClick(View v) {
        resetButton();
        switch (v.getId()) {
            case R.id.id_base_info: {
                selectTab(0);
                break;
            }
            case R.id.id_blood: {
                selectTab(1);
                break;
            }
            case R.id.id_liver_and_kidney: {
                selectTab(2);
                break;
            }
            case R.id.id_urine: {
                selectTab(3);
                break;
            }
            case R.id.id_gynaecology: {
                selectTab(4);
                break;
            }
        }
    }

    private void selectTab(int position) {
        //根据点击的Tab设置对应的Button为绿色
        Drawable drawable = getDrawable(R.drawable.create_record_select_button);
        switch (position) {
            case 0: {
                mBaseInfoButton.setBackground(drawable);
                break;
            }
            case 1: {
                mBloodButton.setBackground(drawable);
                break;
            }
            case 2: {
                mLiverAnKidneyButton.setBackground(drawable);
                break;
            }
            case 3: {
                mUrineButton.setBackground(drawable);
                break;
            }
            case 4: {
                mGynaecologyButton.setBackground(drawable);
                break;
            }
        }
        //设置viewPager的当前Tab
        mViewPager.setCurrentItem(position);

    }

    private void resetButton() {
        Drawable drawable = getDrawable(R.drawable.create_record_button);
        mBaseInfoButton.setBackground(drawable);
        mBloodButton.setBackground(drawable);
        mLiverAnKidneyButton.setBackground(drawable);
        mUrineButton.setBackground(drawable);
        mGynaecologyButton.setBackground(drawable);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CreateRecordActivity.class);
        context.startActivity(intent);
    }
}
