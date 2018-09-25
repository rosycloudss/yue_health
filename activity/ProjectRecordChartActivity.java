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
import android.widget.TextView;

import com.yue_health.R;
import com.yue_health.dao.UserDao;
import com.yue_health.fragment.chart.BarChartFragment;
import com.yue_health.fragment.chart.LineChartFragment;
import com.yue_health.fragment.chart.PieCharFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 本页面将用户的体检信息以图表的形式展示
 */
public class ProjectRecordChartActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ProjectRecordChartActiv";

    //页面button
    private Button mBarChartButton;
    private Button mLineChartButton;
    private Button mPieChartButton;
    private TextView projectNameView;
    private ViewPager mViewPager;

    private Context context = ProjectRecordChartActivity.this;

    private String projectName;
    private int projectId;

    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_record_chart);
        Intent intent = getIntent();
        projectName = intent.getStringExtra("project_name");
        projectId = intent.getIntExtra("project_id", 0);
        if (projectId == 0) {
            MainActivity.actionStart(context, 1);
            finish();
        }

        initView();
        initEvents();
        initDatas();
    }

    @Override
    public void initView() {
        mBarChartButton = findViewById(R.id.id_button_bar_chart);
        mLineChartButton = findViewById(R.id.id_button_line_chart);
        mPieChartButton = findViewById(R.id.id_button_pie_chart);
        projectNameView = findViewById(R.id.id_project_info_name);
        mViewPager = findViewById(R.id.id_viewpager_chart);
        projectNameView.setText(projectName == null ? "" : projectName);
        resetButton();
    }

    @Override
    public void initEvents() {
        mBarChartButton.setOnClickListener(this);
        mLineChartButton.setOnClickListener(this);
        mPieChartButton.setOnClickListener(this);
    }

    @Override
    public void initDatas() {
        userDao = UserDao.getInstance(getApplicationContext());
        user = userDao.getLoginUser();
        if (user == null) {
            LoginActivity.actionStart(context);
            finish();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("project_id", projectId);
        bundle.putInt("user_id", user.getUserId());

        mFragments = new ArrayList<>();

        BarChartFragment barChartFragment = new BarChartFragment();
        barChartFragment.setArguments(bundle);

        LineChartFragment lineChartFragment = new LineChartFragment();
        lineChartFragment.setArguments(bundle);

        PieCharFragment pieCharFragment = new PieCharFragment();
        pieCharFragment.setArguments(bundle);

        mFragments.add(barChartFragment);
        mFragments.add(lineChartFragment);
        mFragments.add(pieCharFragment);

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
        switch (v.getId()) {
            case R.id.id_button_bar_chart: {
                selectTab(0);
                break;
            }
            case R.id.id_button_line_chart: {
                selectTab(1);
                break;
            }
            case R.id.id_button_pie_chart: {
                selectTab(2);
                break;
            }
        }

    }


    public void selectTab(int position) {
        //根据点击的Tab设置对应的Button为绿色
        Drawable drawable = getDrawable(R.drawable.create_record_select_button);
        switch (position) {
            case 0: {
                mBarChartButton.setBackground(drawable);
                break;
            }
            case 1: {
                mLineChartButton.setBackground(drawable);
                break;
            }
            case 2: {
                mPieChartButton.setBackground(drawable);
                break;
            }
        }
        mViewPager.setCurrentItem(position);
    }

    private void resetButton() {
        Drawable drawable = getDrawable(R.drawable.create_record_button);
        mBarChartButton.setBackground(drawable);
        mLineChartButton.setBackground(drawable);
        mPieChartButton.setBackground(drawable);
    }

    public static void actionStart(Context context, int projectId, String projectName) {
        Intent intent = new Intent(context, ProjectRecordChartActivity.class);
        intent.putExtra("project_id", projectId);
        intent.putExtra("project_name", projectName);
        context.startActivity(intent);
    }
}
